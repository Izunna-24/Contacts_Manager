package africa.semicolon.controllers;

import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dataTransferObjects.requests.SignUpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void given(){
        userRepository.deleteAll();
    }

    @Test
    public void  userSignsUp_isSuccessful() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("Bonnymars@yahoo.com");
        signUpRequest.setPhoneNumber("07045623498");
        signUpRequest.setPassword("password");
        var result = userController.signUpWith(signUpRequest);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

    }
    @Test
    public void  userSignsUp_isSuccessful_false() {
        userRepository.deleteAll();
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("Bonnymars@yahoo.com");
        signUpRequest.setPhoneNumber("07045623498");
        signUpRequest.setPassword("password");
        userController.signUpWith(signUpRequest);
        var result = userController.signUpWith(signUpRequest);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

    }

}