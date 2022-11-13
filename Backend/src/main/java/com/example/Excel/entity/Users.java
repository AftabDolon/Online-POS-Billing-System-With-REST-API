package com.example.Excel.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users")
public class Users extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "phone")
    private String phone;

    @Column(name = "role")
    private String role;


}
