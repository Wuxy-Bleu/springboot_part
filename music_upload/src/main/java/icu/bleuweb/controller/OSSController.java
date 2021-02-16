package icu.bleuweb.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class OSSController {

    @GetMapping("/uploadimage")
    public void uploadCover() {

//        System.out.println(ossClient);

    }

    @GetMapping("/uploadsong")
    public String uploadSong() {

        return null;
    }


//    @GetMapping("/oss/policy")
//    public Map<String, String> policy() {
//
//        String host = "https://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
//        String dir = "user-dir-prefix/"; // 用户上传文件时指定的前缀。
//
//        Map<String, String> respMap = null;
//
//        try {
//            long expireTime = 30;
//            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
//            Date expiration = new Date(expireEndTime);
//            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
//            PolicyConditions policyConds = new PolicyConditions();
//            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
//            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
//
//            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
//            byte[] binaryData = postPolicy.getBytes("utf-8");
//            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
//            String postSignature = ossClient.calculatePostSignature(postPolicy);
//
//            respMap = new LinkedHashMap<String, String>();
//            respMap.put("accessid", accessId);
//            respMap.put("policy", encodedPolicy);
//            respMap.put("signature", postSignature);
//            respMap.put("dir", dir);
//            respMap.put("host", host);
//            respMap.put("expire", String.valueOf(expireEndTime / 1000));
//
////            JSONObject jasonCallback = new JSONObject();
////            jasonCallback.put("callbackUrl", callbackUrl);
////            jasonCallback.put("callbackBody",
////                    "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
////            jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
////            String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
////            respMap.put("callback", base64CallbackBody);
////
////            JSONObject ja1 = JSONObject.fromObject(respMap);
////            // System.out.println(ja1.toString());
////            response.setHeader("Access-Control-Allow-Origin", "*");
////            response.setHeader("Access-Control-Allow-Methods", "GET, POST");
////            response(request, response, ja1.toString());
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            ossClient.shutdown();
//        }
//        return respMap;
//    }

}
