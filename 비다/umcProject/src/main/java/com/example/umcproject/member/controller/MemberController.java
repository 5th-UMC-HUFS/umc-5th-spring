package com.example.umcproject.member.controller;

import com.example.umcproject.member.dto.MemberForm;
import com.example.umcproject.member.entity.Member;
import com.example.umcproject.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
// @RestController면 페이지 포워딩 안됨 왜 ??
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService ;

    // 회원 가입, 수정, 검색, 삭제

    @GetMapping("/members/new")
    public String createForm(Model model){

        MemberForm memberForm = new MemberForm() ;

        log.info("member controller - get form");
        model.addAttribute("memberForm", memberForm);

        return "members/memberForm" ;
    }

    @PostMapping("/members/new")
    public String createMember(@Valid  MemberForm memberForm , BindingResult result){

        log.info("member controller - post member");

        if(result.hasErrors()){
            return "members/memberForm";
        }

        memberService.join(memberForm) ;
        return "redirect:/" ;
    }

    @GetMapping("/members")
    public String list(Model model){

        log.info("member controller - get list");
        model.addAttribute("members", memberService.findMembers()) ;
        return "members/memberList" ;
    }

    @GetMapping("/members/{memberId}/edit")
    public String updateForm(@PathVariable("memberId") Long memberId,  Model model){

        log.info("member controller - edit form");

        Member member = memberService.findOne(memberId) ;
        MemberForm form = new MemberForm(member.getName()
                , member.getPhoneNumber()
                , member.getBirthDay()) ;
        model.addAttribute("memberForm", form) ;

        return "members/updateMemberForm" ;
    }

    @PostMapping("/members/{memberId}/edit")
    public String updateMember(@PathVariable Long memberId, @ModelAttribute("form") MemberForm form){

        log.info("member controller - edit member");

        memberService.updateMember(memberId, form);
        return "redirect:/members";
    }


    @DeleteMapping("/members/{memberId}/delete")
    public String leaveMember(@PathVariable Long memberId){
        log.info("member controller - leave member");

        memberService.leave(memberId);
        return "redirect:/" ;
    }
}
