package gep.ma.intelligent.pv.Controllers;

import gep.ma.intelligent.pv.DTO.AddUserRequest;
import gep.ma.intelligent.pv.Models.User;
import gep.ma.intelligent.pv.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")  // Only admins can access this controller
public class AdminController {

    @Autowired
    private UserService userService;

    // Add a new user (Viewer role)
    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody AddUserRequest addUserRequest) {
        if (userService.userExists(addUserRequest.getUsername())) {
            return ResponseEntity.badRequest().body("User already exists!");
        }
        userService.createViewerUser(addUserRequest.getUsername(), addUserRequest.getPassword());
        return ResponseEntity.ok("User created successfully!");
    }

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update user information by ID
    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody AddUserRequest updateUserRequest) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.updateUser(id, updateUserRequest.getPassword());
            return ResponseEntity.ok("User updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete user by ID
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
