package com.encore.basic.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MemberDetailResponseDto {
    private String name;
    private String email;
    private String password;
    private LocalDateTime create_time;

    public static MemberDetailResponseDto of(Member member) {
        MemberDetailResponseDto mrd = MemberDetailResponseDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword())
                .create_time(member.getCreate_time())
                .build();
        return mrd;
    }
}
