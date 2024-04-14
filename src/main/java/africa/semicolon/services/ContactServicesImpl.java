package africa.semicolon.services;

import africa.semicolon.data.models.Contact;
import africa.semicolon.data.repositories.ContactRepository;
import africa.semicolon.dataTransferObjects.requests.CreateContactRequest;
import africa.semicolon.dataTransferObjects.requests.DeleteContactRequest;
import africa.semicolon.dataTransferObjects.requests.EditContactRequest;
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
    public Contact findById(String id) {
        Optional <Contact> contact = contactRepository.findContactById(id);
        if (contact.isEmpty()) throw new ContactNotFoundException("phoneNumber not in memory");
        return contact.get();
    }

    @Override
    public Contact deleteContact(DeleteContactRequest deleteContactRequest) {
    Contact contact = findById(deleteContactRequest.getId());
    contactRepository.delete(contact);
        return contact;
    }

    @Override
    public void editContact(EditContactRequest editContactRequest) {
        Contact contact = findById(editContactRequest.getId());
            //contact.setContactName(editContactRequest.getUsername());
            contact.setPhoneNumber(editContactRequest.getPhoneNumber());
            contact.setEmail(editContactRequest.getEmail());
        contactRepository.save(contact);
    }

    @Override
    public Contact createContact(CreateContactRequest createContactRequest) {
    Contact contact = new Contact();
    fieldValidation(createContactRequest);
    contact.setPhoneNumber(createContactRequest.getPhoneNumber());
    contact.setEmail(createContactRequest.getEmail());
    //contact.setContactName(createContactRequest.getUsername());
    return contactRepository.save(contact);
    }

    private static void fieldValidation(CreateContactRequest createContactRequest) {
        if (createContactRequest.getPhoneNumber().length() != 11 || !createContactRequest.getPhoneNumber().matches("\\d+"))
            throw new InvalidPhoneNumberException("kindly enter  valid phonenumber!");
        //if (createContactRequest.getUsername() == null)
            //throw new NullUsernameException("field must be filled");
        if (createContactRequest.getEmail() == null)throw  new NullEmailException("field must be filled");

        //if (createContactRequest.getPhoneNumber() == null){
            //throw new NullPhoneNumberException("field must be filled");
        }
    }

