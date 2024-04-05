package africa.semicolon.services;

import africa.semicolon.data.models.Contact;
import africa.semicolon.dataTransferObjects.*;

public interface UserServices {
void signUpWith(SignUpRequest signUpRequest);
void signIn(SignInRequest signInRequest);
void createContact(CreateContactRequest createContactRequest);
void deleteContact(DeleteContactRequest deleteContactRequest);
void editContact(EditContactRequest editContactRequest);
Contact findContactById(String id);

}
