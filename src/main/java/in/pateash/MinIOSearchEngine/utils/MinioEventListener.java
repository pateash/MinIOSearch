package in.pateash.MinIOSearchEngine.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MinioEventListener {

    @RabbitListener(queues = "minioQueue")
    public void receiveMessage(String message) {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
        try {
            MinioEvent event = mapper.readValue(message, MinioEvent.class);
            System.out.println("Event Name: " + event.getEventName());
            System.out.println("Key: " + event.getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
