package africa.semicolon.services;


import africa.semicolon.data.models.Contact;
import africa.semicolon.data.repositories.ContactRepository;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dataTransferObjects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void signUp(SignUpRequest signUpRequest) {

    }

    @Override
    public void signIn(SignInRequest signInRequest) {

    }

    @Override
    public void createContact(CreateContactRequest createContactRequest) {

    }

    @Override
    public void deleteContact(DeleteContactRequest deleteContactRequest) {

    }

    @Override
    public void editContact(EditContactRequest editContactRequest) {

    }

    @Override
    public Contact findContactByEmail(String email) {
        return null;
    }

    @Override
    public Contact findContactByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public Contact findContactByUsername(String username) {
        return null;
    }
}
