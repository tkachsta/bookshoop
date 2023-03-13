package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.model.entities.Bookfile2Type.Bookfile2Type;
import com.example.MyBookShopApp.model.entities.Bookfile2Type.Bookfile2TypeRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@Service
public class ResourceStorage {

    private final Bookfile2TypeRepository bookfile2TypeRepository;

    @Value("${upload.path}")
    String uploadPath;
    @Value("${download.path}")
    String downloadPath;

    @Autowired
    public ResourceStorage(Bookfile2TypeRepository bookfile2TypeRepository) {
        this.bookfile2TypeRepository = bookfile2TypeRepository;
    }

    public String saveNewBookImage(MultipartFile file, String slug) throws IOException {

        String resourceURI = null;

        if(!file.isEmpty()) {
            if(!new File(uploadPath).exists()) {
                Files.createDirectories(Paths.get(uploadPath));
                Logger.getLogger(this.getClass().getSimpleName()).info("Created image folder in " + uploadPath);
            }
            String fileName = slug + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            Path path = Paths.get(uploadPath, fileName);
            resourceURI = "/book-covers/"+fileName;
            file.transferTo(path);
            Logger.getLogger(this.getClass().getSimpleName()).info(fileName + " uploaded OK!");
        }
        return resourceURI;
    }

    public Path getBookFilePath(String hash) {
        Bookfile2Type BF2T = bookfile2TypeRepository.findByHashIs(hash);
        return Paths.get(BF2T.getFileEntity().getPath()+BF2T.getFileType().getName());
    }

    public MediaType getBookFileMime(String hash) {
        Bookfile2Type BF2T = bookfile2TypeRepository.findByHashIs(hash);
        String mimeType =
                URLConnection.guessContentTypeFromName(
                        Paths.get(BF2T.getFileEntity().getPath()+BF2T.getFileType().getName()).toString());
        if (mimeType != null) {
            return MediaType.parseMediaType(mimeType);
        } else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public byte[] getBookFileByteArray(String hash) throws IOException {
        Bookfile2Type BF2T = bookfile2TypeRepository.findByHashIs(hash);
        Path path = Paths.get(downloadPath, BF2T.getFileEntity().getPath()+BF2T.getFileType().getName());
        return Files.readAllBytes(path);
    }
}
