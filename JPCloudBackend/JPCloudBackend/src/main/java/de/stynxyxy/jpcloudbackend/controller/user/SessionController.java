package de.stynxyxy.jpcloudbackend.controller.user;

import de.stynxyxy.jpcloudbackend.db.model.user.session.SessionEntity;
import de.stynxyxy.jpcloudbackend.service.db.session.SessionService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SessionController {
    @Autowired
    SessionService sessionService;

    @GetMapping("/session/login")
    public UUID createSession(HttpServletRequest servlet) {
            return UUID.fromString(sessionService.createSession(servlet.getRemoteAddr()));
    }
    @GetMapping("/session/valid")
    public boolean isValid(HttpServletRequest servlet, @RequestParam(name = "token") String tokenstr) {
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
}
