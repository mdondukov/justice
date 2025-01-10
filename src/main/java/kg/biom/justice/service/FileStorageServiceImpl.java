package kg.biom.justice.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${content.base.url}")
    private String baseUrl;

    @Value("${content.base.path}")
    private String basePath;

    @SneakyThrows
    @Override
    public URI save(MultipartFile file, String relativePath) {
        String extension = getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFileName = "%s.%s".formatted(UUID.randomUUID(), extension);
        Path filePath = Paths.get(basePath, relativePath, uniqueFileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());
        return URI.create("%s/%s/%s".formatted(baseUrl, relativePath, uniqueFileName));
    }

    @SneakyThrows
    @Override
    public boolean deleteByUri(URI fileUri, String relativePath) {
        String filename = Paths.get(fileUri.getPath()).getFileName().toString();
        Path filePath = Paths.get(basePath, relativePath, filename);
        return Files.deleteIfExists(filePath);
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
