package in.pateash.MinIOSearchEngine.utils;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MinioEventListener {

    @RabbitListener(queues = "yourQueueName")
    public void receiveMessage(String message) {
        // Process the message/event
        System.out.println("Received MinIO event: " + message);
    }
}
