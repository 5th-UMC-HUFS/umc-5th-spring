package com.example.umc.Domain.Member.Service;

import com.example.umc.Domain.Member.DTO.MemberRegisterRequestDto;
import com.example.umc.Domain.Member.DTO.MemberGetResponseDto;
import com.example.umc.Domain.Member.DTO.MemberUpdateRequestDto;
import com.example.umc.Domain.Member.Entity.Member;
import com.example.umc.Domain.Member.Repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void registerMember(MemberRegisterRequestDto memberRegisterRequestDto) {

        Member newMember = Member.builder()
                .name(memberRegisterRequestDto.getName())
                .nickName(memberRegisterRequestDto.getNickName())
                .birthDay(memberRegisterRequestDto.getBirthDay())
                .phoneNumber(memberRegisterRequestDto.getPhoneNumber())
                .build();

        memberRepository.save(newMember);

    }

    @Transactional
    public MemberGetResponseDto getMember(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if (optionalMember.isPresent()) {
            return MemberGetResponseDto.of(optionalMember.get());
        } else {
            throw new RuntimeException("조회하려는 회원이 존재하지 않음");
        }
    }

    //JPA를 사용하면 트랜잭션 범위 안에서 Dirty Checking이 동작한다.?
    //save를 사용안해도 된다?
    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequestDto memberUpdateDto) {

        Optional<Member> optionalMember = memberRepository.findById(memberId);

        optionalMember
                .orElseThrow(() -> new RuntimeException("수정하려는 회원이 존재하지 않음"));

        Member member = optionalMember.get();
        member.update(memberUpdateDto);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        optionalMember
                .orElseThrow(() -> new RuntimeException("삭제하려는 회원이 존재하지 않음"));

        memberRepository.deleteById(memberId);

    }
}
