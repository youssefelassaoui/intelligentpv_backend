package gep.ma.intelligent.pv.Services;

import gep.ma.intelligent.pv.DTO.SignupRequest;
import gep.ma.intelligent.pv.Models.Role;
import gep.ma.intelligent.pv.Models.User;
import gep.ma.intelligent.pv.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Admin creates a new admin user
    public String signupUser(SignupRequest signupRequest) {
        // Check if the user already exists
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return "Username already exists!";
        }

        // Create a new user object
        User user = new User();
        user.setId(UUID.randomUUID());  // Generate a new UUID for the user
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        // Assign default role as ADMIN
        user.setRoles(Set.of(Role.ADMIN));

        // Save the user in the database
        userRepository.save(user);

        return "Admin user created successfully!";
    }



    // Admin creates a new viewer user
    public String createViewerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            return "User already exists!";
        }

        // Create a new user with the VIEWER role
        User user = new User();
        user.setId(UUID.randomUUID());  // Assign a UUID as the ID
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of(Role.VIEWER));

        // Save the viewer user in the database
        userRepository.save(user);
        return "Viewer created successfully!";
    }

    // Check if a username already exists
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    // Update user details (password only for now) by ID
    public void updateUser(UUID id, String password) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(passwordEncoder.encode(password)); // Update the password
            userRepository.save(user);
        }
    }

    // Delete user by ID
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
