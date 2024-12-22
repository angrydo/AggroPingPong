package com.skuce.aggropingpong.post.repository;

import com.skuce.aggropingpong.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // JPQL 검색 조회 메서드
    @Query("SELECT p FROM Post p " +
            "WHERE (:title IS NULL OR LOWER(p.title) LIKE CONCAT('%', LOWER(:title), '%')) " +
            "AND (:content IS NULL OR LOWER(p.content) LIKE CONCAT('%', LOWER(:content), '%')) " +
            "AND (:sCreatedDate IS NULL OR p.createdDate >= :sCreatedDate) " +
            "AND (:eCreatedDate IS NULL OR p.createdDate <= :eCreatedDate) " +
            "AND (:sUpdatedDate IS NULL OR p.updatedDate >= :sUpdatedDate) " +
            "AND (:eUpdatedDate IS NULL OR p.updatedDate <= :eUpdatedDate)")
    List<Post> searchBy(@Param("title") String title,
                           @Param("content") String content,
                           @Param("sCreatedDate") LocalDateTime sCreatedDate,
                           @Param("eCreatedDate") LocalDateTime eCreatedDate,
                           @Param("sUpdatedDate") LocalDateTime sUpdatedDate,
                           @Param("eUpdatedDate") LocalDateTime eUpdatedDate);
}
