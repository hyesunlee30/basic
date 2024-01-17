package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberUpdateDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class JpaMemberRepository implements MemberRepository{
    //EntityManager는 jpa의 핵심클래스(객체)
    //Entity의 생명주기를 관리. 데이터베이스으

    private final EntityManager entityManager;

    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) {
        //persist : 영속하게 했다. 저장했다. entityManager의 관리상태가 되도록 만들어주고
        //트랜잭션이 커밋될 때 데이터베이스에 저장한다.

        entityManager.persist(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        //jdbc -> jpa -> spring data tpa
        //jpa의 객체지향 jpql 쿼리 문법이다
        //장점은 DB에 따라 문법이 달라지지 않는 객체지향 언어이다. SpringDataJpa의 @Query기술을 쓰면 컴파일 타임에서 check()
        //단점은 DB 고유의 기능과 성능을 극대화하기는 어렵다
        String sql = "select m from Member m";
        List<Member> members = entityManager.createQuery(sql, Member.class).getResultList();
        return members;
    }

    @Override
    public Optional<Member> findById(int id) {
        //pk 값으로 조회
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public void delete(Member id) {
        //delete의 경우 remove 메서드 사용
        //update의 경우 merge 메서드 사용
    }

    public List<Member> findByName(String name) {
        String sql = "select m from Member m where m.name = :name";
        List<Member> memberList = entityManager.createQuery(sql, Member.class)
                .setParameter("name",name).getResultList();
        return memberList;
    }
}
