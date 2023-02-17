package com.thesis.dms.repository;

import com.thesis.dms.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    @Query(value = "select p.* from permission p"
            + " where true "
            + " and (p.name = :name ) "
            + "limit 1"
            , nativeQuery = true
    )
    PermissionEntity getPermissionByName(@Param("name") String name);
    @Query(value = "select p.* from permission p"
            + " where true "
            + " and (p.value = :value ) "
            + "limit 1"
            , nativeQuery = true
    )
    PermissionEntity getPermissionByValue(@Param("value") Long value);
}
