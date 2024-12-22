package com.skuce.aggropingpong.user.service;

import com.skuce.aggropingpong.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserReadService {
    private final UserRepository userRepository;
}
