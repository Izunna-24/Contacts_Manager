package africa.semicolon.services;

import africa.semicolon.data.models.Contact;
import africa.semicolon.dataTransferObjects.CreateContactRequest;
import africa.semicolon.dataTransferObjects.DeleteContactRequest;
import africa.semicolon.dataTransferObjects.EditContactRequest;

public interface ContactServices {
Contact findByPhoneNumber(String phoneNumber);
Contact findByUsername(String username);
Contact findById(String id);
void deleteContact(DeleteContactRequest deleteContactRequest);
void editContact(EditContactRequest editContactRequest);
void createContact(CreateContactRequest createContactRequest);

}
