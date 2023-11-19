package com.sparta.boardapp.service;

import com.sparta.boardapp.controller.exception.PostNotFoundException;
import com.sparta.boardapp.dto.PostAddRequestDto;
import com.sparta.boardapp.dto.PostResponseDto;
import com.sparta.boardapp.dto.PostUpdateRequestDto;
import com.sparta.boardapp.controller.exception.AuthorizeException;
import com.sparta.boardapp.entity.PostEntity;
import com.sparta.boardapp.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;

    public PostResponseDto addPost(PostAddRequestDto requestDto) {
        // Dto -> Entity
        PostEntity postEntity = new PostEntity(requestDto);
        PostEntity savePost = postJpaRepository.save(postEntity);
        return new PostResponseDto(savePost);
    }

    public PostResponseDto getPost(Long postId) {
        PostEntity postEntity = getPostEntity(postId);
        return new PostResponseDto(postEntity);
    }

    public List<PostResponseDto> getPosts() {
        return postJpaRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto) {
        PostEntity postEntity = getPostEntity(postId);
        verifyPassword(postEntity, requestDto.getPassword());
        postEntity.update(requestDto);
        return new PostResponseDto(postEntity);
    }

    public void deletePost(Long postId, String password) {
        PostEntity postEntity = getPostEntity(postId);
        verifyPassword(postEntity, password);
        postJpaRepository.delete(postEntity);
    }

    private PostEntity getPostEntity(Long postId) {
        return postJpaRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("해당 게시글을 찾을 수 없습니다."));
    }

    private static void verifyPassword(PostEntity postEntity, String password) {
        if (!postEntity.passwordMatches(password)) {
            throw new AuthorizeException("비밀번호가 일치하지 않습니다.");
        }
    }
}
