package org.example.service;

import io.minio.*;
import io.minio.errors.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class MinioService {
    @Autowired
    private MinioClient minioClient;
    @Value("${minio.bucket-name}")
    private String bucketName;
    @Value("${minio.url}")
    private String minioUrl;
    Logger logger = LogManager.getLogger(CompanyService.class);

    public void uploadFileToMinIO(File file) {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } else {
                logger.info("Bucket "+bucketName+" already exists.");
            }
            minioClient.uploadObject(UploadObjectArgs.builder().bucket(bucketName).object(file.getName()).filename("D:\\Code\\Java\\Storehouse\\src\\main\\resources\\image-loader\\"+file.getPath()).build());
            logger.info("Success");
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
    }

    public byte[] getImage(String imageName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(imageName)
                        .build())) {
            return convertImageToByte(ImageIO.read(stream));
        }
    }

    public byte[] convertImageToByte(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        byte[] imageBytes = baos.toByteArray();
        return imageBytes;
    }
    public String test(){
        return "hello";
    }

    public String getMinioUrl() {
        return minioUrl;
    }
}
