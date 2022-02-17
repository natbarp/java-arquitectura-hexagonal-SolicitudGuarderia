package com.ceiba.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.servicio.testdatabuilder.FacturacionTestDataBuilder;
import com.ceiba.servicio.testdatabuilder.SolicitudGuarderiaTestDataBuilder;
import com.ceiba.solicitudguarderia.modelo.entidad.Facturacion;
import com.ceiba.solicitudguarderia.modelo.entidad.SolicitudGuarderia;
import com.ceiba.solicitudguarderia.puerto.repositorio.RepositorioSolicitudGuarderia;
import com.ceiba.solicitudguarderia.servicio.ServicioCrearSolicitudGuarderia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearUsuarioTest {

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando la cantidad de registros por usuario supere los 5")
    void deberiaLanzarUnaExepcionCuandoCantidadDeRegistrosPorUsuarioSupereLos5() {
        // arrange
        SolicitudGuarderia solicitudGuarderia = new SolicitudGuarderiaTestDataBuilder().build();
        RepositorioSolicitudGuarderia repositorioSolicitudGuarderia = Mockito.mock(RepositorioSolicitudGuarderia.class);
        ServicioCrearSolicitudGuarderia servicioCrearSolicitudGuarderia = new ServicioCrearSolicitudGuarderia(repositorioSolicitudGuarderia);
        Mockito.when(repositorioSolicitudGuarderia.totalDeRegistros(Mockito.anyLong())).thenReturn(5L);
        //act - assert
        BasePrueba.assertThrows(() -> servicioCrearSolicitudGuarderia.ejecutar(solicitudGuarderia), ExcepcionDuplicidad.class,"El usuario supera los registros permitidos: 5");
    }

    @Test
    @DisplayName("Deberia Crear el usuario de manera correcta llamando al repositorio y retornando un obj tipo factura")
    void deberiaCrearElUsuarioDeManeraCorrecta() {
        // arrange
        SolicitudGuarderia solicitudGuarderia = new SolicitudGuarderiaTestDataBuilder().build();
        Facturacion facturacionDefault = new FacturacionTestDataBuilder().build();
        RepositorioSolicitudGuarderia repositorioSolicitudGuarderia = Mockito.mock(RepositorioSolicitudGuarderia.class);

        Mockito.when(repositorioSolicitudGuarderia.totalDeRegistros(Mockito.anyLong())).thenReturn(0L);
        Mockito.when(repositorioSolicitudGuarderia.crear(solicitudGuarderia)).thenReturn(1L);

        ServicioCrearSolicitudGuarderia servicioCrearSolicitudGuarderia = new ServicioCrearSolicitudGuarderia(repositorioSolicitudGuarderia);
        // act
        Facturacion facturacion = servicioCrearSolicitudGuarderia.ejecutar(solicitudGuarderia);

        //- assert
        assertEquals(facturacionDefault,facturacion);
        Mockito.verify(repositorioSolicitudGuarderia, Mockito.times(1)).crear(solicitudGuarderia);
    }
}
