package africa.semicolon.services;


import africa.semicolon.data.models.Contact;
import africa.semicolon.data.models.User;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dataTransferObjects.requests.*;
import africa.semicolon.dataTransferObjects.responses.SignInResponse;
import africa.semicolon.dataTransferObjects.responses.SignUpResponse;
import africa.semicolon.exceptions.UserExistsException;
import africa.semicolon.exceptions.WrongSignInDetailException;
import africa.semicolon.utilities.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactServices contactServices;
    @Override
    public SignUpResponse signUpWith(SignUpRequest signUpRequest){
        User user = Mapper.map(signUpRequest);
        signUpRequest.setEmail(signUpRequest.getEmail().toLowerCase());
        signUpHelper(signUpRequest);
        userRepository.save(user);
        return Mapper.signUpResponseMap(user);
    }

    private void signUpHelper(SignUpRequest signUpRequest) {
        if (signUpRequest == null) throw new NullPointerException("provide phoneNumber and password");
        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            throw new UserExistsException(String.format("User with this email %s already exists", signUpRequest.getEmail()));
        }
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.findUserByEmail(signInRequest.getEmail());
        loginValidation(signInRequest, user);
        user.setEmail(signInRequest.getEmail());
        user.setLocked(false);
        User savedUser = userRepository.save(user);
        return Mapper.signInResponseMap(savedUser);
    }

    private static void loginValidation(SignInRequest signInRequest, User user) {
        if (user == null) throw new WrongSignInDetailException(String.format("%s is a wrong email, check and try again", signInRequest.getEmail()));
        if (!user.getEmail().equals(signInRequest.getEmail()))
            throw new WrongSignInDetailException(String.format("%s is a wrong email, check and try again", signInRequest.getEmail()));
    }

    @Override
    public void createContactWith(CreateContactRequest createContactRequest) {
        User user = userRepository.findUserByEmail(createContactRequest.getEmail());
        Contact contact = contactServices.createContact(createContactRequest);
        user.getContacts().add(contact);
        userRepository.save(user);
    }

    @Override
    public void deleteContactWith(DeleteContactRequest deleteContactRequest) {
        Contact contact = contactServices.deleteContact(deleteContactRequest);
        User user = userRepository.findUserById(deleteContactRequest.getId());
        user.getContacts().remove(contact);
        userRepository.save(user);
    }

    @Override
    public void editContactWith(EditContactRequest editContactRequest) {
        contactServices.editContact(editContactRequest);
    }

    @Override
    public Contact findContactById(String id) {
        return contactServices.findById(id);
    }

    @Override
    public void signOutWith(SignOutRequest signOutRequest) {
        User user = userRepository.findUserById(signOutRequest.getId());
        user.setPassword(signOutRequest.getPassword());
        user.setLocked(true);
        userRepository.save(user);
    }


}



