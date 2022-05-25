package com.mymy.blog.domain;

import lombok.Getter;

@Getter
public class BlogRequestDto { // RequestDto는 필요한 정보를 몰고다니는 녀석
    //수정요청이 왔으면 일단 두가지 정보가 필요 //유져 이름과, 내용(contents)
    private String username;
    private String contents;
    private String title; //제목 추가
    private String password; //비밀번호 추가
}
