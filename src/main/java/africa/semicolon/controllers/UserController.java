package africa.semicolon.controllers;

import africa.semicolon.dataTransferObjects.requests.*;
import africa.semicolon.dataTransferObjects.responses.ApiResponse;
import africa.semicolon.exceptions.ContactManagerExceptions;
import africa.semicolon.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserServices userServices;
    @PostMapping("/sign_up")
    public ResponseEntity<?> signUpWith(@RequestBody SignUpRequest signUpRequest){
        try {
            var result = userServices.signUpWith(signUpRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), HttpStatus.CREATED);
        }catch (ContactManagerExceptions error){
            return new ResponseEntity<>(new ApiResponse(false, error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sign_in")
    public String signIn(@RequestBody SignInRequest signInRequest){
        try {
            userServices.signIn(signInRequest);
            return String.format("%s is signed in",signInRequest.getEmail());
        }catch (ContactManagerExceptions error){
            return error.getMessage();
        }
    }
    @GetMapping("/add_contact")
    private String addContact(@RequestBody CreateContactRequest createContactRequest){
        try{
            userServices.createContactWith(createContactRequest);
            return String.format("%s's contact has been added successfully",createContactRequest.getUsername());
        }catch (ContactManagerExceptions error){
            return error.getMessage();
        }
    }

    @PatchMapping("/edit_contact")
    private String editContact(@RequestBody EditContactRequest editContactRequest){
        try{
            userServices.editContactWith(editContactRequest);
            return String.format("%s is updated", editContactRequest.getUsername());
        }catch (ContactManagerExceptions error){
            return error.getMessage();
        }
    }

    @DeleteMapping("/delete_contact")
    private String deleteContact(@RequestBody DeleteContactRequest deleteContactRequest){
        try {
            userServices.deleteContactWith(deleteContactRequest);
            return String.format("%s has been deleted from your contact list", deleteContactRequest.getPhoneNumber());
        }catch  (ContactManagerExceptions error){
            return error.getMessage();
        }

            }

            @GetMapping("/find_contact/{id}")
                private String findContact(@PathVariable("id") String id){
                    try{
            userServices.findContactById(id);
            return String.format("%s");
                     }catch (ContactManagerExceptions error){
            return error.getMessage();
        }
            }
            @PostMapping("/sign_out")
            private String sign_out(@RequestBody SignOutRequest signOutRequest){
                try {
                    userServices.signOutWith(signOutRequest);
                    return ("you are signed out");
                }catch (ContactManagerExceptions error){
            return error.getMessage();
        }
            }
        }




