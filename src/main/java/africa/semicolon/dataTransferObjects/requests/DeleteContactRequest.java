package africa.semicolon.dataTransferObjects.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class DeleteContactRequest {
    private String password;
    private String phoneNumber;
    @Id
    private  String id;
}
