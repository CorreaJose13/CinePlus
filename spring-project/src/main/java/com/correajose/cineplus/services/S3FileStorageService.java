package com.correajose.cineplus.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

@Service
public class S3FileStorageService {

    @Autowired
    private AmazonS3 amazonS3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

//    public static String uploadFile(MultipartFile file) throws IOException {
//        String originalFilename = file.getOriginalFilename();
//        String extension = "";
//        if (originalFilename != null && originalFilename.contains(".")) {
//            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        }
//        String uuid = UUID.randomUUID().toString();
//        String filename = uuid + extension;
//
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentLength(file.getSize());
//        metadata.setContentType(file.getContentType());
//
//        amazonS3Client.putObject(
//                new PutObjectRequest(bucketName, filename, file.getInputStream(), metadata)
//                        .withCannedAcl(CannedAccessControlList.PublicRead)
//        );
//
//        return amazonS3Client.getUrl(bucketName, filename).toString();
//    }

    public String uploadFromUrl(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        String contentType = connection.getContentType();
        int contentLength = connection.getContentLength();

        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IOException("La URL no corresponde a una imagen válida");
        }

        String extension = "";
        if (contentType.equals("image/jpeg") || contentType.equals("image/jpg")) {
            extension = ".jpg";
        } else if (contentType.equals("image/png")) {
            extension = ".png";
        } else if (contentType.equals("image/gif")) {
            extension = ".gif";
        } else {
            extension = "." + contentType.substring(contentType.indexOf("/") + 1);
        }

        String filename = UUID.randomUUID().toString() + extension;

        ObjectMetadata metadata = new ObjectMetadata();
        if (contentLength > 0) {
            metadata.setContentLength(contentLength);
        }
        metadata.setContentType(contentType);

        try (InputStream inputStream = connection.getInputStream()) {
            amazonS3Client.putObject(
                    new PutObjectRequest(bucketName, filename, inputStream, metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
        }

        return amazonS3Client.getUrl(bucketName, filename).toString();
    }
}