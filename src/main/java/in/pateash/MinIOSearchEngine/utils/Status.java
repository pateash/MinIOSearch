package in.pateash.MinIOSearchEngine.utils;

import lombok.Getter;

@Getter
public enum Status {
    SUCCESS(200),
    ERROR(400),
    INTERNAL_SERVER_ERROR(500);


    private final int code;
    Status(int code) {
        this.code=code;
    }
}
