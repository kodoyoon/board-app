package com.sparta.boardapp.dto;

import lombok.Getter;

@Getter
public class PostAddRequestDto {
    private String title;
    private String author;
    private String password;
    private String content;
}
