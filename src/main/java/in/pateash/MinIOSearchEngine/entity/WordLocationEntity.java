package in.pateash.MinIOSearchEngine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data @AllArgsConstructor
@Document(indexName = "file-words" )
public class WordLocationEntity {
    @Id
    private String id;

    private String word;
    private String bucketName;
    private String fileName;
    private String minioFilePath;
}
