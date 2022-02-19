package com.codemon.authserver.controllers;

import com.codemon.authserver.models.Permiso;
import com.codemon.authserver.services.PermisoService;
import com.codemon.authserver.utils.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("permiso")
@Tag(name = "Permiso")
@RequiredArgsConstructor
public class PermisoController {
    final PermisoService permisoService;

    @GetMapping()
    public Response<List<Permiso>> all() {
        List<Permiso> result = this.permisoService.all();
        return new Response(result);
    }
}
