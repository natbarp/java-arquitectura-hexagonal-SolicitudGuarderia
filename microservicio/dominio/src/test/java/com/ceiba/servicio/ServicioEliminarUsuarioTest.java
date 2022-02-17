package com.ceiba.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.solicitudguarderia.puerto.repositorio.RepositorioSolicitudGuarderia;
import com.ceiba.solicitudguarderia.servicio.ServicioEliminarSolicitudGuarderia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarUsuarioTest {

    @Test
    @DisplayName("Deberia eliminar el usuario llamando al repositorio")
    void deberiaEliminarElUsuarioLlamandoAlRepositorio() {
        // arrange
        RepositorioSolicitudGuarderia repositorioSolicitudGuarderia = Mockito.mock(RepositorioSolicitudGuarderia.class);
        ServicioEliminarSolicitudGuarderia servicioEliminarSolicitudGuarderia = new ServicioEliminarSolicitudGuarderia(repositorioSolicitudGuarderia);
        Mockito.when(repositorioSolicitudGuarderia.existePorId(1L)).thenReturn(true);
        // act - assert
        servicioEliminarSolicitudGuarderia.ejecutar(1l);
        Mockito.verify(repositorioSolicitudGuarderia, Mockito.times(1)).eliminar(1l);
    }

    @Test
    @DisplayName("Deberia validar la existencia previa del usuario")
    void deberiaValidarLaExistenciaPreviaDelUsuario() {
        // arrange
        RepositorioSolicitudGuarderia repositorioSolicitudGuarderia = Mockito.mock(RepositorioSolicitudGuarderia.class);
        Mockito.when(repositorioSolicitudGuarderia.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioEliminarSolicitudGuarderia servicioEliminarSolicitudGuarderia = new ServicioEliminarSolicitudGuarderia(repositorioSolicitudGuarderia);

        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarSolicitudGuarderia.ejecutar(1L), ExcepcionValorInvalido.class,"El registro no existe en el sistema");
    }

}
