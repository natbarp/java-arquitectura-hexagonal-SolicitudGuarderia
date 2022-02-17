package com.ceiba.solicitudguarderia.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.solicitudguarderia.servicio.ServicioEliminarSolicitudGuarderia;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarSolicitudGuarderia implements ManejadorComando<Long> {

    private final ServicioEliminarSolicitudGuarderia servicioEliminarSolicitudGuarderia;

    public ManejadorEliminarSolicitudGuarderia(ServicioEliminarSolicitudGuarderia servicioEliminarSolicitudGuarderia) {
        this.servicioEliminarSolicitudGuarderia = servicioEliminarSolicitudGuarderia;
    }

    public void ejecutar(Long idSolicitudGuarderia) {
        this.servicioEliminarSolicitudGuarderia.ejecutar(idSolicitudGuarderia);
    }
}
