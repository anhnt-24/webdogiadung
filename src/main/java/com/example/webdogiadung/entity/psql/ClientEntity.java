package com.example.webdogiadung.entity.psql;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@NoArgsConstructor
@Table(name = "client")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientEntity extends BaseEntity<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name", nullable = false)
    String name;

    String email;

    @Column(name = "phone", nullable = false)
    String phone;

    @Column(name = "total_spent")
    Double totalSpent;

    @Column(name = "num_order")
    Long numOrder;
}
