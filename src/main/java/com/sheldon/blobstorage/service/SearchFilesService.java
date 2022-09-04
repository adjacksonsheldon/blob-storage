package com.sheldon.blobstorage.service;

import com.azure.storage.blob.BlobContainerClient;
import com.sheldon.blobstorage.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class SearchFilesService {

    private final AppConfig appConfig;
    private final BlobContainerClient blobContainerClient;

    public void uploadAll() throws IOException {
        Files.walk(Paths.get(appConfig.getSourceFiles()))
                .filter(Files::isRegularFile)
                .forEach(file -> uploadFile(file));
    }

    private void uploadFile(Path path) {
        final var blobClient = blobContainerClient.getBlobClient(path.getFileName().toString());
        blobClient.uploadFromFile(path.toString(), true);
    }
}
