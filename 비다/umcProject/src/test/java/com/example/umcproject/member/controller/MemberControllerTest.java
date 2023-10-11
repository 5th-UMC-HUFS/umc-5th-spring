package com.example.umcproject.member.controller;

import com.example.umcproject.member.dto.MemberForm;
import com.example.umcproject.member.service.MemberService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberControllerTest {

    @Autowired
    private MemberService memberService ;


    @Test
    public void 폼띄우기(){ // ok

        // given
        Long memberId = memberService.join(new MemberForm("heemin", "010-1111-1111", LocalDateTime.now())) ;

        TestRestTemplate rest = new TestRestTemplate();

        // when
        ResponseEntity<String> memberForm
                = rest.getForEntity("http://localhost:8081/members/new", String.class) ;
        ResponseEntity<String> memberList
                = rest.getForEntity("http://localhost:8081/members", String.class) ;
        ResponseEntity<String> updateMemberForm
                = rest.getForEntity("http://localhost:8081/members/{memberId}/edit", String.class, memberId ) ;

        // then 
        Assertions.assertThat(memberForm.getStatusCode()).isEqualTo(HttpStatus.OK) ;
        Assertions.assertThat(memberList.getStatusCode()).isEqualTo(HttpStatus.OK) ;
        Assertions.assertThat(updateMemberForm.getStatusCode()).isEqualTo(HttpStatus.OK) ;

    }

    @Test
    public void null매개변수() {

        // 필수 값인 name 을 입력안했을 때

    }

    @Test
    public void 회원가입처리() {
    }

    @Test
    public void 회원정보수정() {
    }

    @Test
    public void 회원탈퇴() {
    }


}