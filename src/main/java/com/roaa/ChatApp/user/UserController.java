package com.roaa.ChatApp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final  UserService service;

    @MessageMapping("/user.addUser")
    @SendTo("/User/topic")
    public User addUser(@Payload User user){
        service.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/User/topic")
    public User disconnect(@Payload User user){
        service.disconnect(user);
        return user;
    }

    @GetExchange("/users")
    public ResponseEntity<List<User>> findConnectedUsers(){
        return ResponseEntity.ok(service.findConnectedUsers());
    }

}
