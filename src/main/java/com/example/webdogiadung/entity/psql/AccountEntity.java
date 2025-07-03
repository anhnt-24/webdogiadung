package com.example.webdogiadung.entity.psql;

import com.example.webdogiadung.constants.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@NoArgsConstructor
@Table(name = "account")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity extends BaseEntity <String>{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String phone;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;

    @Column(name = "avatar_url")
    String avatarUrl;


}
