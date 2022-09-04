package com.sheldon.blobstorage.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BlobContainerClientFactory {

    private final AppConfig appConfig;

    @Bean
    public BlobContainerClient blobContainerClient(){
        return new BlobContainerClientBuilder()
                .connectionString(appConfig.getConnectStr())
                .containerName(appConfig.getContainerName())
                .buildClient();
    }
}