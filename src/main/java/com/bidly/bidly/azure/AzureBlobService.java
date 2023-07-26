package com.bidly.bidly.azure;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class AzureBlobService {

    @Value("${azure.storage.accountName}")
    private String accountName;

    @Value("${azure.storage.accountKey}")
    private String accountKey;

    public String uploadMultipartFileToBlob(MultipartFile file, String fileName) throws IOException {

        String connectionString = String.format("DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net",
                accountName, accountKey);
        System.out.println(connectionString);
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("images");
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        try (InputStream is = file.getInputStream()) {
            blobClient.upload(is, file.getSize(), true);
        }
        return blobClient.getBlobUrl();
    }
}
