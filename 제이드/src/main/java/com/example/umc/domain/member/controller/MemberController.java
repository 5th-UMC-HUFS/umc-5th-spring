package com.example.umc.domain.member.controller;

import com.example.umc.domain.member.dto.MemberRequestDto;
import com.example.umc.domain.member.dto.MemberUpdateRequestDto;
import com.example.umc.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity registerMember(
            @RequestBody MemberRequestDto requestDto
    ) {
        memberService.createMember(requestDto);
        return new ResponseEntity<>("회원가입이 완료되었습니다.", HttpStatus.CREATED);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity getMemberInfo(
            @PathVariable Long memberId
    ) {
        return new ResponseEntity<>(memberService.getMember(memberId), HttpStatus.OK);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberUpdateRequestDto requestDto
    ) {
        memberService.updateMember(memberId, requestDto);
        return new ResponseEntity<>("회원정보가 수정되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(
            @PathVariable Long memberId
    ) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>("회원탈퇴가 완료되었습니다.", HttpStatus.OK);
    }

}