package com.martin.Drive.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements  FileStorageService {

    private final Path root = Paths.get("./uploads");

    @Override
    public void init() {

        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("The directory can't be initiated");
        }

    }

    @Override
    public void save(MultipartFile file, String uniqueFileName) {

        try {

            Files.copy(file.getInputStream(), this.root.resolve(uniqueFileName));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("File with that name already exists");
            }
            throw new RuntimeException(e.getMessage());
        }

    }


    @Override
    public Resource load(String filename) {
     try {
         Path file = root.resolve(filename);
         Resource resource = new UrlResource(file.toUri());

         if (resource.exists() || resource.isReadable()) {
             return resource;
         } else {
             throw new RuntimeException("Couldn't read file");
         }

     } catch (MalformedURLException e) {
     throw new RuntimeException("Error: " + e.getMessage());

     }
    }

    @Override
    public boolean delete(String filename) {

        try {
            Path file = root.resolve(filename);
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Error: " +e.getMessage());
        }


    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }


    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files");
        }
    }
}
