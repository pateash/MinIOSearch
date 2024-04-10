package in.pateash.MinIOSearchEngine.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class MinioEvent {
    @JsonProperty("EventName")
    private String eventName;
    @JsonProperty("Key")
    private String key;
}
