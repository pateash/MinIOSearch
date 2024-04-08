package in.pateash.MinIOSearchEngine.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "word")
public class DocumentEntity {
    @Id
    private String id;
    private String content;
}



