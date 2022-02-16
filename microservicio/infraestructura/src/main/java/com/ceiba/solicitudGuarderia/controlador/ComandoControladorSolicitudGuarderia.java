package com.ceiba.solicitudGuarderia.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.solicitudGuarderia.modelo.entidad.Facturacion;
import com.ceiba.solicitudGuarderia.comando.ComandoSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.comando.manejador.ManejadorActualizarSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.comando.manejador.ManejadorCrearSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.comando.manejador.ManejadorEliminarSolicitudGuarderia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/registro")
@Api(tags = { "Controlador comando usuario"})
@CrossOrigin(origins = "*")
public class ComandoControladorSolicitudGuarderia {

    private final ManejadorCrearSolicitudGuarderia manejadorCrearSolicitudGuarderia;
	private final ManejadorEliminarSolicitudGuarderia manejadorEliminarSolicitudGuarderia;
	private final ManejadorActualizarSolicitudGuarderia manejadorActualizarSolicitudGuarderia;

    @Autowired
    public ComandoControladorSolicitudGuarderia(ManejadorCrearSolicitudGuarderia manejadorCrearSolicitudGuarderia,
												ManejadorEliminarSolicitudGuarderia manejadorEliminarSolicitudGuarderia,
												ManejadorActualizarSolicitudGuarderia manejadorActualizarSolicitudGuarderia) {
        this.manejadorCrearSolicitudGuarderia = manejadorCrearSolicitudGuarderia;
		this.manejadorEliminarSolicitudGuarderia = manejadorEliminarSolicitudGuarderia;
		this.manejadorActualizarSolicitudGuarderia = manejadorActualizarSolicitudGuarderia;
    }

    @PostMapping("/crear")
    @ApiOperation("Crear Usuario")
    public ComandoRespuesta<Facturacion> crear(@RequestBody ComandoSolicitudGuarderia comandoSolicitudGuarderia) {
        return manejadorCrearSolicitudGuarderia.ejecutar(comandoSolicitudGuarderia);
    }

    @DeleteMapping(value="borrar/{id}")
	@ApiOperation("Eliminar Usuario")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminarSolicitudGuarderia.ejecutar(id);
	}

	@PutMapping(value="actualizar/{id}")
	@ApiOperation("Actualizar Usuario")
	public ComandoRespuesta<Facturacion> actualizar(@RequestBody ComandoSolicitudGuarderia comandoSolicitudGuarderia, @PathVariable Long id) {
		comandoSolicitudGuarderia.setId(id);
		return manejadorActualizarSolicitudGuarderia.ejecutar(comandoSolicitudGuarderia);
	}
}
