package de.stynxyxy.jpclouddevelopment;

import de.stynxyxy.jpclouddevelopment.security.user.User;
import de.stynxyxy.jpclouddevelopment.security.user.UserRepository;
import de.stynxyxy.jpclouddevelopment.security.user.role.Role;
import de.stynxyxy.jpclouddevelopment.security.user.role.RoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class JpCloudDevelopmentApplication {
    @Value("${jpcloud.authentication.defaultcredentials.username}")
    private String defaultUsername;
    @Value("${jpcloud.authentication.defaultcredentials.password}")
    private String defaultPassword;


    public static void main(String[] args) {
        SpringApplication.run(JpCloudDevelopmentApplication.class, args);
    }


    @Bean
    public CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            if (roleRepo.findByName("ROLE_USER") == null) {
                roleRepo.save(new Role( "ROLE_USER", null));
            }

            if (userRepo.findByUsername(defaultUsername).isEmpty()) {
                Role userRole = roleRepo.findByName("ROLE_USER");
                User user = new User();
                user.setUsername(defaultUsername);
                user.setPassword(encoder.encode(defaultPassword)); // bcrypt
                user.setRoles(Set.of(userRole));
                userRepo.save(user);
            }
        };
    }

}
