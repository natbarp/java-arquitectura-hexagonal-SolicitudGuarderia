package com.ceiba.configuracion;

import com.ceiba.solicitudGuarderia.puerto.dao.DaoSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.puerto.repositorio.RepositorioSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.servicio.ServicioActualizarSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.servicio.ServicioCrearSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.servicio.ServicioEliminarSolicitudGuarderia;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearSolicitudGuarderia servicioCrearUsuario(RepositorioSolicitudGuarderia repositorioUsuario) {
        return new ServicioCrearSolicitudGuarderia(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarSolicitudGuarderia servicioEliminarUsuario(RepositorioSolicitudGuarderia repositorioUsuario) {
        return new ServicioEliminarSolicitudGuarderia(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarSolicitudGuarderia servicioActualizarUsuario(RepositorioSolicitudGuarderia repositorioUsuario, DaoSolicitudGuarderia daoSolicitudGuarderia) {
        return new ServicioActualizarSolicitudGuarderia(repositorioUsuario, daoSolicitudGuarderia);
    }
	

}
