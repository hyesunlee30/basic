package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberUpdateDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface MemberRepository {


    Member save(Member member);

    List<Member> findAll();

    Optional<Member> findById(int id);

    void delete(Member member);

}
