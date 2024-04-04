package africa.semicolon.dataTransferObjects;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class DeleteContactRequest {
    private String password;
    private String phoneNumber;
    private  String id;
}
