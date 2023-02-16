package com.thesis.dms.repository;

import com.thesis.dms.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolesRepository extends JpaRepository<RoleEntity, Long> {
    @Query(value = "select r.* from role r"
            + " where true "
            + " and (r.code = :code ) "
            + "limit 1"
            , nativeQuery = true
    )
    RoleEntity checkRoleCode(@Param("code") String code);

    @Query(value = "select r.* from role r"
            + " where true "
            + " and (r.name = :name ) "
            + "limit 1"
            , nativeQuery = true
    )
    RoleEntity checkRoleName(@Param("name") String name);
}
