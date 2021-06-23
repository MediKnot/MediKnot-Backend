package com.hack.azure.mediknot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file.upload-dir}")
    private String baseDir;

    @PostMapping("/upload/{id}")
    public HashMap<String, String> uploadFile(@PathVariable Integer id, @RequestParam(value = "file") MultipartFile file) throws IOException{
        String fileExtension = getFileExtension(file);
        String filename = getRandomString();

        File targetFile = getTargetFile(fileExtension, filename, id);

        file.transferTo(targetFile);
        String uploadedDirectory = targetFile.getAbsolutePath();
        HashMap<String, String> response = new HashMap<>();
        response.put("path", uploadedDirectory);
        return response;
    }

    private String getRandomString() {
        return new Random().nextInt(999999) + "_" + System.currentTimeMillis();
    }

    private File getTargetFile(String fileExtn, String fileName, Integer id) {
        File userDir = new File(baseDir + id);
        if(!userDir.exists()){
            userDir.mkdirs();
        }

        File targetFile = new File(baseDir + id + "/" + fileName + fileExtn);
        return targetFile;
    }

    private String getFileExtension(MultipartFile inFile) {
        return inFile.getOriginalFilename().substring(inFile.getOriginalFilename().lastIndexOf('.'));
    }

}
