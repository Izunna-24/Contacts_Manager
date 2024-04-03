package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataMongoTest
class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void testThatContactsAreSavedInThisRepository(){
        contactRepository.deleteAll();
        Contact contact = new Contact();
        contactRepository.save(contact);
        assertEquals(1,contactRepository.count());

    }

}