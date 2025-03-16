package de.stynxyxy.jpcloudbackend.controller.user;

import de.stynxyxy.jpcloudbackend.db.model.user.session.SessionEntity;
import de.stynxyxy.jpcloudbackend.service.db.session.SessionService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class SessionController {
    @Autowired
    SessionService sessionService;

    @GetMapping("/session/login")
    public String createSession(HttpServletRequest servlet, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        return sessionService.createSession(servlet.getRemoteAddr(),username,password);
    }
    @GetMapping("/session/valid")
    public boolean isValid(HttpServletRequest servlet, @RequestParam(name = "token") String tokenstr) {
        return valid(servlet,tokenstr);
    }
    @GetMapping("/session/logout")
    public ResponseEntity<Boolean> logout(HttpServletRequest request,@RequestParam(name = "token") String tokenstr) {
        UUID token = UUID.fromString(tokenstr);
        if (valid(request,tokenstr)) {
            sessionService.removeByToken(token);
            return ResponseEntity.of(Optional.of(true));
        }
        return ResponseEntity.status(403).build();
    }
    public boolean valid(HttpServletRequest servlet,  String tokenstr) {
        UUID token = UUID.fromString(tokenstr);
        SessionEntity entity = sessionService.findByToken(token);
        if (entity == null) {
            System.out.println("Entity Is NUll!");
            return false;
        }
        if (!servlet.getRemoteAddr().equals(entity.getIp())) {
            System.out.println("db IP: "+entity.getIp()+ " currentIp: "+servlet.getRemoteAddr());
            System.out.println("other Ip");
            return false;
        }
        if (!entity.isStillvalid()) {
            sessionService.removeByIp(servlet.getRemoteAddr());
            return false;
        }
        return true;
    }

    @GetMapping("/session/login/web")
    public ResponseEntity<Void> createwebSession(HttpServletRequest servlet, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        String token = sessionService.createSession(servlet.getRemoteAddr(),username,password);
        if (token != null && !token.contains("Err")) {
            return ResponseEntity.status(302).header("Location","/main.html?token=" + token).build();
        }
        return ResponseEntity.status(302).header("Location","/error.html?error="+token).build();
    }
}
