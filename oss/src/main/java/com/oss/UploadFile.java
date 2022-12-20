package com.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.oss.dto.OSSOperateDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

@Slf4j
public class UploadFile {

    static CharSequence cdnPrefix=null;

    /**
     * 可访问的url
     */
    private static String getAccessUrl(String bucketName, String objectName){
        // ali oss地址: https://yadea-tsp.oss-cn-shanghai.aliyuncs.com/tsp/user/header/5f864e04de0349f8b9a80f5f94f58777.jpeg
        if(StringUtils.isBlank(cdnPrefix)){
            return "https://" + bucketName + "." + "oss-cn-shanghai.aliyuncs.com" + "/" + objectName;
        }
        // CDN 地址: https://tsposs.yadeaiot.com.cn/tsp/user/header/5f864e04de0349f8b9a80f5f94f58777.jpeg
        return cdnPrefix + "/" + objectName;
    }

    public static void main(String[] args) throws Exception {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTW";
        String accessKeySecret = "CW0pBRucw";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "examplebucket4";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "test/example1.txt";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        String filePath= "D:\\ossTest\\1.txt";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传文件。
            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
            System.out.println(putObjectResult);

            OSSOperateDto result = OSSOperateDto.builder()
                    .accessUrl(getAccessUrl(bucketName, objectName))
                    .fullKey(objectName)
                    .build();
            System.out.println(result);
        } catch (OSSException oe) {
            log.info("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.info("Error Message:" + oe.getErrorMessage());
            log.info("Error Code:" + oe.getErrorCode());
            log.info("Request ID:" + oe.getRequestId());
            log.info("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            log.info("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.info("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
