package com.codemon.authserver.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class UpdateUsuarioDto {
    @Size(max = 100)
    private String nombre;
    @Email
    private String email;
    private String password;
    @Size(max = 10)
    private String telefono;
    @Min(value = 1)
    private Long rolId;
}
