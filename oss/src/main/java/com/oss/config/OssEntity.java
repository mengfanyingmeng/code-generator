package com.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class OssEntity {

    private String endPoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;
}
