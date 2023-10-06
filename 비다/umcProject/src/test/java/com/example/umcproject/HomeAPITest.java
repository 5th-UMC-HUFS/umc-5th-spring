package com.example.umcproject;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class HomeAPITest {

    @Test
    public void homeAPI(){ // ok
        TestRestTemplate rest = new TestRestTemplate() ;
        // RestTemplate :  API 요청을 호출해 응답을 가져와 수행할 수 있음 (요청과 응답 객체를 갖고 있는 클래스)
        // ㄴ TestRestTemplate  : 테스트용 : 요청과 응답에 대한 정보가 더 자세히 나타나있음

        ResponseEntity<String> res
                = rest.getForEntity("http://localhost:8081/" , String.class, "Spring") ;

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK) ;
    }

    @Test
    public void memberAPI(){ // ok
        TestRestTemplate rest = new TestRestTemplate() ;
        // RestTemplate :  API 요청을 호출해 응답을 가져와 수행할 수 있음 (요청과 응답 객체를 갖고 있는 클래스)
        // ㄴ TestRestTemplate  : 테스트용 : 요청과 응답에 대한 정보가 더 자세히 나타나있음

        ResponseEntity<String> memberList
                = rest.getForEntity("http://localhost:8081/members" , String.class) ;

        Assertions.assertThat(memberList.getStatusCode()).isEqualTo(HttpStatus.OK) ;
    }

    @Test
    public void failsHelloApi(){ // ok
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res
                = rest.getForEntity("http://localhost:8081/wrongPath",String.class) ;

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND) ; // 400

    } // failsHelloApi


}
