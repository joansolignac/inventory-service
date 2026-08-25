package com.joan.inventoryservice.common.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    protected Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    protected Instant updatedAt;

    @Override
    public boolean equals(Object o) {
        //Same object
        if (this == o) return true;

        //Different objects
        if (o == null || getClass() != o.getClass()) return false;

        //Type Inference
        Auditable auditable = (Auditable) o;

        return this.id != null && this.id.equals(auditable.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
