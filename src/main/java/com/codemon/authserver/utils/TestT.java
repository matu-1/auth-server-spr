package com.codemon.authserver.utils;

import com.codemon.authserver.dtos.CreateRolDto;
import com.codemon.authserver.dtos.CreateUsuarioDto;
import com.codemon.authserver.models.Rol;
import com.codemon.authserver.models.Usuario;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestT {
    public static void main(String[] args) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
       /* CreateUsuarioDto dto = new CreateUsuarioDto();
        dto.setNombre("matias");
        dto.setEmail("matu@gmail");
        Usuario usuario = new Usuario();
        usuario.setNombre("pepe");
        usuario.setEmail("pepe@gmail");
        usuario.setId(5L);
        usuario.setTelefono("777884844");
        usuario.setPassword("pass123");
        usuario.setCreatedAt(new Date());
        System.out.println(dto);
        System.out.println(usuario);
        mapper.map(dto, usuario);
        System.out.println(dto);
        System.out.println(usuario);
        System.out.println(usuario.getId());
        System.out.println(usuario.getCreatedAt()); */
        CreateRolDto dto = new CreateRolDto();
        dto.setNombre("mata");
        ArrayList<Long> permisosId = new ArrayList<>();
        permisosId.add(5L);
        dto.setPermisosId(permisosId);
        Rol rol = mapper.map(dto, Rol.class);
        System.out.println(rol);
    }
}
