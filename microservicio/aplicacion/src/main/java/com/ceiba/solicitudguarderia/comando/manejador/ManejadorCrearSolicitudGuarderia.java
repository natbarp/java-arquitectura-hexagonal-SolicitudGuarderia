package com.ceiba.solicitudguarderia.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.solicitudguarderia.comando.ComandoSolicitudGuarderia;
import com.ceiba.solicitudguarderia.comando.fabrica.FabricaSolicitudGuarderia;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.solicitudguarderia.modelo.entidad.Facturacion;
import com.ceiba.solicitudguarderia.modelo.entidad.SolicitudGuarderia;
import com.ceiba.solicitudguarderia.servicio.ServicioCrearSolicitudGuarderia;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearSolicitudGuarderia implements ManejadorComandoRespuesta<ComandoSolicitudGuarderia, ComandoRespuesta<Facturacion>> {

    private final FabricaSolicitudGuarderia fabricaSolicitudGuarderia;
    private final ServicioCrearSolicitudGuarderia servicioCrearSolicitudGuarderia;

    public ManejadorCrearSolicitudGuarderia(FabricaSolicitudGuarderia fabricaSolicitudGuarderia, ServicioCrearSolicitudGuarderia servicioCrearSolicitudGuarderia) {
        this.fabricaSolicitudGuarderia = fabricaSolicitudGuarderia;
        this.servicioCrearSolicitudGuarderia = servicioCrearSolicitudGuarderia;
    }

    public ComandoRespuesta<Facturacion> ejecutar(ComandoSolicitudGuarderia comandoSolicitudGuarderia) {
        SolicitudGuarderia solicitudGuarderia = this.fabricaSolicitudGuarderia.crear(comandoSolicitudGuarderia);
        return new ComandoRespuesta<>(this.servicioCrearSolicitudGuarderia.ejecutar(solicitudGuarderia));
    }
}
