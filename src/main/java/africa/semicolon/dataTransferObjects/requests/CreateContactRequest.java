package africa.semicolon.dataTransferObjects.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class CreateContactRequest {
    private String phoneNumber;
    private String email;
    //private String username;
}
