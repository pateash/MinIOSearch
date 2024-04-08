package in.pateash.MinIOSearchEngine.controller;

import in.pateash.MinIOSearchEngine.utils.Response;
import in.pateash.MinIOSearchEngine.utils.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = ProductNotfoundException.class)
    public ResponseEntity<Object> exception(ProductNotfoundException exception) {
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MinIOFileUploadException.class)
    public ResponseEntity<Object> exception(MinIOFileUploadException exception) {
        return new ResponseEntity<>(
                new Response(Status.INTERNAL_SERVER_ERROR, "Error Uploading file: " + exception.getMessage(), null),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = MinIOSearchException.class)
    public ResponseEntity<Object> exception(MinIOSearchException exception) {
        return new ResponseEntity<>(
                new Response(Status.INTERNAL_SERVER_ERROR, "Error Searching file: " + exception.getMessage(), null),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

class ProductNotfoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
}

class MinIOFileUploadException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public MinIOFileUploadException(String message) {
        super(message);
    }
}

class MinIOSearchException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public MinIOSearchException(String message) {
        super(message);
    }
}
