package africa.semicolon.services;

import africa.semicolon.data.models.Contact;
import africa.semicolon.dataTransferObjects.*;

import java.util.List;

public interface UserServices {
void signUp(SignUpRequest signUpRequest);
void signIn(SignInRequest signInRequest);
void addContact(AddContactRequest addContactRequest);
void deleteContact(DeleteContactRequest deleteContactRequest);
void editContact(EditContactRequest editContactRequest);
Contact findContactByEmail(String email);
Contact findContactByPhoneNumber(String  phoneNumber);
Contact findContactByUsername(String username);
}
