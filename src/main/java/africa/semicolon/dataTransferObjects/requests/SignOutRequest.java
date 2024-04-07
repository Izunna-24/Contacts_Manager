package africa.semicolon.dataTransferObjects.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class SignOutRequest {
    private String id;
    private String password;
}
