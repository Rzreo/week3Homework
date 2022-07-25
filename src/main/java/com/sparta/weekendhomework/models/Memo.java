package com.sparta.weekendhomework.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity
public class Memo extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String desc;


    public Memo(MemoRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.desc = requestDto.getDesc();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
    }

    public void update(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.desc = requestDto.getDesc();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
    }
}
