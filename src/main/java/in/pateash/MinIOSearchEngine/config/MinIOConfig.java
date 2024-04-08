package in.pateash.MinIOSearchEngine.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class MinIOConfig {

    @Value("${minio.url}")
    private String url;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient MinIOClient() {
        try {
            return MinioClient.builder()
                    .endpoint(url)
                    .credentials(accessKey, secretKey)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing MinIO client: " + e.getMessage());
        }
    }
}
