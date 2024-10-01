package gep.ma.intelligent.pv.Services;

import gep.ma.intelligent.pv.Models.User;
import gep.ma.intelligent.pv.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // No need for Optional.ofNullable, as findByUsername already returns Optional<User>
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        User user = optionalUser.get();

        // Convert the user roles (Set<Role>) into GrantedAuthority for Spring Security
        Collection<? extends GrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());

        // Return the UserDetails object which includes username, password, and authorities (roles)
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    // Helper method to map the Set<Role> to a Collection of GrantedAuthority
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<gep.ma.intelligent.pv.Models.Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
}
