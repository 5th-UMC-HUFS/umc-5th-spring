package com.example.umcproject.member.service;

import com.example.umcproject.member.dto.MemberForm;
import com.example.umcproject.member.entity.Member;
import com.example.umcproject.member.entity.MemberStatus;
import com.example.umcproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional // 간단할 떄만 클래스 레벨에 써주고 왠만하면 메서드레벨에 써주자 !
public class MemberService {

    private final MemberRepository memberRepository ;

    // 회원 가입, 수정, 삭제

    /*
    회원 가입
     */
    public Long join(MemberForm memberForm){

        Member member = Member.createMember(memberForm.getName()
                , memberForm.getPhoneNumber()
                , memberForm.getBirthDay()) ;

        memberRepository.save(member) ;

        return member.getId() ;

    } // createMember

    /*
    회원 수정
     */
    public Member updateMember( Long memberId,  MemberForm memberForm){

        Member member = memberRepository.find(memberId) ;

        String name = memberForm.getName() ;
        String phoneNumber = memberForm.getPhoneNumber() ;
        LocalDateTime birthDay = memberForm.getBirthDay() ;

        return member.updateMember(member , name, phoneNumber, birthDay) ;

    }

    /*
    회원 검색
     */
    @Transactional(readOnly = true)
    public List<Member> findMembers(){
        /*
         검색 로직 추가 필요
         */
        return memberRepository.findAll() ;
    }

    @Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.find(memberId) ;
    }

    /*
    회원 삭제
     */
    public void leave(Long memberId){

        Member member = memberRepository.find(memberId) ;
        member.updateStatus(member, MemberStatus.INACTIVE);

        /*
        7일 뒤에 MemberStatus.DELETE 로 바뀌는 로직 추가 필요
         */

    }

}
