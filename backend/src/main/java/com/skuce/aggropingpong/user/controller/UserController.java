package com.skuce.aggropingpong.user.controller;

import com.skuce.aggropingpong.common.dto.CommonResponse;
import com.skuce.aggropingpong.user.dto.UserCreateRequestDto;
import com.skuce.aggropingpong.user.dto.UserResponseDto;
import com.skuce.aggropingpong.user.dto.UserUpdateRequestDto;
import com.skuce.aggropingpong.user.service.UserWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
    public class UserController {

    private final UserWriteService userWriteService;

    /** 회원가입 API */
    @PostMapping("/api/v1/user/create")
    public ResponseEntity<CommonResponse<?>> createUser(@RequestBody UserCreateRequestDto requestDto) {
        UserResponseDto responseDto = userWriteService.create(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    /** 회원정보수정 API */
    @PostMapping("/api/v1/user/update")
    public ResponseEntity<CommonResponse<?>> updateUser(@RequestBody UserUpdateRequestDto requestDto) {
        UserResponseDto responseDto = userWriteService.update(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }

    /** 회원정보삭제 API */
    @DeleteMapping("/api/v1/user/delete")
    public ResponseEntity<CommonResponse<?>> deleteUser(@PathVariable Long id) {
        UserResponseDto responseDto = userWriteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.success(responseDto));
    }
}
