package com.codemon.authserver.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CreatePermisoDto {
    @NotEmpty
    @Size(max = 100)
    private String nombre;
    private String descripcion;
}
