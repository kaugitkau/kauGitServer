package com.example.Kau_Git.entity.common;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {
    //생성 날짜
    @CreatedDate
    private LocalDateTime createdAt;
    //수정 날짜
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
