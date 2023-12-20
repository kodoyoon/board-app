package com.sparta.boardapp.dto;

import com.sparta.boardapp.entity.PostEntity;

import java.time.LocalDateTime;

public record PostResponseDto( //record 는 getter 를 사용하지 않아요. 기본적으로  private final 이 되어 있기 때문에 접근제어자 생략가능, 자체적으로 접근가능
     Long id,
     String title,
     String author,
     String content,
     LocalDateTime createdAt //작성일
) {
    public PostResponseDto(PostEntity savePost) { //오버로딩된 생성자
        this(
                savePost.getId(),
                savePost.getTitle(),
                savePost.getAuthor(),
                savePost.getContents(),
                savePost.getCreatedAt()
        );
    }
}
