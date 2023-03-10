package com.thesis.dms.entity;

import com.thesis.dms.utils.RandomUtils;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    @Column(name = "created_date", columnDefinition = "timestamp")
    private ZonedDateTime createdDate;

    @Column(name = "updated_date", columnDefinition = "timestamp")
    private ZonedDateTime updatedDate;

    @Column(name = "uid")
    private String uid;

    @Column(name = "col_deleted", columnDefinition = "integer default 0")
    private int deleted;

    @PrePersist
    public void prePersist() {
        uid = RandomUtils.getRandomId();
        createdDate = ZonedDateTime.now();
        updatedDate = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = ZonedDateTime.now();
    }
}
