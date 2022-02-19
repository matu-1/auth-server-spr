package com.codemon.authserver.controllers;

import com.codemon.authserver.dtos.CreateUsuarioDto;
import com.codemon.authserver.dtos.UpdateUsuarioDto;
import com.codemon.authserver.models.Usuario;
import com.codemon.authserver.services.UsuarioService;
import com.codemon.authserver.utils.Permission;
import com.codemon.authserver.utils.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario")
public class UsuarioController {
    final UsuarioService usuarioService;

    @Secured(Permission.USUARIO_LISTAR)
    @GetMapping()
    public Response<List<Usuario>> all(){
        List<Usuario> result = usuarioService.all();
        return new Response(result);
    }

    @Secured(Permission.USUARIO_VISUALIZAR)
    @GetMapping("{id}")
    public Response<Usuario> findById(@PathVariable long id){
        Usuario result = usuarioService.findById(id);
        return new Response(result);
    }

    @Secured(Permission.USUARIO_CREAR)
    @PostMapping
    public Response<Usuario> create(@Valid @RequestBody CreateUsuarioDto body){
        Usuario result = usuarioService.create(body);
        return new Response(result);
    }

    @Secured(Permission.USUARIO_EDITAR)
    @PutMapping("{id}")
    public Response<Usuario> update(@PathVariable long id, @RequestBody UpdateUsuarioDto body){
        Usuario result = usuarioService.update(id, body);
        return new Response(result);
    }

    @Secured(Permission.USUARIO_BORRAR)
    @DeleteMapping("{id}")
    public Response<Usuario> remove(@PathVariable long id){
        Usuario result = usuarioService.remove(id);
        return new Response(result);
    }
}
