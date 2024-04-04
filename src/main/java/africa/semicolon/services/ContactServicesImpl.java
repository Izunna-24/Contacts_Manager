package africa.semicolon.services;

import africa.semicolon.data.models.Contact;
import africa.semicolon.data.repositories.ContactRepository;
import africa.semicolon.dataTransferObjects.CreateContactRequest;
import africa.semicolon.dataTransferObjects.DeleteContactRequest;
import africa.semicolon.dataTransferObjects.EditContactRequest;
import africa.semicolon.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactServicesImpl implements ContactServices{
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact findByPhoneNumber(String phoneNumber) {
        Optional<Contact> contact = contactRepository.findContactByPhoneNumber(phoneNumber);
        if (contact.isEmpty()) throw new ContactNotFoundException(String.format("contact with phoneNumber %s, does not exist", phoneNumber));
        return contact.get();
    }

    @Override
    public Contact findByUsername(String username) {
        Optional<Contact> contact = contactRepository.findContactByUsername(username);
        if (contact.isEmpty()) throw new ContactNotFoundException(String.format("contact with username %s, is not found!",username));
        return contact.get();
    }

    @Override
    public Contact findById(String id) {
        Optional <Contact> contact = contactRepository.findContactById(id);
        if (contact.isEmpty()) throw new ContactNotFoundException("phoneNumber not in memory");
        return contact.get();
    }

    @Override
    public void deleteContact(DeleteContactRequest deleteContactRequest) {
    Contact contact = findById(deleteContactRequest.getId());
    contactRepository.delete(contact);

    }

    @Override
    public void editContact(EditContactRequest editContactRequest) {
        Contact contact = findByUsername(editContactRequest.getUsername());
        if (!contact.getId().equals(editContactRequest.getUsername())) {
            throw new ContactNotFoundException(String.format("Contact with username %s does not exist", editContactRequest.getUsername()));
        }
            contact.setUsername(editContactRequest.getUsername());
            contact.setPhoneNumber(editContactRequest.getPhoneNumber());
            contact.setEmail(editContactRequest.getEmail());
    // do i set id?
        contactRepository.save(contact);
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

