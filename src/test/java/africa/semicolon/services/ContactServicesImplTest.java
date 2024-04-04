package africa.semicolon.services;

import africa.semicolon.data.repositories.ContactRepository;
import africa.semicolon.dataTransferObjects.CreateContactRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ContactServicesImplTest {
    @Autowired
    private ContactServices contactServices;

    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    public void provided(){contactRepository.deleteAll();}

    @Test
    public void contactsCanBeCreatedTest () {
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setPhoneNumber("07076492345");
        createContactRequest.setUsername("Joe");
        createContactRequest.setEmail("joecool@gmail.com");
        contactServices.createContact(createContactRequest);
        assertEquals(1,contactRepository.count());
    }
}