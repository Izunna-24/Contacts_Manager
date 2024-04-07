package africa.semicolon.services;

import africa.semicolon.data.models.Contact;
import africa.semicolon.dataTransferObjects.requests.*;
import africa.semicolon.dataTransferObjects.responses.SignInResponse;
import africa.semicolon.dataTransferObjects.responses.SignUpResponse;

public interface UserServices {
SignUpResponse signUpWith(SignUpRequest signUpRequest);
SignInResponse signIn(SignInRequest signInRequest);
void createContactWith(CreateContactRequest createContactRequest);
void deleteContactWith(DeleteContactRequest deleteContactRequest);
void editContactWith(EditContactRequest editContactRequest);
Contact findContactById(String id);
void signOutWith(SignOutRequest signOutRequest);

}
