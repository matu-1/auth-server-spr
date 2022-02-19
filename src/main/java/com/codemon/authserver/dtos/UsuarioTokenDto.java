package com.codemon.authserver.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class UsuarioTokenDto {
    private Long id;
    private String nombre;
    private String email;
    private Long rolId;
    private Date createdAt;
    private String accessToken;
}
