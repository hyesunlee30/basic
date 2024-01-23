package com.encore.basic.controller;

import com.encore.basic.domain.*;
import com.encore.basic.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;


@Api(tags = "회원관리서비스")
//@Responsebody를 쓸 필요가 없음
@RestController
@RequestMapping("memberRest")
public class MemberRestController {

    private MemberService service;
    @Autowired
    MemberRestController(MemberService service){
        this.service = service;
    }


    @GetMapping("list")
    public List<MemberResponseDto> getMemberList()
    {
        return service.getAllMemberList();
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<Map<String, Object>> getMember(@PathVariable(value = "id")int id)
    {
        try {
            return ResponseEntityController.mepCustom2(service.findById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntityController.mepCustom1(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }


    @PostMapping("create")
    public String createMember(@RequestBody MemberRequestDto memberRequestDto) {
        service.save(memberRequestDto);
        return "ok";
    }

    @DeleteMapping("delete/{id}")
    public String deleteMember(@PathVariable(value = "id")int id) {
        service.delete(id);
        return "ok";
    }

    @PatchMapping("update")
    public MemberDetailResponseDto updateMember(@RequestBody MemberUpdateDto memberUpdateDto){
        service.update(memberUpdateDto);
        return service.findById(memberUpdateDto.getId());
    }

}
