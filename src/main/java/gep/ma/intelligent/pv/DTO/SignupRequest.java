package gep.ma.intelligent.pv.DTO;

import gep.ma.intelligent.pv.Models.Role;
import lombok.Data;
import java.util.Set;

@Data
public class SignupRequest {
    private String username;
    private String password;
}
