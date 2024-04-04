package africa.semicolon.dataTransferObjects;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CreateContactRequest {
    private String phoneNumber;
    private String email;
    private String username;
}
