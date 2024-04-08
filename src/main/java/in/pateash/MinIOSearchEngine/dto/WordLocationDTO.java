package in.pateash.MinIOSearchEngine.dto;

import in.pateash.MinIOSearchEngine.entity.WordLocationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class WordLocationDTO {
    private String word;
    private String fileName;
    private String minioFilePath;

    public WordLocationDTO(WordLocationEntity wordLocationEntity) {
        this.word = wordLocationEntity.getWord();
        this.minioFilePath = wordLocationEntity.getMinioFilePath();
        this.fileName=wordLocationEntity.getFileName();
    }
}
