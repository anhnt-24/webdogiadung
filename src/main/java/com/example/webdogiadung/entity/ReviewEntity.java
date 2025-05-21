package com.example.webdogiadung.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "review")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewEntity extends BaseEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    Double rating;

    @Column(length = 1000)
    String comment;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

    String clientName;

    String phone;
}
