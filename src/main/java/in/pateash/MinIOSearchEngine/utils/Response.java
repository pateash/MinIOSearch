package in.pateash.MinIOSearchEngine.utils;

import lombok.*;

@Data
public class Response {
    private int code;
    private String message;
    private Object data; // it will contain anything we want to send back

    public Response(Status status, String message,Object data) {
        this.code = status.getCode();
        this.message = message;
        this.data=data;
    }

}
