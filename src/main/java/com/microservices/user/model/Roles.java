package com.microservices.user.model;

import com.microservices.user.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles implements Serializable {

    private static final long serialVersionUID = 123;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    private Boolean isDeleted;
}
