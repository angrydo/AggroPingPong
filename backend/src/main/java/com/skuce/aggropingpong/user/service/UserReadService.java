package com.skuce.aggropingpong.user.service;

import com.skuce.aggropingpong.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserReadService {
    private final UserRepository userRepository;
}
