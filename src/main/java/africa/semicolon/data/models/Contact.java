package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("contacts")
public class Contact {
    @Id
    private String id;
    private String phoneNumber;
    private String email;
    private String username;
}
