package umcHomework.umc1.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umcHomework.umc1.member.dto.MemberRequestDto;
import umcHomework.umc1.member.dto.MemberResponseDto;
import umcHomework.umc1.member.entity.Member;
import umcHomework.umc1.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(MemberRequestDto memberRequestDto){
        Member member = Member.builder()
                .name(memberRequestDto.getName())
                .nickname(memberRequestDto.getNickname())
                .phoneNum(memberRequestDto.getPhoneNum())
                .bDay(memberRequestDto.getBDay())
                .build();
        memberRepository.save(member);
    }

    @Transactional
    public MemberResponseDto getMember(Long id){
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()){
            Member realMember = member.get();
            return MemberResponseDto.of(realMember);
        }else{
            throw new RuntimeException();
        }
    }

    @Transactional
    public void updateMember(MemberRequestDto memberRequestDto, Long id){
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()){
            Member realmember = member.get();
            realmember.updateMember(memberRequestDto);
        }else{
            throw new RuntimeException();
        }
    }

    @Transactional
    public void deleteMember(Long id){
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()){
            Member realMember = member.get();
            memberRepository.delete(realMember);
        }else{
            throw new RuntimeException();
        }
    }
}
