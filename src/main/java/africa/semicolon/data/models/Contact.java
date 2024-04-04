package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("contacts")
public class Contact {
    @Id
    private String id;
    private String phoneNumber;
    private String email;
    private String username;

    //@DBRef
    //private List<Contact> contactList;
}
