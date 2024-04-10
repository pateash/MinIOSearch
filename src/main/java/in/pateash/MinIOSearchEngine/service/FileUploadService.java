package in.pateash.MinIOSearchEngine.service;

import in.pateash.MinIOSearchEngine.dto.WordLocationDTO;
import in.pateash.MinIOSearchEngine.entity.WordLocationEntity;
import in.pateash.MinIOSearchEngine.repository.WordLocationRepository;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.Math.min;

@Service
public class FileUploadService {

    private final MinioClient minioClient;
    private final WordLocationRepository wordLocationRepository;

    private final FileProcessor fileProcessor;

    // Constructor with @Autowired MinioClient and WordLocationRepository
    @Value("${minio.bucket-name}")
    String bucketName;

    @Autowired
    public FileUploadService(MinioClient minioClient, WordLocationRepository wordLocationRepository, FileProcessor fileProcessor) {
        this.minioClient = minioClient;
        this.wordLocationRepository = wordLocationRepository;
        this.fileProcessor = fileProcessor;
    }
    private String extractTextUsingTika(MultipartFile file) throws Exception {
        Tika tika = new Tika();
        try (InputStream input = file.getInputStream()) {
            return tika.parseToString(input);
        }
    }
    public String indexFile(MultipartFile multipartFile) throws Exception {
//        String fileName = multipartFile.getOriginalFilename();
        String uploadFileName = fileProcessor.preProcess(multipartFile);
        // Upload file to MinIO
        String fileUrl = uploadFileToMinIO(multipartFile, bucketName, uploadFileName);
        // Process file and index words
        indexWords(multipartFile, bucketName, fileUrl, uploadFileName);

        return uploadFileName;
    }

    private String uploadFileToMinIO(MultipartFile multipartFile, String bucketName, String fileName) throws Exception {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                        .contentType(multipartFile.getContentType())
                        .build()
        );
        String filePath = String.format("/%s/%s", bucketName, fileName);// MinIO URL path
        System.out.println("File Uploaded to Minio: " + filePath);
        return filePath;
    }

    private void indexWords(MultipartFile multipartFile, String bucketName, String fileUrl, String fileName) throws Exception {
//        String content = new String(multipartFile.getBytes());
        String content = extractTextUsingTika(multipartFile);
//        String[] words = content.split("[]+"); // Split by whitespace
        /*
         * for better tokenization we are using Lucene, this removes spaces, punctuations and prepositions etc. like this/the and retains meaningful words
         * */
//        System.out.println(content.substring(0,Math.min(100,content.length())));
        List<String> words = fileProcessor.tokenizeString(content);
        Set<String> uniqueWords = new HashSet<>(fileProcessor.tokenizeString(content));
        System.out.println("Indexing file, Total tokens: " + words.size());
        System.out.println("Indexing file, Unique tokens: " + uniqueWords.size() + "\n First 10 tokens");
        System.out.println(uniqueWords.stream().toList().subList(0, min(uniqueWords.size(),10)));

        List<WordLocationEntity> wordLocationEntityList = uniqueWords.stream().map(word ->
                new WordLocationEntity(UUID.randomUUID().toString(), word, bucketName, fileUrl, fileName)
        ).toList();
        wordLocationRepository.saveAll(wordLocationEntityList);
    }

    public List<WordLocationDTO> search(String word) {
        System.out.println("Searching for :" + word);
        List<WordLocationEntity> entities = wordLocationRepository.findAllByWordContainsIgnoreCase(word);
        return streamToDtoList(entities);
    }

    public List<WordLocationDTO> searchUsingFilePath(String filepath) {
        System.out.println("Searching for filepath :" + filepath);
        List<WordLocationEntity> entities = wordLocationRepository.findAllByMinioFilePathContainsIgnoreCase(filepath);
        return streamToDtoList(entities);
    }

    public List<WordLocationDTO> searchUsingWordAndFilePath(String word, String filepath) {
        System.out.printf("Searching for word: %s, filepath :%s%n", word, filepath);
        List<WordLocationEntity> entities = wordLocationRepository.findAllByWordContainsIgnoreCaseAndMinioFilePathContainingIgnoreCase(word, filepath);
        return streamToDtoList(entities);
    }

    private List<WordLocationDTO> streamToDtoList(List<WordLocationEntity> entities) {
        return entities.stream()
                .map(entity -> new WordLocationDTO(entity.getWord(), entity.getMinioFilePath(), entity.getFileName()))
                .collect(Collectors.toList());
    }
}
