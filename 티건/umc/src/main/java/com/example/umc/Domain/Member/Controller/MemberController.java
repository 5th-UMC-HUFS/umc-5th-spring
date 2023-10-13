package com.example.umc.domain.member.controller;

import com.example.umc.domain.member.dto.MemberGetResponseDto;
import com.example.umc.domain.member.dto.MemberRegisterRequestDto;
import com.example.umc.domain.member.dto.MemberUpdateRequestDto;
import com.example.umc.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/register")
    public ResponseEntity<String> registerMember(@RequestBody MemberRegisterRequestDto memberRegisterRequestDto) {
        memberService.registerMember(memberRegisterRequestDto);
        return ResponseEntity.ok()
                .body("회원가입 성공");
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<MemberGetResponseDto> getMember(@PathVariable Long memberId) {
        return ResponseEntity.ok()
                .body(memberService.getMember(memberId));
    }

    //put -> 비어있는 곳을 null로 새로생성
    //patch -> 원래 있던 애를 수정할때
    @PutMapping("/member/{memberId}")
    public ResponseEntity<String> updateMember(@PathVariable Long memberId,
                                               @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        memberService.updateMember(memberId, memberUpdateRequestDto);
        return ResponseEntity.ok()
                .body("수정 성공");
    }

    @DeleteMapping("/member/{memberId}")
    public ResponseEntity<String> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok()
                .body("삭제 성공");
    }

}
