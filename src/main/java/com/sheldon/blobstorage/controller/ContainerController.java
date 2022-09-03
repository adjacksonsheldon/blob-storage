package com.sheldon.blobstorage.controller;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.sheldon.blobstorage.config.AppConfig;
import com.sheldon.blobstorage.model.ContainerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("containers")
@RequiredArgsConstructor
public class ContainerController {

    private final AppConfig appConfig;

    @PostMapping
    public String create(@RequestBody ContainerModel containerModel) {

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(appConfig.getConnectStr())
                .buildClient();

        BlobContainerClient containerClient = blobServiceClient.createBlobContainer(containerModel.getNome());

        return "Container has created";
    }

    @GetMapping
    public List<String> findAll() {

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(appConfig.getConnectStr())
                .buildClient();

        return blobServiceClient.listBlobContainers()
                .stream()
                .map(b-> b.getName())
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{nome}")
    public String delete(@PathVariable String nome) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(appConfig.getConnectStr())
                .buildClient();

        blobServiceClient.deleteBlobContainer(nome);

        return "Container has deleted";
    }
}