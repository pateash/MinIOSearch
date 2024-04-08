package in.pateash.MinIOSearchEngine.repository;

import in.pateash.MinIOSearchEngine.entity.WordLocationEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordLocationRepository extends ElasticsearchRepository<WordLocationEntity, String> {
    List<WordLocationEntity> findAllByWordContainsIgnoreCase(String word);

//    @Query("{\"bool\": {\"should\": [{\"match_phrase\": {\"words\": {\"query\": \"?0\", \"analyzer\": \"standard\"}}}]}}")
//    List<WordLocationEntity> findByMatchingWordsIgnoreCase(String word);

    List<WordLocationEntity> findAllByMinioFilePathContainsIgnoreCase(String filePath);

    List<WordLocationEntity> findAllByWordContainsIgnoreCaseAndMinioFilePathContainingIgnoreCase(String word, String filePath);
}


