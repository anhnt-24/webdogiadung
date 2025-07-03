package com.example.webdogiadung.entity.psql;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@NoArgsConstructor
@Table(name = "category")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryEntity extends BaseEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(unique = true)
    String name;

    String description;

    String thumbnail;

    @Column(columnDefinition = "BOOLEAN DEFAULT false",name = "is_deleted")
    Boolean isDeleted=false;

}
