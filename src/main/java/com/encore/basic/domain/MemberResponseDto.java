package com.encore.basic.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MemberResponseDto {
    private int id;
    private String name;
    private String email;
    private String password;

    public static MemberResponseDto of(Member member) {
        MemberResponseDto mrd = MemberResponseDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword())
                .build();
        return mrd;
    }
}
