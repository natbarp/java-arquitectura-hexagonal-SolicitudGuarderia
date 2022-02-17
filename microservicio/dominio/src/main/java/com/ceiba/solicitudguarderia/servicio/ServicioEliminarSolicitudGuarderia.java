package com.ceiba.solicitudguarderia.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.solicitudguarderia.puerto.repositorio.RepositorioSolicitudGuarderia;

public class ServicioEliminarSolicitudGuarderia {

    private static final String EL_REGISTRO_NO_EXISTE_EN_EL_SISTEMA = "El registro no existe en el sistema";

    private final RepositorioSolicitudGuarderia repositorioSolicitudGuarderia;

    public ServicioEliminarSolicitudGuarderia(RepositorioSolicitudGuarderia repositorioSolicitudGuarderia) {
        this.repositorioSolicitudGuarderia = repositorioSolicitudGuarderia;
    }

    public void ejecutar(Long id) {
        validarExistenciaPrevia(id);
        this.repositorioSolicitudGuarderia.eliminar(id);
    }

    private void validarExistenciaPrevia(Long id) {
        boolean existe = this.repositorioSolicitudGuarderia.existePorId(id);
        if(!existe) {
            throw new ExcepcionValorInvalido(EL_REGISTRO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
