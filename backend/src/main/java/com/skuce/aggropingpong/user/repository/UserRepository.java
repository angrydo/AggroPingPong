package com.skuce.aggropingpong.user.repository;

import com.skuce.aggropingpong.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // ENTITY #설명필요!!!
    Optional<User> findByUsername(String username);
    List<User> findAllByName(String name, Sort sort);
    Page<User> findAllByName(String name, Pageable pageable);

    // BOOLEAN #설명필요!!!
    Boolean existsByName(String name);
    Boolean existsByUsername(String username);
}
