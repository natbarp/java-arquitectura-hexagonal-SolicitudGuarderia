package com.ceiba.solicitudguarderia.comando.fabrica;

import com.ceiba.solicitudguarderia.comando.ComandoSolicitudGuarderia;
import com.ceiba.solicitudguarderia.modelo.entidad.SolicitudGuarderia;
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
