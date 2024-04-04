package africa.semicolon.services;

import africa.semicolon.data.models.Contact;
import africa.semicolon.dataTransferObjects.CreateContactRequest;
import africa.semicolon.dataTransferObjects.DeleteContactRequest;
import africa.semicolon.dataTransferObjects.EditContactRequest;

import java.util.List;

public interface ContactServices {
List<Contact> findContactByPhoneNumber(String phoneNumber);
List<Contact> findContactByUsername(String username);
List<Contact> findContactsById(String id);
void deleteContact(DeleteContactRequest deleteContactRequest);
void editContact(EditContactRequest editContactRequest);
void createContact(CreateContactRequest createContactRequest);

}
