package com.codemon.authserver.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Permiso extends BaseEntity {
    @Column(length = 80)
    private String nombre;
    private String descripcion;
}
