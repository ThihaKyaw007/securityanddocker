package com.testing.RBAC.config;

import com.testing.RBAC.dao.RoleRepository;
import com.testing.RBAC.dao.UserRepository;
import com.testing.RBAC.entity.Role;
import com.testing.RBAC.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Component
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        Role guestRole = new Role();
        guestRole.setName("GUEST");

        roleRepository.saveAll(Arrays.asList(adminRole, userRole, guestRole));

        Users adminUser = new Users();
        adminUser.setUsername("admin");
        adminUser.setPassword(new BCryptPasswordEncoder().encode("admin123"));
        adminUser.setRoles(Set.of(adminRole)); // Ensure the user has the ADMIN role
        userRepository.save(adminUser);

        Users user = new Users();
        user.setUsername("user");
        user.setPassword(new BCryptPasswordEncoder().encode("user123"));
        user.setRoles(Set.of(userRole)); // Assign USER role
        userRepository.save(user);
    }

}
