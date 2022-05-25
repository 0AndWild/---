package com.mymy.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Blog extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private  String title;

    @Column(nullable = false)
    private  String password;



    public Blog(String username, String contents, String title, String password) {
        this.username = username;
        this.contents = contents;
        this.title = title; //제목 추가
        this.password = password; // 비밀번호 추가
    }

    public Blog(BlogRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle(); //추가
        this.password = requestDto.getPassword(); //추가

    }

    public void update(BlogRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();

        //밑에 항목 추가하니 업데이트 항목이 안들어오면 업데이트 테스트시 get에서 ""비어있는 항목으로 바뀌어버려서 제거
//        this.title = requestDto.getTitle(); //추가
//        this.password = requestDto.getPassword(); //추가
    }

//    public void  (BlogRequestDto requestDto) {
//        this.username = requestDto.getUsername();
//        this.contents = requestDto.getContents();
//    }
}
