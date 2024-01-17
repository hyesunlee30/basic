package com.encore.basic.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberRequestDto {
    private String name;
    private String email;
    private String password;
}
