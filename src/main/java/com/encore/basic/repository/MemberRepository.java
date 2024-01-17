package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface MemberRepository {


    Member save(Member member);

    List<Member> findAll();

    Optional<Member> findById(int id);
}
