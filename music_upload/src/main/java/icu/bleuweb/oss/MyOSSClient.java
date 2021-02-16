package icu.bleuweb.oss;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Component    //将这个类 注入IOC容器中，以便之后取出来
public final class MyOSSClient {


    // TODO 这里是否有些奇怪啊，一个类的私有域的值，从配置文件中获取，然后再用这些值在构造器中 构建另一个私有的对象
    @Value("${alibaba.cloud.oss.endpoint}")
    private String endpoint;
    @Value("${alibaba.cloud.access-key}")
    private String accessKeyId;
    @Value("${alibaba.cloud.secret-key}")
    private String secretAccessKey;
    @Value("${alibaba.cloud.oss.bucket}")
    private String bucket;
    @Value("${alibaba.cloud.oss.callbackUrl}")
    private String callbackUrl;

    /**
     * 获取policy的值，返回值是什么我还没想好
     *
     * @param dir 用户上传文件时指定的前缀。
     * @throws UnsupportedEncodingException 抛出去的异常 调用时记得处理
     */
    public Map<String, String> getPolicy(String dir) throws UnsupportedEncodingException {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, secretAccessKey);

        String host = "https://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint

        long expireTime = 3000;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);

        // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        //上面构造了两个对象 expiration: 应该是过期时间  policyConds: 应该是设定了一些policy的属性
        //然后传入generatePostPolicy中，生成的就是policy -> postPolicy了吧
        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        //下面只是对policy做一些处理罢了
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        //这里是通过处理过后的policy 获取签名的值
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        // 这里是将得到的所有东西 封装到一个map中
        Map<String, String> respMap = new LinkedHashMap<String, String>();
        respMap.put("accessid", accessKeyId);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));

        JSONObject jasonCallback = new JSONObject();
        jasonCallback.put("callbackUrl", callbackUrl);
        jasonCallback.put("callbackBody",
                "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
        jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
        String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
        respMap.put("callback", base64CallbackBody);

        return respMap;
    }

    /**
     * java直接上传本地文件
     *
     * @param localFile 本地文件的位置
     */
    public void uploadLocalFile(String localFile, String fileName) throws FileNotFoundException {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, secretAccessKey);

        InputStream inputStream = new FileInputStream(localFile);
        ossClient.putObject(bucket, fileName, inputStream);

        ossClient.shutdown();
    }

}
