package com.example.umc.service;

import com.example.umc.entity.UserRequest;
import com.example.umc.repository.UserRepository;
import com.example.umc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public User createUser(UserRequest userRequest) {

        User user = new User();

        user.setName(userRequest.getName());
        user.setNickname(userRequest.getNickname());
        user.setBirthday(user.getBirthday());
        user.setPhonenumber(user.getPhonenumber());

        userRepository.save(user);

        return user;
    }

    public User getUser(Long id){

        Optional<User> user = userRepository.findById(id);

        return user.get();
    }

    @Transactional
    public User updateUser(UserRequest userRequest, Long id) {

        Optional<User> userOptional = userRepository.findById(id);

        User user = userOptional.get();

        user.setName(userRequest.getName());
        user.setNickname(userRequest.getNickname());
        user.setBirthday(user.getBirthday());
        user.setPhonenumber(user.getPhonenumber());

        return userRepository.save(user);
    }

    @Transactional
    public User delete(Long id) {

        Optional<User> user = userRepository.findById(id);

        userRepository.delete(user.get());

        return user.get();

    }

}
