package com.example.umc.controller;

import com.example.umc.config.BaseException;
import com.example.umc.config.BaseResponse;
import com.example.umc.model.entity.Member;
import com.example.umc.model.dto.UserRequest;
import com.example.umc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/create")
    public ResponseEntity<Member> createUser(@RequestBody UserRequest userRequest){
        Member user = memberService.createUser(userRequest);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/get/{Id}")
    public BaseResponse<Member> getUser(@PathVariable(name = "Id") Long Id){
        try{
            Member member = memberService.getUser(Id);
            return new BaseResponse<>(member);
        }catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @PatchMapping("/update/{Id}")
    public BaseResponse<Member> updateUser(@PathVariable(name = "Id") Long Id, @RequestBody UserRequest userRequest){
        try{
            Member member = memberService.updateUser(userRequest, Id);
            return new BaseResponse<>(member);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

    }

    @DeleteMapping("/delete/{Id}")
    public BaseResponse<String> deleteUser(@PathVariable(name = "Id") Long Id){
        try{
            memberService.delete(Id);
            String result = "삭제되었습니다.";
            return new BaseResponse<>(result);
        }catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


}
