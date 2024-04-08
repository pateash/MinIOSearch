package in.pateash.MinIOSearchEngine.controller;

import in.pateash.MinIOSearchEngine.dto.WordLocationDTO;
import in.pateash.MinIOSearchEngine.service.FileIndexingService;
import in.pateash.MinIOSearchEngine.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static in.pateash.MinIOSearchEngine.utils.Status.ERROR;
import static in.pateash.MinIOSearchEngine.utils.Status.SUCCESS;

@RestController
@RequestMapping("/api/v1")
public class FileController {
    private final FileIndexingService fileIndexingService;

    @Autowired
    public FileController(FileIndexingService fileIndexingService) {
        this.fileIndexingService = fileIndexingService;
    }

    @CrossOrigin
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<Response> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String uploadFileName = fileIndexingService.indexFile(file);
            return ResponseEntity.ok(
                    new Response(SUCCESS,"File uploaded and indexed successfully: " + uploadFileName, null)
                    );
        } catch (Exception e) {
            // Log the exception details
            return new ResponseEntity<>(
                    new Response(ERROR, "Failed to upload and index the file: " + e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Response> search(@RequestParam(value = "w" , required = false) String word, @RequestParam(value = "f", required = false) String filepath) {
        if(word==null && filepath==null)
            throw new MinIOSearchException("Please provide either  word using ?w=, or filepath using ?f= for search");
        List<WordLocationDTO> searches;
        try {
        if(word!=null && filepath!=null)
            searches = fileIndexingService.searchUsingWordAndFilePath(word,filepath);
            else
            searches = word!=null ? fileIndexingService.search(word) : fileIndexingService.searchUsingFilePath(filepath);

            return ResponseEntity.ok(
                    new Response(SUCCESS, "Total searches found: " +searches.size(), searches)
            );
        } catch (Exception e) {
            throw new MinIOSearchException(e.getMessage());
        }
    }

    @GetMapping("/hello")
    public ResponseEntity<Response> hello(@RequestParam("name") String name) {
        return new ResponseEntity<>(
                new Response(SUCCESS, "hello "+name, null),
                HttpStatus.OK);
    }
    @PostMapping("/helloPost")
    public ResponseEntity<Response> helloPost(@RequestParam("name") String name) {
        return new ResponseEntity<>(
                new Response(SUCCESS, "hello Post "+name, null),
                HttpStatus.OK);
    }
}
