package com.thesis.dms.repository;

import com.thesis.dms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "select u.* from tbl_user u" +
            " inner join tbl_profiles p on p.id=u.col_profile_id" +
            " where true" +
            " and UPPER(p.col_username) = UPPER(:username)  " +
            " limit 1"
            , nativeQuery = true)
    UserEntity findByUsername(@Param("username") String username);
}
