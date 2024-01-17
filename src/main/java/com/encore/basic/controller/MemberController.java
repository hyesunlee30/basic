package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberDetailResponseDto;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;

//controller 어노테이션을 통해 싱글톤 컴포넌트로 생성 -> 스프링 빈으로 등록
//스프링 빈이란 스프링이 생성하고 관리하는 객체를 의미
//제어의 역전 IoC
//@AllArgsConstructor
//@RequiredArgsConstructor
@Controller
@RequestMapping("member")
public class MemberController {


    //의존성주입(DI) 방법 1
    //필드주입방식
    //@Autowired를 쓰려면 final 키워드를 쓰면 안된다.
    //private MemberService service;

    //의존성주입(DI) 방법 2
    //생성자주입방식이고, 가장 많이 사용하는 방법
    //생성자 방법은 final을 붙여 상수로 사용할 수 있다. 안정성을 높일 수 있다. 초기화가 가능하다.
    //순환참조방지
    //다형성 구현 가능
//    private final MemberRepository memberRepository;
//    @Autowired -- 생성자가 하나일 때는 생략 가능
//     MemberController (JdbcMemberRepository memberRepository){
//    @Autowired
//     MemberController (JpaMemberRepository memberRepository){
//      this.memberRepository = memberRepository;
//    }
    private MemberService service;
    @Autowired
    MemberController (MemberService service){
        this.service = service;
    }

    //의존성주입(DI) 방법 3
    //@RequiredArgsConstructor를 이용한 방식
    //@NonNull어노테이션이 붙어있는 필드 또는 초기화되지 않은 final필드를 대상으로 생성자 생성
    //유연성 -> 다형성적인 부분에서 떨어짐. 타입이 변하면 바로 바꿔줘야함
    //가독성 ? ? ?
    //private final MemberService service;
    @GetMapping("header")
    public String memberIndex()
    {
        return "member/header";
    }

    @GetMapping("list")
    public String getMemberList(Model model)
    {
        model.addAttribute("members",service.getAllMemberList());
        return "member/member-list";
    }

    @GetMapping("detail")
    public String getMember(@RequestParam(value = "id")int id, Model model)
    {
        try {
            MemberDetailResponseDto dto = service.findById(id);
            if( dto != null){
                model.addAttribute("members",dto);
            }

            return "member/member-detail";
        } catch (NoSuchElementException e) {
            return "404-error-page";
        }
    }

    @GetMapping("create")
    public String createScreen()
    {
        return "member/member-create";
    }

    @PostMapping("create")
    public String createMember(MemberRequestDto memberRequestDto,Model model) {
        service.save(memberRequestDto);
        model.addAttribute("members",service.getAllMemberList());
        return "redirect:/member/list";
    }

}
