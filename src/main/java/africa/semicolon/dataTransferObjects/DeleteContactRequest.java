package africa.semicolon.dataTransferObjects;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class DeleteContactRequest {
    private String password;
    private String phoneNumber;
    @Id
    private  String id;
}
