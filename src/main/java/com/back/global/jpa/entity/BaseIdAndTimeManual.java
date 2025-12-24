package com.back.global.jpa.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Auto-Increment와 자동생성을 걸지 않는 베이스 엔티티
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseIdAndTimeManual extends BaseEntity{

    @Id
    private int id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
