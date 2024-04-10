package in.pateash.MinIOSearchEngine.controller;

import in.pateash.MinIOSearchEngine.dto.WordLocationDTO;
import in.pateash.MinIOSearchEngine.service.FileUploadService;
import in.pateash.MinIOSearchEngine.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static in.pateash.MinIOSearchEngine.utils.Status.ERROR;
import static in.pateash.MinIOSearchEngine.utils.Status.SUCCESS;

@RestController
@Tag(name = "Search API")
@RequestMapping("/api/v1")
public class SearchController {
    private final FileUploadService fileUploadService;

    @Autowired
    public SearchController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @CrossOrigin
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<Response> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String uploadFileName = fileUploadService.uploadFile(file);
            return ResponseEntity.ok(
                    new Response(SUCCESS, "File uploaded and indexed successfully: " + uploadFileName, null)
            );
        } catch (Exception e) {
            // Log the exception details
            return new ResponseEntity<>(
                    new Response(ERROR, "Failed to upload and index the file: " + e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Operation(summary = "Search a file in Minio using word(?w=) or file location(?f=)", description = "Returns list of matched files with their location in Minio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully searched"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/search")
    public ResponseEntity<Response> search(@RequestParam(value = "w", required = false) String word, @RequestParam(value = "f", required = false) String filepath) {
        if (word == null && filepath == null)
            throw new MinIOSearchException("Please provide either  word using ?w=, or filepath using ?f= for search");
        List<WordLocationDTO> searches;
        try {
            if (word != null && filepath != null)
                searches = fileUploadService.searchUsingWordAndFilePath(word, filepath);
            else
                searches = word != null ? fileUploadService.search(word) : fileUploadService.searchUsingFilePath(filepath);

            return ResponseEntity.ok(
                    new Response(SUCCESS, "Total searches found: " + searches.size(), searches)
            );
        } catch (Exception e) {
            throw new MinIOSearchException(e.getMessage());
        }
    }

    @Operation(summary = "Hello world, Test API")
    @GetMapping("/hello")
    public ResponseEntity<Response> hello(@RequestParam("name") String name) {
        return new ResponseEntity<>(
                new Response(SUCCESS, "hello " + name, null),
                HttpStatus.OK);
    }
//    @PostMapping("/helloPost")
//    public ResponseEntity<Response> helloPost(@RequestParam("name") String name) {
//        return new ResponseEntity<>(
//                new Response(SUCCESS, "hello Post "+name, null),
//                HttpStatus.OK);
//    }
}
