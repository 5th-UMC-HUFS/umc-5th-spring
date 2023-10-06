package umcHomework.umc1.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umcHomework.umc1.member.dto.MemberRequestDto;
import umcHomework.umc1.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity createMember(
            @RequestBody MemberRequestDto memberRequestDto
    ){
        memberService.createMember(memberRequestDto);
        return new ResponseEntity<>("회원 가입 성공", HttpStatus.CREATED);
    }

    @GetMapping("/{memebrId}")
    public ResponseEntity getMember(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(memberService.getMember(id), HttpStatus.OK);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity updateMember(
        @PathVariable Long id,
        @RequestBody MemberRequestDto memberRequestDto
    ){
        memberService.updateMember(memberRequestDto, id);
        return new ResponseEntity<>("회원 정보 수정 완료.", HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(
            @PathVariable Long id
    ){
        memberService.deleteMember(id);
        return new ResponseEntity<>("회원 삭제 완료", HttpStatus.OK);
    }


}
