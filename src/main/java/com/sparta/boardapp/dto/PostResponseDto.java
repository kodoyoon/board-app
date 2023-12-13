package com.sparta.boardapp.dto;

import com.sparta.boardapp.entity.PostEntity;

import java.time.LocalDateTime;

public record PostResponseDto( //record 는 getter 를 사용하지 않아요.
     Long id,
     String title,
     String author,
     String content,
     LocalDateTime createdAt
) {
    public PostResponseDto(PostEntity savePost) {
        this(
                savePost.getId(),
                savePost.getTitle(),
                savePost.getAuthor(),
                savePost.getContents(),
                savePost.getCreatedAt()
        );
    }
}
