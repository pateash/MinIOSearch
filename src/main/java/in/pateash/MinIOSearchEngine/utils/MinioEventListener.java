package in.pateash.MinIOSearchEngine.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.pateash.MinIOSearchEngine.service.FileUploadService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MinioEventListener {

    @Autowired
    FileUploadService fileUploadService;

    @RabbitListener(queues = "minioQueue")
    public void receiveMessage(String message) {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
        try {
            MinioEvent event = mapper.readValue(message, MinioEvent.class);

            System.out.println("Event Name: " + event.getEventName());
            String fileUrl = event.getKey();
            System.out.println("fileUrl: " + fileUrl);
            fileUploadService.indexFile(fileUrl);
            System.out.println("==========================================\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
