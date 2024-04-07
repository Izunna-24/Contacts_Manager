package africa.semicolon.dataTransferObjects.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class EditContactRequest {
    @Id
    private String id;
    private String PhoneNumber;
    private String email;
    private String username;

}
