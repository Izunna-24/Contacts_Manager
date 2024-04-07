package africa.semicolon.dataTransferObjects.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class SignUpRequest {
private String email;
private String phoneNumber;
private String password;
}
