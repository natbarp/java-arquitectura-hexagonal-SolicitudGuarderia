package com.ceiba.solicitudGuarderia.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.solicitudGuarderia.comando.ComandoSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.comando.fabrica.FabricaSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.modelo.entidad.Facturacion;
import com.ceiba.solicitudGuarderia.modelo.entidad.SolicitudGuarderia;
import com.ceiba.solicitudGuarderia.servicio.ServicioActualizarSolicitudGuarderia;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarSolicitudGuarderia implements ManejadorComandoRespuesta<ComandoSolicitudGuarderia, ComandoRespuesta<Facturacion>> {

    private final FabricaSolicitudGuarderia fabricaSolicitudGuarderia;
    private final ServicioActualizarSolicitudGuarderia servicioActualizarSolicitudGuarderia;

    public ManejadorActualizarSolicitudGuarderia(FabricaSolicitudGuarderia fabricaSolicitudGuarderia, ServicioActualizarSolicitudGuarderia servicioActualizarSolicitudGuarderia) {
        this.fabricaSolicitudGuarderia = fabricaSolicitudGuarderia;
        this.servicioActualizarSolicitudGuarderia = servicioActualizarSolicitudGuarderia;
    }

    public ComandoRespuesta<Facturacion> ejecutar(ComandoSolicitudGuarderia comandoSolicitudGuarderia) {
        SolicitudGuarderia solicitudGuarderia = this.fabricaSolicitudGuarderia.crear(comandoSolicitudGuarderia);
        return new ComandoRespuesta<>(this.servicioActualizarSolicitudGuarderia.ejecutar(solicitudGuarderia));
    }
}
