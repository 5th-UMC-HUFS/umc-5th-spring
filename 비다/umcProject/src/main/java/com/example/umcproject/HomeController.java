package com.example.umcproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {
// 매핑 안될땐
    @GetMapping("hello")
    public String hello(Model model){ // Model : view로 넘길 수 있는 데이타
        model.addAttribute("data", "hello" ); // <data , hello> --> Model 에 붙여서
        return "home"; // forwarding 할 view 페이지 return --> 자동으로 html 붙음
    }

    @RequestMapping("/")
    public String home(Model model){
        log.info("home controller"); // 이 로그 찍힌거 확인하니 화면에서 문제 발생한거 알 수 있음

        model.addAttribute("data", "hello" );
        return "home" ; // home.html
    }

}
