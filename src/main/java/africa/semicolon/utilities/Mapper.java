package africa.semicolon.utilities;

import africa.semicolon.data.models.User;
import africa.semicolon.dataTransferObjects.SignUpRequest;

public class Mapper {
public static User map(SignUpRequest signUpRequest){
    User user  = new User();
    user.setEmail(signUpRequest.getEmail());
    user.setPhoneNumber(signUpRequest.getPhoneNumber());
    user.setPassword(signUpRequest.getPassword());
    return user;
}
}
