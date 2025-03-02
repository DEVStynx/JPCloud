package de.stynxyxy.jpcloudbackend.controller.user;

import de.stynxyxy.jpcloudbackend.db.model.user.UserEntity;
import de.stynxyxy.jpcloudbackend.service.db.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users/register")
    public boolean register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("permRead") boolean perm_read, @RequestParam("permWrite") boolean perm_write) {
        System.out.println("connection");
        System.out.println("new user: "+username+ " password: "+password);
        return userService.registerUser(username,password,perm_write,perm_read);
    }
    @GetMapping("/users/register2/{username}/{password}/{perm_read}/{perm_write}")
    public boolean register2(@PathVariable String username, @PathVariable String password, @PathVariable boolean perm_read, @PathVariable boolean perm_write) {
        System.out.println("connection");
        System.out.println("new user: "+username+ " password: "+password);
        return userService.registerUser(username,password,perm_write,perm_read);
    }

    @GetMapping("/user")
    public UserEntity getByName(@RequestParam("username") String username) {

        return userService.findByUserName(username);
    }
}
