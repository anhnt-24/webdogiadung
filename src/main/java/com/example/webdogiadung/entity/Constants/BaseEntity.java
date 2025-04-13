package com.example.webdogiadung.entity.Constants;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@EntityListeners(BaseEntity.class)
@JsonIgnoreProperties(value = {"createdAt","updatedAt"}, allowGetters = false)
public abstract class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    public abstract Long getId();
}
