package gep.ma.intelligent.pv.Controllers;

import gep.ma.intelligent.pv.DTO.LoginRequest;
import gep.ma.intelligent.pv.DTO.SignupRequest;
import gep.ma.intelligent.pv.Models.User;
import gep.ma.intelligent.pv.Services.UserService;
import gep.ma.intelligent.pv.Util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Admin Signup
    @PostMapping("/signup")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequest signupRequest) {
        String result = userService.signupUser(signupRequest);
        if ("Username already exists!".equals(result)) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    // Sign in Endpoint
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        // Set the authentication object in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate a JWT token
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Get the username from the authenticated principal
        String username = authentication.getName();

        // Extract roles from the authentication object, ensure proper casting
        List<String> roles = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority()) // Extract the authority (role)
                .collect(Collectors.toList());

        // Create response object
        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("username", username);
        response.put("roles", roles);

        // Return the response with token, username, and roles
        return ResponseEntity.ok(response);
    }



    // Endpoint to get the list of viewers
    @GetMapping("/viewers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllViewers() {
        return userService.getAllUsers();
    }

    // Get all users (optional)
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
