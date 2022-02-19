package com.codemon.authserver.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateRolDto {
    @NotEmpty
    @Size(max = 50)
    private String nombre;
    private String descripcion;
    @NotEmpty
    private List<Long> permisosId;
}
