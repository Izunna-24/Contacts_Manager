package africa.semicolon.dataTransferObjects.responses;

import lombok.Data;

@Data
public class SignInResponse {
    private String username;
    private boolean isLocked;
}
