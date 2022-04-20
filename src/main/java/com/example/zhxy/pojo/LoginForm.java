package com.example.zhxy.pojo;

import lombok.Data;

/**
 * @author JlX
 * @create 2022-04-18 10:37
 */
@Data
public class LoginForm {

    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;

}

