package com.thesis.dms.repository;

import com.thesis.dms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "select u.* from users u" +
            " where true" +
            " and UPPER(u.username) = UPPER(:username)  " +
            " limit 1"
            , nativeQuery = true)
    UserEntity findByUsername(@Param("username") String username);

    @Query(value = "select * from users u where true " + " and u.email = :email limit 1", nativeQuery = true)
    UserEntity findByEmail(@Param("email") String email);

    @Query(value = "select u.* from users u" +
            " where true" +
            " and u.phone = :phone" +
            " limit 1"
            , nativeQuery = true)
    UserEntity findByPhone(@Param("phone") String phone);
}
