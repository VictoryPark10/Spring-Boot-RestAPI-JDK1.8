package com.vicsoft.basicapi8.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfo {

    private long seq;

    private String id;

    private String password;

    private String name;

    private int age;

}
