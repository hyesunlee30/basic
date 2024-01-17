package com.encore.basic.service;

import com.encore.basic.domain.*;
import com.encore.basic.repository.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
//@RequiredArgsConstructor
public class MemberService {

    //MemberRepository와 JpaRepository 둘 다 가지고 있는 것만 쓸 수 있다.
    //private MemberRepository repository;
    //SpringDataJpaMemberRepository 를 쓰면 extends를 JpaRepository만 하면 jpa기능 다 쓸 수 있음

    private MemberRepository repository;
//    @Autowired
//    MemberService(JpaMemberRepository repository) {
//        this.repository = repository;
//    }
//

//    private SpringDataJpaMemberRepository repository;
    @Autowired
    MemberService(SpringDataJpaMemberRepository repository) {
        this.repository = repository;
    }

    //private final MemoryMemberRepository repository;

    public void save(MemberRequestDto memberRequestDto) {

        Member member = Member.builder()
                .name(memberRequestDto.getName())
                .email(memberRequestDto.getEmail())
                .password(memberRequestDto.getPassword())
                .build();
        Member newMember = repository.save(member);

        if(newMember.getName().equals("kim")) {
            throw new IllegalArgumentException();
        }

    }

    public List<MemberResponseDto> getAllMemberList() {
        List<Member> members = repository.findAll();
        return members.stream().map(MemberResponseDto::of).collect(Collectors.toList());
    }

    public MemberDetailResponseDto findById(int id) throws EntityNotFoundException {
        Member member = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return MemberDetailResponseDto.of(member);
    }

    public void delete(int id) {
        Member member = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.delete(member);

    }

    public MemberResponseDto update (MemberUpdateDto memberRequestDto) {
        Member member = repository.findById(memberRequestDto.getId()).orElseThrow(EntityNotFoundException::new);

        member = Member.builder()
                .id(member.getId())
                .name(memberRequestDto.getName())
                .password(memberRequestDto.getPassword())
                .email(memberRequestDto.getEmail())
                .create_time(member.getCreate_time())
                .build();

        repository.save(member);
        return MemberResponseDto.of(member);
    }

}
