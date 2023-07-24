package com.bidly.bidly.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class HelperMethods {
    
    public static String getFileUrl(MultipartFile file) throws IOException {
        if (file != null) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String directory = "src/main/resources/static/job-images/";
            String filePath = Paths.get(directory, fileName).toString();
            String fileUrl = "job-images/" + fileName;

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(file.getBytes());
            stream.close();
            return fileUrl;
        }
        return null;
        
    }
}
