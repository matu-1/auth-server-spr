package com.codemon.authserver.dtos;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CreateUsuarioDto {
    @NotEmpty
    @Size(max = 100)
    private String nombre;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    @Size(max = 10)
    private String telefono;
    @NotNull
    @Min(value = 1)
    private Long rolId;
}
