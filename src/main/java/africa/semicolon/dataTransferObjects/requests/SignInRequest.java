package africa.semicolon.dataTransferObjects.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class SignInRequest {
    private String email;
}
