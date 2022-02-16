package com.ceiba.solicitudGuarderia.comando.fabrica;

import com.ceiba.solicitudGuarderia.comando.ComandoSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.modelo.entidad.SolicitudGuarderia;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudGuarderia {

    public SolicitudGuarderia crear(ComandoSolicitudGuarderia comandoSolicitudGuarderia) {
        return new SolicitudGuarderia(
                comandoSolicitudGuarderia.getId(),
                comandoSolicitudGuarderia.getNombrePropietario(),
                comandoSolicitudGuarderia.getIdPropietario(),
                comandoSolicitudGuarderia.getTipoAnimal(),
                comandoSolicitudGuarderia.getFechaIngreso(),
                comandoSolicitudGuarderia.getDiasEstadia()
        );
    }

}
