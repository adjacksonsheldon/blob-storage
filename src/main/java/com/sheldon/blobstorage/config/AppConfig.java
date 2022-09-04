package com.sheldon.blobstorage.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfig {

    @Value("${AZURE_STORAGE_CONNECTION_STRING}")
    private String connectStr;

    @Value("${SOURCE_FILES}")
    private String sourceFiles;

    @Value("${CONTAINER_NAME}")
    private String containerName;
}
