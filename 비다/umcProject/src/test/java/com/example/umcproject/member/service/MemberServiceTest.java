package com.example.umcproject.member.service;

import com.example.umcproject.member.dto.MemberForm;
import com.example.umcproject.member.entity.Member;
import com.example.umcproject.member.entity.MemberStatus;
import com.example.umcproject.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService ;
    @Autowired MemberRepository memberRepository ;
    @Autowired EntityManager en ;

    @Test
    public void 회원가입() { // ok 

        // given
        Member member = Member.createMember("heemin", "010-1111-1111", LocalDateTime.now()) ;
        memberRepository.save(member) ;

        // when
        en.flush();

        // then
        Member findMember =  memberRepository.find( member.getId()) ;
        Assert.assertEquals(member, findMember);
    }

    @Test
    public void 회원조회(){
        Member member = Member.createMember("heemin", "010-1111-1111", LocalDateTime.now()) ;
        memberRepository.save(member) ;

        Member findMember = memberService.findOne(member.getId()) ;
        Assert.assertEquals(member.getId(), findMember.getId());
    }


    @Test
    public void 회원_정보_수정() { // ok

        // given
        Member member = Member.createMember("heemin", "010-1111-1111", LocalDateTime.now()) ;
        memberRepository.save(member) ;


        // when
        memberService.updateMember(member.getId(), new MemberForm("shin","010-2222-1111", LocalDateTime.now()) );

        // then
        Assert.assertEquals(member.getName(), "shin") ;
        Assert.assertEquals(member.getPhoneNumber(), "010-2222-1111") ;
    }


    @Test
    public void 탈퇴하기() {

        // given
        Member member = Member.createMember("heemin", "010-1111-1111", LocalDateTime.now()) ;
        memberRepository.save(member) ;

        // when
        memberService.leave(member.getId());

        // then
        Assert.assertEquals(member.getMemberStatus(), MemberStatus.INACTIVE);
    }
}