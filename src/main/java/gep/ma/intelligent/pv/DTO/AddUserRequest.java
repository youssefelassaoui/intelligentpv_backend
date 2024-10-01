package gep.ma.intelligent.pv.DTO;

import lombok.Data;

@Data
public class AddUserRequest {
    private String username;  // Email or username
    private String password;
}
