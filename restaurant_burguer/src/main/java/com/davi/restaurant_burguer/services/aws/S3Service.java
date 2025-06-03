package com.davi.restaurant_burguer.services.aws;

import com.davi.restaurant_burguer.interfaces.IStorageServiceAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3Service implements IStorageServiceAdapter {
    private final S3Client s3Client;
    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Value("${aws.s3.region}")
    private String region;

    public S3Service(@Value("${aws.s3.region}") String region, @Value("${aws.s3.bucket}") String bucketName) {
        this.region = region;
        this.bucketName = bucketName;
        this.s3Client = S3Client.builder()
                .region(Region.of(this.region))
                .build();
    }

    @Override
    public String uploadFile(byte[] file, String fileName, String contentType) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(this.bucketName)
                .key(fileName)
                .contentType(contentType)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file));
        return String.format("https://s3.%s.amazonaws.com/%s/%s", this.region, this.bucketName, fileName);
    }
}