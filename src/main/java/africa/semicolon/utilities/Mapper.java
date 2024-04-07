package africa.semicolon.utilities;

import africa.semicolon.data.models.User;
import africa.semicolon.dataTransferObjects.requests.SignUpRequest;
import africa.semicolon.dataTransferObjects.responses.SignInResponse;
import africa.semicolon.dataTransferObjects.responses.SignUpResponse;

public class Mapper {
        public static User map(SignUpRequest signUpRequest){
        User user  = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setPassword(signUpRequest.getPassword());
        return user;
    }

    public static SignUpResponse signUpResponseMap(User user){
            SignUpResponse signUpResponse = new SignUpResponse();
            signUpResponse.setUserId(user.getId());
            signUpResponse.setUsername(user.getUsername());
            return signUpResponse;

    }
}
