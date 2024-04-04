package africa.semicolon.exceptions;

public class InvalidPhoneNumberException extends ContactManagerExceptions{
    public InvalidPhoneNumberException(String message){
        super(message);
    }
}
