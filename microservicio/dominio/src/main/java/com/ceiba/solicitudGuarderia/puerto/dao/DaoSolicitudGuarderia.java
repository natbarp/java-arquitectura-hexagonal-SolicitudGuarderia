package com.ceiba.solicitudGuarderia.puerto.dao;

import com.ceiba.solicitudGuarderia.modelo.dto.DtoSolicitudGuarderia;

import java.util.List;

public interface DaoSolicitudGuarderia {

    /**
     * Permite listar usuarios
     * @return los usuarios
     */
    List<DtoSolicitudGuarderia> listar();

    List<DtoSolicitudGuarderia> listarPorIdPropietario(Long idPropietario);

}
