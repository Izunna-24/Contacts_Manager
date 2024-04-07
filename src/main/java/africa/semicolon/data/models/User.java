package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Document("Users")
public class User {
    private String email;
    @Id
    private String id;
    private String username;
    private String password;
    private String phoneNumber;
    private boolean isLocked = true;

    @DBRef
    private List<Contact> contacts = new ArrayList<>();
}
