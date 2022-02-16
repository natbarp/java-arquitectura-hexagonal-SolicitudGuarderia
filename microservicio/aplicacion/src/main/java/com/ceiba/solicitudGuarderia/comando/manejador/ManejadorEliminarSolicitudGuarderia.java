package com.ceiba.solicitudGuarderia.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.solicitudGuarderia.servicio.ServicioEliminarSolicitudGuarderia;
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
