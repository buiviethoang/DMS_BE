package com.thesis.dms.repository;

import com.thesis.dms.entity.auth.TokenPasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TokenPasswordRepository extends JpaRepository<TokenPasswordEntity, Long> {

    TokenPasswordEntity findFirstByToken(String token);

    @Query(value = "select * from token_password n " +
            " where n.user_id = :userId" +
            " and n.expired_date > now() "
            , nativeQuery = true)
    List<TokenPasswordEntity> findByUserId(@Param("userId") long userId);
}
