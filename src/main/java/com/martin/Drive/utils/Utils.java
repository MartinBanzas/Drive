package com.martin.Drive.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class Utils {

    public static String getFileTypeByProbeContentType(String fileName){
        String fileType = "Undetermined";
        final File file = new File(fileName);
        try{
            fileType = Files.probeContentType(file.toPath());
        }
       catch (IOException ioException){
            System.out.println("File type not detected for " + fileName);
        }
        return fileType;
    }





}
