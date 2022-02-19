package com.codemon.authserver.controllers;

import com.codemon.authserver.dtos.CreateRolDto;
import com.codemon.authserver.dtos.UpdateRolDto;
import com.codemon.authserver.models.Permiso;
import com.codemon.authserver.models.Rol;
import com.codemon.authserver.services.RolService;
import com.codemon.authserver.utils.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("rol")
@RequiredArgsConstructor
@Tag(name = "Rol")
public class RolController {
    final RolService rolService;

    @GetMapping()
    public Response<List<Rol>> all(){
        List<Rol> result = rolService.all();
        return new Response(result);
    }

    @GetMapping("{id}")
    public Response<Rol> findById(@PathVariable long id){
        Rol result = rolService.findById(id);
        return new Response(result);
    }

    @PostMapping
    public Response<Rol> create(@Valid @RequestBody CreateRolDto body){
        Rol result = rolService.createWithPermisos(body);
        return new Response(result);
    }

    @PutMapping("{id}")
    public Response<Rol> update(@PathVariable long id, @RequestBody UpdateRolDto body){
        Rol result = rolService.updateWithPermisos(id, body);
        return new Response(result);
    }

    @DeleteMapping("{id}")
    public Response<Rol> remove(@PathVariable long id){
        Rol result = rolService.remove(id);
        return new Response(result);
    }

    @GetMapping("permiso/{id}")
    public Response<List<Permiso>> findPermisos(@PathVariable Long id){
        List<Permiso> result = rolService.findPermisos(id);
        return new Response(result);
    }
}
