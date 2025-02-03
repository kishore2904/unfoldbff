package com.unfold.unfoldbff.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String role;
}
