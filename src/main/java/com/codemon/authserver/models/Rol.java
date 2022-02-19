package com.codemon.authserver.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Rol extends BaseEntity {
    @Column(length = 50)
    private String nombre;
    private String descripcion;
}
