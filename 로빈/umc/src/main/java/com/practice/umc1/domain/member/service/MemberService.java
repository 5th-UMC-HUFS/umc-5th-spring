package com.practice.umc1.domain.member.service;

import com.practice.umc1.global.exception.CustomException;
import com.practice.umc1.global.exception.ExceptionEnum;
import com.practice.umc1.domain.member.dto.MemberRequestDto;
import com.practice.umc1.domain.member.dto.MemberResponseDto;
import com.practice.umc1.domain.member.entity.Member;
import com.practice.umc1.domain.member.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public ResponseEntity<MemberResponseDto> createMember(MemberRequestDto memberRequestDto, Member member){
        Member newMember = new Member(memberRequestDto, member);
        memberRepository.save(newMember);

        return ResponseEntity.ok().body(new MemberResponseDto(newMember));
    }

    @Transactional(readOnly = true)
    public ResponseEntity findMember(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isEmpty()) {
            throw new CustomException(ExceptionEnum.NOT_EXIST_MEMBER);
        }

        return ResponseEntity.ok().body(new MemberResponseDto(memberOptional.get()));
    }

    @Transactional
    public ResponseEntity updateMember(Long id, MemberRequestDto memberRequestDto, Member member) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isEmpty()) {
            throw new CustomException(ExceptionEnum.NOT_EXIST_MEMBER);
        }

        memberOptional.get().update(memberRequestDto);
        return ResponseEntity.ok().body(new MemberResponseDto(memberOptional.get()));
    }

    @Transactional
    public ResponseEntity deleteMember(Long id, Member member) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isEmpty()) {
            throw new CustomException(ExceptionEnum.NOT_EXIST_MEMBER);
        }

        memberRepository.deleteById(id);
        return ResponseEntity.ok().body(new MemberResponseDto(memberOptional.get()));
    }

}
