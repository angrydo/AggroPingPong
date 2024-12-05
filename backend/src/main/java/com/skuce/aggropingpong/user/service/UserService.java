package com.skuce.aggropingpong.user.service;

import com.skuce.aggropingpong.user.dto.UserRequestDto;
import com.skuce.aggropingpong.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void create(UserRequestDto userRequestDto) {
        //수정필요!!!
    }
    @Transactional
    public void update( userRequestDto RequestDto) {

    }
}
