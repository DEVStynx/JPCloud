package de.stynxyxy.jpclouddevelopment;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import de.stynxyxy.jpclouddevelopment.db.model.branch.BranchRepository;
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

import java.io.File;
import java.util.Set;
import java.util.logging.Logger;

@SpringBootApplication
public class JpCloudDevelopmentApplication {
    @Value("${jpcloud.authentication.defaultcredentials.username}")
    private String defaultUsername;
    @Value("${jpcloud.authentication.defaultcredentials.password}")
    private String defaultPassword;
    @Value("${jpcloud.branches.defaultbranch.label}")
    private String defaultBranchlabel;
    @Value("${jpcloud.branches.defaultbranch.path}")
    private String defaultBranchPath;

    public static void main(String[] args) {
        SpringApplication.run(JpCloudDevelopmentApplication.class, args);
    }

    //Used to initialize standard branches and users
    @Bean
    public CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, BranchRepository branchRepo, PasswordEncoder encoder) {
        return args -> {
            Logger logger = Logger.getLogger("StandardSetup");
            //Setup standard Roles
            if (roleRepo.findByName("ROLE_USER") == null) {
                roleRepo.save(new Role( "ROLE_USER", null));
                logger.info("Created standard User Role");
            }
            //Setup standard Users
            if (userRepo.findByUsername(defaultUsername).isEmpty()) {
                Role userRole = roleRepo.findByName("ROLE_USER");
                User user = new User();
                user.setUsername(defaultUsername);
                user.setPassword(encoder.encode(defaultPassword)); // bcrypt
                user.setRoles(Set.of(userRole));
                userRepo.save(user);
                logger.info("Created standard User: " + user.getUsername() +" : "+ user.getPassword());
            }
            //Setup standard Branch
            if (branchRepo.findByLabel(defaultBranchlabel).isEmpty()) {
                File defaultBranch = new File(defaultBranchPath);
                if (defaultBranch != null && defaultBranch.exists() && defaultBranch.isDirectory()) {

                    Branch branch = new Branch(defaultBranchlabel,defaultBranch.getAbsolutePath());
                    branchRepo.save(branch);
                    logger.info("Created standard Branch: " + branch.getLabel() +" : "+ branch.getPath());
                } else {
                    logger.warning("Issue creating default branch: ");
                    logger.warning("Does path: "+defaultBranch.getAbsolutePath() + " exist: "+ defaultBranch.exists());
                    logger.warning("Is the path a directory: "+ defaultBranch.isDirectory());
                    if (defaultBranch.isDirectory()) {
                        logger.warning("The given path should be a directory!");
                    }
                }
            }


        };
    }

}
