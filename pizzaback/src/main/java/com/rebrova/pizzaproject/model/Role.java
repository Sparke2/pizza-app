package com.rebrova.pizzaproject.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "roleUser")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "flag")
    private Integer flag;
}
