package com.sheldon.blobstorage.controller;

import com.azure.storage.blob.BlobContainerClientBuilder;
import com.sheldon.blobstorage.config.AppConfig;
import com.sheldon.blobstorage.service.SearchFilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("blobs")
@RequiredArgsConstructor
public class BlobController {
    private final AppConfig appConfig;

    private final SearchFilesService searchFilesService;

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("containerName") String containerName) throws IOException {

        final var blobContainerClient = new BlobContainerClientBuilder()
                .connectionString(appConfig.getConnectStr())
                .containerName(containerName)
                .buildClient();

        final var blobClient = blobContainerClient.getBlobClient(file.getOriginalFilename());

        blobClient.upload(file.getInputStream(), file.getSize(), true);

        return "file has created";
    }

    @PostMapping("/all")
    public String uploadAll() throws IOException {

        searchFilesService.uploadAll();

        return "file has created";
    }
}