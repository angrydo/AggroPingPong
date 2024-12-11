package com.skuce.aggropingpong.post.controller;

import com.skuce.aggropingpong.common.dto.CommonResponse;
import com.skuce.aggropingpong.post.dto.PostCreateRequestDto;
import com.skuce.aggropingpong.post.dto.PostDetailResponseDto;
import com.skuce.aggropingpong.post.dto.PostSimpleResponseDto;
import com.skuce.aggropingpong.post.dto.PostUpdateRequestDto;
import com.skuce.aggropingpong.post.service.PostReadService;
import com.skuce.aggropingpong.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostReadService postReadService;
    private final PostWriteService postWriteService;

    @GetMapping("/api/v1/post/detail/{id}")
    public ResponseEntity<CommonResponse<?>> getPost(@PathVariable Long id) {
        PostDetailResponseDto responseDto = postReadService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @GetMapping("/api/v1/post/list")
    public ResponseEntity<CommonResponse<?>> getPosts() {
        List<PostSimpleResponseDto> responseDto = postReadService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @PostMapping("/api/v1/post/search")
    public ResponseEntity<CommonResponse<?>> searchPosts(@RequestBody Map<String, Object> params) {
        List<PostSimpleResponseDto> responseDto = postReadService.searchBy(params);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @PostMapping("/api/v1/post/create")
    public ResponseEntity<CommonResponse<?>> createPost(@RequestBody PostCreateRequestDto requestDto) {
        PostDetailResponseDto responseDto = postWriteService.create(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @PutMapping("/api/v1/post/update")
    public ResponseEntity<CommonResponse<?>> updatePost(@RequestBody PostUpdateRequestDto requestDto) {
        PostDetailResponseDto responseDto = postWriteService.update(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    @DeleteMapping("/api/v1/post/delete/{id}")
    public ResponseEntity<CommonResponse<?>> deletePost(@PathVariable Long id) {
        PostDetailResponseDto responseDto = postWriteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }
}
