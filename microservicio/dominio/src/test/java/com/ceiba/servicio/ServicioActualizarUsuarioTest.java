package com.ceiba.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.solicitudGuarderia.modelo.entidad.SolicitudGuarderia;
import com.ceiba.solicitudGuarderia.puerto.dao.DaoSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.puerto.repositorio.RepositorioSolicitudGuarderia;
import com.ceiba.servicio.testdatabuilder.SolicitudGuarderiaTestDataBuilder;
import com.ceiba.solicitudGuarderia.servicio.ServicioActualizarSolicitudGuarderia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioActualizarUsuarioTest {

    @Test
    @DisplayName("Deberia validar la existencia previa del usuario")
    void deberiaValidarLaExistenciaPreviaDelUsuario() {
        // arrange
        SolicitudGuarderia solicitudGuarderia = new SolicitudGuarderiaTestDataBuilder().conId(1L).build();
        RepositorioSolicitudGuarderia repositorioSolicitudGuarderia = Mockito.mock(RepositorioSolicitudGuarderia.class);
        DaoSolicitudGuarderia daoSolicitudGuarderia = Mockito.mock(DaoSolicitudGuarderia.class);
        Mockito.when(repositorioSolicitudGuarderia.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarSolicitudGuarderia servicioActualizarUsuario = new ServicioActualizarSolicitudGuarderia(repositorioSolicitudGuarderia, daoSolicitudGuarderia);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarUsuario.ejecutar(solicitudGuarderia), ExcepcionDuplicidad.class,"El usuario no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio() {
        // arrange
        SolicitudGuarderia solicitudGuarderia = new SolicitudGuarderiaTestDataBuilder().conId(1L).build();
        RepositorioSolicitudGuarderia repositorioSolicitudGuarderia = Mockito.mock(RepositorioSolicitudGuarderia.class);
        DaoSolicitudGuarderia daoSolicitudGuarderia = Mockito.mock(DaoSolicitudGuarderia.class);
        Mockito.when(repositorioSolicitudGuarderia.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarSolicitudGuarderia servicioActualizarUsuario = new ServicioActualizarSolicitudGuarderia(repositorioSolicitudGuarderia, daoSolicitudGuarderia);
        // act
        servicioActualizarUsuario.ejecutar(solicitudGuarderia);
        //assert
        Mockito.verify(repositorioSolicitudGuarderia,Mockito.times(1)).actualizar(solicitudGuarderia);
    }
}
