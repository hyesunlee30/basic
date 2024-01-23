package com.encore.basic.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Builder
@AllArgsConstructor
//기본 생성자가 필요한 이유는. DB 조회 후 member객체를 만든다.
//New Member 리플랙션 기술을 이용해 만들어 준다. setter 없이도 런타임에 알아서 넣어준다
//조회해서 조립하는 상황에서 쓰려고
@NoArgsConstructor
public class Member {
    @Setter // memory repo
    //GenerationType.auto - jpa구현체가 자동으로 적절한 키생성 전략 선택
    //GeneretionTy[e.identity - auto_increment
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(nullable = false, length = 50)
    private String email;
    private String password;
    @Setter // memory repo
    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime create_time;
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    public Member updateMember(Member member, MemberUpdateDto memberUpdateDto) {

        this.name = memberUpdateDto.getName();
        this.email = memberUpdateDto.getEmail();
        this.password = memberUpdateDto.getPassword();

        return member;
    }

}
