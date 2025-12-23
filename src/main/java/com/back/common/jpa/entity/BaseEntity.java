package com.back.common.jpa.entity;

import com.back.common.config.GlobalConfig;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 베이스 엔티티
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
// 모든 엔티티들의 조상
public class BaseEntity {
    public String getModelTypeCode() {
        return this.getClass().getSimpleName();
    }

    protected void publishEvent(Object event) {
        GlobalConfig.getEventPublisher().publish(event);
    }
}