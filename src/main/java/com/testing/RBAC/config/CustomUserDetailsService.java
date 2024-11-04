package com.testing.RBAC.config;

import com.testing.RBAC.dao.UserRepository;
import com.testing.RBAC.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;  // Inject the user repository to fetch user details

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Convert User entity to Spring Security's UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),  // Password should be already encoded
                getAuthorities(user)  // Convert the roles to GrantedAuthority format
        );
    }

    // Helper method to convert User roles into GrantedAuthority
    private Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))  // Convert each role to a SimpleGrantedAuthority
                .collect(Collectors.toList());
    }
}

