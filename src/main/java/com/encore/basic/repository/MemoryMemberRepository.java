package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private final List<Member> memberList = new ArrayList<>();

    static int id =0;

    @Override
    public Member save(Member member) {
        LocalDateTime currentDate = LocalDateTime.now();
        member.setId(id);
        member.setCreate_time(currentDate);
        memberList.add(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        return memberList;
    }

    @Override
    public Optional<Member> findById(int id) {
        return memberList.stream().filter(a->a.getId()==id).findFirst();
    }
}
