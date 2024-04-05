package africa.semicolon.services;


import africa.semicolon.data.models.Contact;
import africa.semicolon.data.models.User;
import africa.semicolon.data.repositories.ContactRepository;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dataTransferObjects.*;
import africa.semicolon.exceptions.WrongSignInDetailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.utilities.Mapper.map;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;
   
    @Autowired
    private ContactServices contactServices;
    @Override
    public void signUpWith(SignUpRequest signUpRequest){
    User user = map(signUpRequest);
    userRepository.save(user);
    }

    @Override
    public void signIn(SignInRequest signInRequest) {
        User user = userRepository.findUserByEmail(signInRequest.getEmail());
        loginValidation(signInRequest, user);
        user.setEmail(signInRequest.getEmail());
        user.setLocked(false);
        userRepository.save(user);

    }

    private static void loginValidation(SignInRequest signInRequest, User user) {
        if (user == null) throw new WrongSignInDetailException(String.format("%s is a wrong email, check and try again", signInRequest.getEmail()));
        if (!user.getEmail().equals(signInRequest.getEmail()))
            throw new WrongSignInDetailException(String.format("%s is a wrong email, check and try again", signInRequest.getEmail()));
    }

    @Override
    public void createContact(CreateContactRequest createContactRequest) {
     Contact contact = contactServices.createContact(createContactRequest);
     User user = userRepository.findUserByUsername(createContactRequest.getUsername());
     user.getContacts().add(contact);
     userRepository.save(user);
    }

    @Override
    public void deleteContact(DeleteContactRequest deleteContactRequest) {
        Contact contact = contactServices.deleteContact(deleteContactRequest);
        User user = userRepository.findUserById(deleteContactRequest.getId());
        user.getContacts().remove(contact);
        userRepository.save(user);
    }

    @Override
    public void editContact(EditContactRequest editContactRequest) {
        contactServices.editContact(editContactRequest);
    }

    @Override
    public Contact findContactById(String id) {
        return contactServices.findById(id);
    }


}



