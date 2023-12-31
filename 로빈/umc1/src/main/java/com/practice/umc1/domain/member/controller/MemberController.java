package com.practice.umc1.domain.member.controller;


import com.practice.umc1.domain.member.entity.Member;
import com.practice.umc1.domain.member.service.MemberService;
import com.practice.umc1.domain.member.dto.MemberRequestDto;
import com.practice.umc1.domain.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberRequestDto memberRequestDto, Member member ) {
        return memberService.createMember(memberRequestDto, member);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity findMember(@PathVariable Long id) {
        return memberService.findMember(id);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity updateMember(@PathVariable Long id, @RequestBody MemberRequestDto memberRequestDto, Member member) {
        return memberService.updateMember(id, memberRequestDto, member);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long id, Member member) {
        return memberService.deleteMember(id, member);
    }

}
