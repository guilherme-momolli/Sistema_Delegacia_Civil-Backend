package br.gov.pr.pc.dp.sistema_delegacia_civil.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {

    private String uploadDir;
    private String imageDir;
    private String audioDir;
    private String videoDir;
    private String reportDir;


    public FileStorageConfig() {
    }

    public FileStorageConfig(String uploadDir, String imageDir, String audioDir, String videoDir, String reportDir) {
        this.uploadDir = uploadDir;
        this.imageDir = imageDir;
        this.audioDir = audioDir;
        this.videoDir = videoDir;
        this.reportDir = reportDir;
    }

    public String getUploadDir() {
        return uploadDir != null ? uploadDir : System.getProperty("user.home") + "/Documentos/Uploads";
    }

    public String getImageDir() {
        return imageDir != null ? imageDir : getUploadDir() + "/Images";
    }

    public String getAudioDir() {
        return audioDir != null ? audioDir : getUploadDir() + "/Audios";
    }

    public String getVideoDir() {
        return videoDir != null ? videoDir : getUploadDir() + "/Videos";
    }

    public String getReportDir() {return reportDir != null? reportDir: getReportDir() + "/Relatorios";}

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public void setImageDir(String imageDir) {
        this.imageDir = imageDir;
    }

    public void setAudioDir(String audioDir) {
        this.audioDir = audioDir;
    }

    public void setVideoDir(String videoDir) {
        this.videoDir = videoDir;
    }

    public void setReportDir(String reportDir) {
        this.reportDir = reportDir;
    }
}
