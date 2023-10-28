package com.practice.umc1;


import com.practice.umc1.domain.member.dto.RequestDto;
import com.practice.umc1.domain.member.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/post")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/post")
    public ResponseEntity<ResponseDto> createMember(@RequestBody RequestDto requestDto) {
        return memberService.createMember(requestDto);
    }

    @PostMapping("/post")
    public ResponseEntity findMember() {
        return memberService.findMember();
    }



}
