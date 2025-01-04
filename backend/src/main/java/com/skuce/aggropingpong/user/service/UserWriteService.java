package com.skuce.aggropingpong.user.service;

import com.skuce.aggropingpong.common.exception.CustomNotFoundException;
import com.skuce.aggropingpong.user.domain.User;
import com.skuce.aggropingpong.user.dto.UserCreateRequestDto;
import com.skuce.aggropingpong.user.dto.UserResponseDto;
import com.skuce.aggropingpong.user.dto.UserUpdateRequestDto;
import com.skuce.aggropingpong.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserWriteService {

    private final UserRepository userRepository;

    public UserResponseDto create(UserCreateRequestDto requestDto) {
        return new UserResponseDto(userRepository.save(requestDto.toEntity()));
    }

    public UserResponseDto update(UserUpdateRequestDto responseDto) {
        return new UserResponseDto(userRepository.save(responseDto.toEntity()));
    }

    public UserResponseDto delete(Long id) {
        User entity = userRepository.findById(id).orElseThrow(
                () -> new CustomNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
        return new UserResponseDto(entity);
    }
}
