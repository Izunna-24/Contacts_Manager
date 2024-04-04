package africa.semicolon.services;

import africa.semicolon.data.models.Contact;
import africa.semicolon.data.repositories.ContactRepository;
import africa.semicolon.dataTransferObjects.CreateContactRequest;
import africa.semicolon.dataTransferObjects.DeleteContactRequest;
import africa.semicolon.dataTransferObjects.EditContactRequest;
import africa.semicolon.exceptions.InvalidPhoneNumberException;
import africa.semicolon.exceptions.NullEmailException;
import africa.semicolon.exceptions.NullPhoneNumberException;
import africa.semicolon.exceptions.NullUsernameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactServicesImpl implements ContactServices{
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> findContactByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public List<Contact> findContactByUsername(String username) {
        return null;
    }

    @Override
    public List<Contact> findContactsById(String id) {
        return null;
    }

    @Override
    public void deleteContact(DeleteContactRequest deleteContactRequest) {

    }

    @Override
    public void editContact(EditContactRequest editContactRequest) {

    }

    @Override
    public void createContact(CreateContactRequest createContactRequest) {
    Contact contact = new Contact();
    fieldValidation(createContactRequest);
    contact.setPhoneNumber(createContactRequest.getPhoneNumber());
    contact.setEmail(createContactRequest.getEmail());
    contact.setUsername(createContactRequest.getUsername());
    contactRepository.save(contact);
    }

    private static void fieldValidation(CreateContactRequest createContactRequest) {
        if (createContactRequest.getPhoneNumber().length() != 11 || !createContactRequest.getPhoneNumber().matches("\\d+"))
            throw new InvalidPhoneNumberException("kindly enter  valid phonenumber!");
        if (createContactRequest.getUsername() == null)
            throw new NullUsernameException("field must be filled");
        if (createContactRequest.getEmail() == null)throw  new NullEmailException("field must be filled");

        //if (createContactRequest.getPhoneNumber() == null){
            //throw new NullPhoneNumberException("field must be filled");
        }
    }

