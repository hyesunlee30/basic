package com.encore.basic.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberUpdateDto {
    private int id;
    private String name;
    private String email;
    private String password;
}
