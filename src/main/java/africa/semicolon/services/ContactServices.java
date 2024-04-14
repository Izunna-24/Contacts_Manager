package africa.semicolon.services;

import africa.semicolon.data.models.Contact;
import africa.semicolon.dataTransferObjects.requests.CreateContactRequest;
import africa.semicolon.dataTransferObjects.requests.DeleteContactRequest;
import africa.semicolon.dataTransferObjects.requests.EditContactRequest;

public interface ContactServices {
Contact findByPhoneNumber(String phoneNumber);


Contact findById(String id);
Contact deleteContact(DeleteContactRequest deleteContactRequest);
void editContact(EditContactRequest editContactRequest);
Contact createContact(CreateContactRequest createContactRequest);

}
