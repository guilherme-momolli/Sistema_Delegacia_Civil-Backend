package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.configurations.FileStorageConfig;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Path.of(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Não foi possível criar o diretório onde os arquivos serão armazenados.", ex);
        }
    }

    public String storeFile(MultipartFile file, String subfolder) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (originalFileName.contains("..")) {
                throw new FileStorageException("Nome do arquivo inválido: " + originalFileName);
            }

            String fileName = UUID.randomUUID().toString() + "_" + originalFileName;

            Path destinationDir = this.fileStorageLocation.resolve(subfolder).normalize();
            Files.createDirectories(destinationDir);

            Path targetLocation = destinationDir.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception ex) {
            throw new FileStorageException("Falha ao armazenar o arquivo: " + originalFileName, ex);
        }
    }


    public byte[] getImagem(String fileName, String subFolder) {
        try {
            Path targetLocation = this.fileStorageLocation
                    .resolve("Imagens")
                    .resolve(subFolder)
                    .resolve(fileName)
                    .normalize();

            return Files.readAllBytes(targetLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Não foi possível recuperar o arquivo " + fileName + ". Tente novamente!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName, String subFolder) {
        try {
            Path filePath = this.fileStorageLocation
                    .resolve("Imagens")
                    .resolve(subFolder)
                    .resolve(fileName)
                    .normalize();

            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("Arquivo não encontrado: " + fileName);
            }
        } catch (MalformedURLException | FileNotFoundException e) {
            throw new FileStorageException("Arquivo não encontrado " + fileName, e);
        }
    }


    public void deleteFile(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName);
            Files.deleteIfExists(filePath);
        } catch (Exception ex) {
            throw new FileStorageException("Erro ao excluir o arquivo: " + fileName, ex);
        }
    }

    public String getCaminhoImagem(String fileName) {
        return this.fileStorageLocation.resolve(fileName).toString();
    }
}

