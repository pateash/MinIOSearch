package in.pateash.MinIOSearchEngine.service;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileProcessor {

    public List<String> tokenizeString(String text) {
        List<String> result = new ArrayList<>();
        try (Analyzer analyzer = new StandardAnalyzer();
             TokenStream tokenStream = analyzer.tokenStream(null, new StringReader(text))) {

            CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();

            while (tokenStream.incrementToken()) {
                result.add(attr.toString());
            }

            tokenStream.end();
        } catch (Exception e) {
            e.printStackTrace(); // Proper error handling goes here
        }
        return result;
    }

    public String preProcess(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
//        String uploadFileName = String.format("%s_%s", fileName, now.format(formatter));
        String uploadFileName = fileName;
        System.out.println("Upload file name :" + uploadFileName);
        return uploadFileName;
    }
}
