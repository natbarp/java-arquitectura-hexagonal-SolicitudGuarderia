package com.ceiba.configuracion.solicitudguarderia.controlador;

import com.ceiba.solicitudguarderia.consulta.ManejadorListarSolicitudGuarderia;
import com.ceiba.solicitudguarderia.modelo.dto.DtoSolicitudGuarderia;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/listarRegistrosGuarderia")
@Api(tags={"Controlador consulta usuario"})
@CrossOrigin(origins = "*")
public class ConsultaControladorSolicitudGuarderia {

    private final ManejadorListarSolicitudGuarderia manejadorListarSolicitudGuarderia;

    public ConsultaControladorSolicitudGuarderia(ManejadorListarSolicitudGuarderia manejadorListarSolicitudGuarderia) {
        this.manejadorListarSolicitudGuarderia = manejadorListarSolicitudGuarderia;
    }

    @GetMapping
    @ApiOperation("Listar Usuarios")
    public List<DtoSolicitudGuarderia> listar() {
        return this.manejadorListarSolicitudGuarderia.ejecutar();
    }

    @GetMapping(value="/{idPropietario}")
    @ApiOperation("Listar Usuarios por id")
    public List<DtoSolicitudGuarderia> listar(@PathVariable Long idPropietario) {
        return this.manejadorListarSolicitudGuarderia.ejecutarPorIdPropietario(idPropietario);
    }

}
