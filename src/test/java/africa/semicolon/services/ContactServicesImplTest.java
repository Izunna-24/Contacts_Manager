package africa.semicolon.services;

import africa.semicolon.data.models.Contact;
import africa.semicolon.data.repositories.ContactRepository;
import africa.semicolon.dataTransferObjects.requests.CreateContactRequest;
import africa.semicolon.dataTransferObjects.requests.DeleteContactRequest;
import africa.semicolon.dataTransferObjects.requests.EditContactRequest;
import africa.semicolon.exceptions.ContactManagerExceptions;
import africa.semicolon.exceptions.ContactNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ContactServicesImplTest {
    @Autowired
    private ContactServices contactServices;

    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    public void provided() {
        contactRepository.deleteAll();
    }

    @Test
    public void contactsCanBeCreatedTest() {
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setPhoneNumber("07076492345");
        createContactRequest.setUsername("Joe");
        createContactRequest.setEmail("joecool@gmail.com");
        contactServices.createContact(createContactRequest);
        assertEquals(1, contactRepository.count());
    }

    @Test
    public void creatingContactWithSameDetail_throwsContactExistsExceptionTest() {
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setPhoneNumber("07076492345");
        createContactRequest.setUsername("Joe");
        createContactRequest.setEmail("joecool@gmail.com");
        contactServices.createContact(createContactRequest);
        assertEquals(1, contactRepository.count());

        try {
            contactServices.createContact(createContactRequest);
        } catch (ContactManagerExceptions error) {
            assertEquals(error.getMessage(), "User exists");
        }
    }

    @Test
    public void createdContact_canBeEdited_test() {
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setPhoneNumber("07076492345");
        createContactRequest.setUsername("Joe");
        createContactRequest.setEmail("joecool@gmail.com");

        contactServices.createContact(createContactRequest);
        assertEquals(1, contactRepository.count());

        String id = contactRepository.findAll().getFirst().getId();

        EditContactRequest editContactRequest = new EditContactRequest();
        editContactRequest.setId(id);
        editContactRequest.setPhoneNumber("08044934509");
        editContactRequest.setEmail("pokerclub@yahoo.com");
        editContactRequest.setUsername("Canon");
        contactServices.editContact(editContactRequest);

        Optional<Contact> contact = contactRepository.findContactById(id);
        assertTrue(contact.isPresent());

        assertEquals("08044934509", contact.get().getPhoneNumber());
        assertEquals("pokerclub@yahoo.com", contact.get().getEmail());
        //assertEquals("Canon",contact.get().getUsername());
    }

    @Test
    public void createdContact_canBeDeleted_test() {
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setPhoneNumber("07076492345");
        createContactRequest.setUsername("Joe");
        createContactRequest.setEmail("joecool@gmail.com");

        contactServices.createContact(createContactRequest);
        assertEquals(1, contactRepository.count());

        DeleteContactRequest deleteContactRequest = new DeleteContactRequest();
        String id = contactRepository.findAll().getFirst().getId();


        deleteContactRequest.setId(id);
        deleteContactRequest.setPassword("password");
        deleteContactRequest.setPhoneNumber("07076492345");
        contactServices.deleteContact(deleteContactRequest);

        assertEquals(0, contactRepository.count());
    }

    @Test
    public void createTwoContact_deleteOne_OneRemainsTest() {
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setPhoneNumber("07076492345");
        createContactRequest.setUsername("Joe");
        createContactRequest.setEmail("joecool@gmail.com");

        contactServices.createContact(createContactRequest);

        createContactRequest.setPhoneNumber("09076492345");
        createContactRequest.setUsername("Mabel");
        createContactRequest.setEmail("Vantistour@gmail.com");

        contactServices.createContact(createContactRequest);
        
        assertEquals(2, contactRepository.count());

        DeleteContactRequest deleteContactRequest = new DeleteContactRequest();
        String id = contactRepository.findAll().get(1).getId();


        deleteContactRequest.setId(id);
        //deleteContactDTO may not be neccesary afterall
        //deleteContactRequest.setPassword("password");
        //deleteContactRequest.setPhoneNumber("07076492345");
        contactServices.deleteContact(deleteContactRequest);

        assertEquals(1, contactRepository.count());
    }

    @Test
    public void findContactByPhoneNumber_contactIsFound(){
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setPhoneNumber("07076492345");
        createContactRequest.setUsername("Joe");
        createContactRequest.setEmail("joecool@gmail.com");

        contactServices.createContact(createContactRequest);

        createContactRequest.setPhoneNumber("09076492345");
        createContactRequest.setUsername("Mabel");
        createContactRequest.setEmail("Vantistour@gmail.com");

        contactServices.createContact(createContactRequest);

        assertEquals(2, contactRepository.count());

        Contact foundContact = contactServices.findByPhoneNumber("09076492345");
        assertEquals("Mabel", foundContact.getContactName());
        assertEquals("Vantistour@gmail.com", foundContact.getEmail());
    }

    @Test
    public void nullContactWhileFindingByPhoneNumber_throwsExceptionTest(){
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setPhoneNumber("07076492345");
        createContactRequest.setUsername("Joe");
        createContactRequest.setEmail("joecool@gmail.com");

        contactServices.createContact(createContactRequest);

        createContactRequest.setPhoneNumber("09076492345");
        createContactRequest.setUsername("Mabel");
        createContactRequest.setEmail("Vantistour@gmail.com");

        contactServices.createContact(createContactRequest);

        assertEquals(2, contactRepository.count());

        assertThrows(ContactNotFoundException.class,()-> contactServices.findByPhoneNumber(" "));
    }
}
