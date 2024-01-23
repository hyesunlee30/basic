package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("response/entity")
public class ResponseEntityController {

    //@ResponseStatus 어노테이션 방식
    @GetMapping("status")
    @ResponseStatus(HttpStatus.CREATED)
    public String responseStatus() {
        //200
        //201 생성됐다. insert, save
        return "OK";
    }

    //ResponseStatus 어노테이션 방식
    //객체반환
    @GetMapping("status2")
    @ResponseStatus(HttpStatus.CREATED)
    public Member responseStatus2() {
        return Member.builder().name("test").email("naver").password("dsfsf").build();
    }

    //body 커스텀 객체
    @GetMapping("custom1")
    public ResponseEntity<Member> custom1() {
        Member member = Member.builder().name("test").email("naver").password("dsfsf").build();
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    //body 커스텀 html
    @GetMapping("custom2")
    public ResponseEntity<String> custom2() {
        String html = "<h1>없는 ID입니다.</h1>";
        return new ResponseEntity<>(html, HttpStatus.NOT_FOUND);
    }

    //body 커스텀 map 형태 메시지 커스텀

    public static ResponseEntity<Map<String, Object>> mepCustom1(HttpStatus status, String error) {
        Map<String, Object> body = new HashMap<>();
        body.put("status",Integer.toString(status.value()));
        body.put("error",error);
        return new ResponseEntity<>(body, status);
    }


    public static ResponseEntity<Map<String, Object>> mepCustom2(Object object, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("status",Integer.toString(status.value()));
        body.put("message",object);
        return new ResponseEntity<>(body, status);
    }

    //메서드 체이닝 메서드 , 클래스 메서드를 사용하고 있따.
    @GetMapping("chaining1")
    public ResponseEntity<Member> chaining1() {
        Member member = Member.builder().name("test").email("naver").password("dsfsf").build();
        return ResponseEntity.ok(member);
    }

    @GetMapping("chaining2")
    public ResponseEntity<String> chaining2() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("chaining3")
    public ResponseEntity<Member> chaining3() {
        Member member = Member.builder().name("test").email("naver").password("dsfsf").build();
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

}
