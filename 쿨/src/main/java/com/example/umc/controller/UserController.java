package com.example.umc.controller;

import com.example.umc.entity.User;
import com.example.umc.entity.UserRequest;
import com.example.umc.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@Getter
@AllArgsConstructor
public class UserController {

    private final UserService userservice;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){
        User user = this.userservice.createUser(userRequest);
        String a = "create";
        return ResponseEntity.ok().body(a);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "Id") Long Id){
        User user = this.userservice.getUser(Id);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<User> updateUser(@PathVariable(name = "Id") Long Id, @RequestBody UserRequest userRequest){
        User user = this.userservice.updateUser(userRequest, Id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<User> deleteUser(@PathVariable(name = "Id") Long Id){
        User user = this.userservice.delete(Id);
        return ResponseEntity.ok().body(user);
    }


}
