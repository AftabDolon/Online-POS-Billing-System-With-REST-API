package com.example.Excel.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final Long serialVersionUID = -4196550013222459134L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    public boolean hasId() {
        return id != null && id > 0;
    }
}