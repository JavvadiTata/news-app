package com.my_projects.news_app_v1.models;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginCredentials {

    private String email;
    private String password;

}