package africa.semicolon.services;

import africa.semicolon.data.models.User;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dataTransferObjects.requests.SignInRequest;
import africa.semicolon.dataTransferObjects.requests.SignOutRequest;
import africa.semicolon.dataTransferObjects.requests.SignUpRequest;
import africa.semicolon.exceptions.ContactManagerExceptions;
import africa.semicolon.exceptions.UserExistsException;
import africa.semicolon.exceptions.WrongSignInDetailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServicesImplTest {
    @Autowired
    private UserServices userServices;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void given(){
        userRepository.deleteAll();
    }

    @Test
    public void  userSignsUp_numberOfUsersIncreaseByOneTest() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("Bonnymars@yahoo.com");
        signUpRequest.setPhoneNumber("07045623498");
        signUpRequest.setPassword("password");
        userServices.signUpWith(signUpRequest);
        assertEquals(1,userRepository.count());

    }
    @Test
    public void  userSignsUpWithSameDetail_userCountThrowsUserExistsException() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("Bonnymars@yahoo.com");
        signUpRequest.setPhoneNumber("07045623498");
        signUpRequest.setPassword("password");
        userServices.signUpWith(signUpRequest);

        signUpRequest.setEmail("Bonnymars@yahoo.com");
        signUpRequest.setPhoneNumber("07045623498");
        signUpRequest.setPassword("password");
        userServices.signUpWith(signUpRequest);

        //assertEquals(2, userRepository.count());
        try {
            userServices.signUpWith(signUpRequest);
        } catch (UserExistsException error) {
            assertEquals(error.getMessage(), "User already exist");

        }


    }
        @Test
        public void userCanSign_in_Test () {
            SignUpRequest signUpRequest = new SignUpRequest();
            signUpRequest.setEmail("Bonnymars@yahoo.com");
            signUpRequest.setPhoneNumber("07045623498");
            signUpRequest.setPassword("password");
            userServices.signUpWith(signUpRequest);
            assertEquals(1, userRepository.count());

            SignInRequest signInRequest = new SignInRequest();
            signInRequest.setEmail("Bonnymars@yahoo.com");
            userServices.signIn(signInRequest);
            User user = userRepository.findUserByEmail("Bonnymars@yahoo.com");
            assertFalse(user.isLocked());
    }

    @Test
        public void userSignInWith_wrongEmail_serviceThrowsWrongSignInException () {
            SignUpRequest signUpRequest = new SignUpRequest();
            signUpRequest.setEmail("Bonnymars@yahoo.com");
            signUpRequest.setPhoneNumber("07045623498");
            signUpRequest.setPassword("password");
            userServices.signUpWith(signUpRequest);
            assertEquals(1, userRepository.count());

            SignInRequest signInRequest = new SignInRequest();
            signInRequest.setEmail("Bountyco@gmail.com");
            assertThrows(WrongSignInDetailException.class,()-> userServices.signIn(signInRequest));
    }

    @Test
    public void userCanSign_outTest(){
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("Bonnymars@yahoo.com");
        signUpRequest.setPhoneNumber("07045623498");
        signUpRequest.setPassword("password");
        userServices.signUpWith(signUpRequest);
        assertEquals(1, userRepository.count());

        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail("Bonnymars@yahoo.com");
        userServices.signIn(signInRequest);
        User user = userRepository.findUserByEmail("Bonnymars@yahoo.com");
        assertFalse(user.isLocked());

        SignOutRequest signOutRequest = new SignOutRequest();
        user = userRepository.findUserByEmail("Bonnymars@yahoo.com");
        signOutRequest.setId(user.getId());
        signOutRequest.setPassword(user.getPassword());
        userServices.signOutWith(signOutRequest);
        user = userRepository.findUserByEmail("Bonnymars@yahoo.com");
        assertTrue(user.isLocked());
    }
}