package icu.bleuweb;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

@SpringBootTest
public class UploadTest {

    @Test
    void testOssUpload() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-shanghai.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "";
        String accessKeySecret = "";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = new FileInputStream("D:\\singerPic\\singerPic\\1586081310686Peter Broderick.jpeg");
        ossClient.putObject("bleuweb-music", "test/1586081310686Peter Broderick.jpeg", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    void otherTest(){
        Date date1 = new Date();

        System.out.println(date1.toString());
    }

//    @Autowired
//    OSSClient ossClient;
//
//    @Test
//    void testUpload02() throws FileNotFoundException {
//        System.out.println(ossClient);
//
//        // 上传文件流。
//        InputStream inputStream = new FileInputStream("D:\\singerPic\\singerPic\\1586078732611Ramin Djawadi.jpg");
//        ossClient.putObject("bleuweb-music", "test/1586078732611Ramin Djawadi.jpg", inputStream);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//    }


    @Test
    void testUpload03(){
//        OSSClient ossClient = new OSSClient();
    }
}
