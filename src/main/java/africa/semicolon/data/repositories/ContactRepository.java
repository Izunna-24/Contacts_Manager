package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    Optional<Contact> findContactById(String id);
    Optional<Contact> findContactByPhoneNumber(String phoneNumber);


    Optional<Contact> findContactByEmail(String email);

}
