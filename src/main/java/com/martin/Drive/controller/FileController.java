package com.martin.Drive.controller;

import com.martin.Drive.entity.Fichero;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.martin.Drive.service.FicheroService;
import com.martin.Drive.service.FileStorageService;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.martin.Drive.utils.Utils.getFileTypeByProbeContentType;


@Controller
@RequestMapping("/drive")
@CrossOrigin()
public class FileController {

    private final Path root = Paths.get("./uploads");

    @Autowired
    private FicheroService ficheroService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/files")
    public String fileList(Model theModel) {
       List<Fichero> archivos = ficheroService.findAll();
        theModel.addAttribute("files", archivos);
        return "drive/files";
    }

    @PostMapping("/new/upload")
    @Transactional
    public ResponseEntity<String> uploadFile(Model model,
                             @RequestParam("file")MultipartFile file) {

        String message="";
        Fichero archivo = new Fichero();
        String originalFileName = file.getOriginalFilename();
        String uniqueFileName = generateUniqueFileName(originalFileName);

        archivo.setRuta(uniqueFileName);
        String tipo = getFileTypeByProbeContentType(uniqueFileName);
        Long size = file.getSize();
        archivo.setSize(size);
        archivo.setTipo(tipo);
        archivo.setFCreacion(LocalDateTime.now());

        ficheroService.save(archivo);

        try {

            fileStorageService.save(file, uniqueFileName);
            message = "Se ha guardado el fichero con éxito: " + uniqueFileName;
            model.addAttribute("message", message);

        } catch (Exception e) {
            message = "No se ha podido guardar el fichero: " + uniqueFileName + ". Error: " + e.getMessage();
            model.addAttribute("message", message);
        }

        return ResponseEntity.ok("Fichero subido correctamente");
    }

    private String generateUniqueFileName(String originalFileName) {
        String baseName;
        String extension = "";
        int lastDotIndex = originalFileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < originalFileName.length() - 1) {
            baseName = originalFileName.substring(0, lastDotIndex);
            extension = originalFileName.substring(lastDotIndex + 1);
        } else {

            baseName = originalFileName+extension;
        }
        String uniqueFileName = baseName;
        int count = 1;
        while (Files.exists(this.root.resolve(uniqueFileName))) {
            uniqueFileName = baseName + "("+count+ ")";
            count++;
        }

        return uniqueFileName+"."+extension;
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        Fichero archivo = ficheroService.findById(id);
        Resource file = fileStorageService.load(archivo.getRuta());
        fileStorageService.delete(file.getFilename());
        ficheroService.deleteById(id);

        return ResponseEntity.ok("El fichero con ID " + id + " se ha eliminado correctamente");
    }

    @PatchMapping("/rename/{id}")
    @Transactional
    public ResponseEntity<String> renameVariable(@PathVariable Long id, @RequestParam("newName") String newName) throws IOException {
        Fichero archivo = ficheroService.findById(id);

        if (archivo == null) {
            return ResponseEntity.notFound().build();
        }
        Path oldPath = root.resolve(archivo.getRuta());
        Path newPath = root.resolve(newName);
        archivo.setRuta(newName);
        ficheroService.save(archivo);
        Files.move(oldPath, newPath);

        return ResponseEntity.ok("El fichero con ID " + id + " se ha renombrado correctamente a " + newName);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable Long id) {

        Fichero fichero = ficheroService.findById(id);
        Resource file = fileStorageService.load(fichero.getRuta());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}

