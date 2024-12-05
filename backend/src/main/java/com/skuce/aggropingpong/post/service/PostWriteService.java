package com.skuce.aggropingpong.post.service;

import com.skuce.aggropingpong.common.exception.CustomNotFoundException;
import com.skuce.aggropingpong.common.exception.CustomValidateException;
import com.skuce.aggropingpong.post.domain.Post;
import com.skuce.aggropingpong.post.dto.PostCreateRequestDto;
import com.skuce.aggropingpong.post.dto.PostDetailResponseDto;
import com.skuce.aggropingpong.post.dto.PostUpdateRequestDto;
import com.skuce.aggropingpong.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostWriteService {

    private final PostRepository postRepository;

    public PostDetailResponseDto create(PostCreateRequestDto requestDto) {
        if (requestDto.getTitle() == null || requestDto.getTitle().isEmpty())
            throw new CustomValidateException("The post title must not be empty.");
        if (requestDto.getContent() == null || requestDto.getContent().isEmpty())
            throw new CustomValidateException("The post content must not be empty.");
        return new PostDetailResponseDto(postRepository.save(requestDto.toEntity()));
    }

    public PostDetailResponseDto update(PostUpdateRequestDto requestDto) {
        if (requestDto.getTitle() == null || requestDto.getTitle().isEmpty())
            throw new CustomValidateException("The post title must not be empty.");
        if (requestDto.getContent() == null || requestDto.getContent().isEmpty())
            throw new CustomValidateException("The post content must not be empty.");

        Post entity = postRepository.findById(requestDto.getId()).orElseThrow(
                () -> new CustomNotFoundException("Post not found with id: " + requestDto.getId()));
        return new PostDetailResponseDto(postRepository.save(entity.update(requestDto)));
    }

    public PostDetailResponseDto delete(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(
                () -> new CustomNotFoundException("Post not found with id: " + id));
        postRepository.deleteById(id);
        return new PostDetailResponseDto(entity);
    }
}
