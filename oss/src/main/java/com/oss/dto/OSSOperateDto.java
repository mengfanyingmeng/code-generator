package com.oss.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @description: OSS操作(授权签名、上传等)之后的DTO对象
 * @author: lijian
 * @create: 2021-03-04 9:23 下午
 **/
@Data
@Builder
public class OSSOperateDto {
    /**
     * 授权签名之后的url
     */
    private String signedUrl;
    /**
     * 可访问的url
     * https://yadea-tsp-test.oss-cn-shanghai.aliyuncs.com/live/header/8bd0e1fb1ac34cc7bb64d466e723040c.png
     * protocolPrefix + bucketName + "." + EndPointTail + objectName（fileKey(业务定义的目录) + "/" + UUIDGenerator.getUUID() + 文件后缀）
     */
    private String accessUrl;
    /**
     * 在默认BucketName下面的完整的key，或者叫objectname
     */
    private String fullKey;
}
