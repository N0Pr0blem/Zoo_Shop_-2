package org.example.controller;

import io.minio.*;
import io.minio.errors.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
public class MinioController {
    @Autowired
    private MinioClient minioClient;
    @Value("${minio.bucket-name}")
    private String bucketName;
    Logger logger = LogManager.getLogger(CompanyService.class);

    @GetMapping("/upload")
    public String uploadFileToMinIO() {
        try {
            String objectName="file.txt";
            String path="C:\\Users\\megga\\Desktop\\file.txt";
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } else {
                logger.error("Bucket "+bucketName+" already exists.");
            }
            minioClient.uploadObject(UploadObjectArgs.builder().bucket(bucketName).object(objectName).filename(path).build());
            logger.trace("Success");
        } catch (MinioException e) {
            logger.error("Error occurred: " + e);
            logger.error("HTTP trace: " + e.httpTrace());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return"redirect:/";
    }

    @PostMapping("/download")
    public String downloadObject() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.downloadObject(
                DownloadObjectArgs.builder()
                        .bucket(bucketName)
                        .object("file.txt")
                        .filename("file.txt")
                        .build());
        return "redirect:/";
    }
}

