package com.example.umc.domain.member.service;

import com.example.umc.domain.member.dto.MemberRequestDto;
import com.example.umc.domain.member.dto.MemberResponseDto;
import com.example.umc.domain.member.dto.MemberUpdateRequestDto;
import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(MemberRequestDto requestDto) {
        Member member = Member.builder()
                .name(requestDto.getName())
                .nickname(requestDto.getNickname())
                .birthday(requestDto.getBirthday())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();
        memberRepository.save(member);
    }

    @Transactional
    public MemberResponseDto getMember(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return MemberResponseDto.of(member);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequestDto requestDto) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.updateMember(requestDto);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            memberRepository.delete(member);
        } else {
            throw new RuntimeException();
        }
    }
}