package kg.biom.justice.service;

import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

public interface FileStorageService {

    URI save(MultipartFile file, String relativePath);

    boolean deleteByUri(URI fileUri, String relativePath);
}
