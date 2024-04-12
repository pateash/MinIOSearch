package in.pateash.MinIOSearchEngine.utils;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMinioEventListener {

    @KafkaListener(topics = "minio-events", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Received MinIO Event: " + message);
        // Further processing can be done here
    }
}
