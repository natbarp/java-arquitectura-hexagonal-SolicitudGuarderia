package com.ceiba.solicitudguarderia.puerto.repositorio;

import com.ceiba.solicitudguarderia.modelo.entidad.SolicitudGuarderia;

public interface RepositorioSolicitudGuarderia {
    /**
     * Permite crear un usuario
     * @param solicitudGuarderia
     * @return el id generado
     */
    Long crear(SolicitudGuarderia solicitudGuarderia);

    /**
     * Permite actualizar un usuario
     * @param solicitudGuarderia
     */
    void actualizar(SolicitudGuarderia solicitudGuarderia);

    /**
     * Permite eliminar un usuario
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe un usuario con un nombre
     * @param name
     * @return si existe o no
     */
    boolean existe(String name);

    /**
     * Permite validar si existe un usuario con un nombre excluyendo un id
     * @return si existe o no
     */
    boolean existePorId(Long id);


    Long totalDeRegistros(Long id);

}
