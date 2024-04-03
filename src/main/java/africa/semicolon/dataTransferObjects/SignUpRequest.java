package africa.semicolon.dataTransferObjects;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SignUpRequest {
private String email;
private String phoneNumber;
private String password;
}
