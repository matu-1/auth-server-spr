package com.codemon.authserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(RolPermisoId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolPermiso {
    @Id
    @Column(name = "rol_id")
    private Long rolId;
    @Id
    @Column(name = "permiso_id")
    private Long permisoId;
    @ManyToOne()
    @JoinColumn(name = "rol_id", insertable = false, updatable = false)
    private Rol rol;
    @ManyToOne()
    @JoinColumn(name = "permiso_id", insertable = false, updatable = false)
    private Permiso permiso;
}
