package com.ceiba.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.servicio.testdatabuilder.FacturacionTestDataBuilder;
import com.ceiba.servicio.testdatabuilder.SolicitudGuarderiaTestDataBuilder;
import com.ceiba.solicitudguarderia.modelo.dto.DtoSolicitudGuarderia;
import com.ceiba.solicitudguarderia.modelo.entidad.Facturacion;
import com.ceiba.solicitudguarderia.modelo.entidad.SolicitudGuarderia;
import com.ceiba.solicitudguarderia.puerto.dao.DaoSolicitudGuarderia;
import com.ceiba.solicitudguarderia.puerto.repositorio.RepositorioSolicitudGuarderia;
import com.ceiba.solicitudguarderia.servicio.ServicioActualizarSolicitudGuarderia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @DisplayName("Deberia actualizar correctamente en el repositorio con tipo animal perro")
    void deberiaActualizarCorrectamenteEnElRepositorioEditandoAnimalYDiasEstadia() {
        // arrange
        SolicitudGuarderia solicitudGuarderia = new SolicitudGuarderiaTestDataBuilder()
                .conId(1L).conDiasEstadia(4L).build();
        RepositorioSolicitudGuarderia repositorioSolicitudGuarderia = Mockito.mock(RepositorioSolicitudGuarderia.class);
        DaoSolicitudGuarderia daoSolicitudGuarderia = Mockito.mock(DaoSolicitudGuarderia.class);
        Mockito.when(repositorioSolicitudGuarderia.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarSolicitudGuarderia servicioActualizarUsuario = new ServicioActualizarSolicitudGuarderia(repositorioSolicitudGuarderia, daoSolicitudGuarderia);
        // act
        servicioActualizarUsuario.ejecutar(solicitudGuarderia);
        //assert
        Mockito.verify(repositorioSolicitudGuarderia,Mockito.times(1)).actualizar(solicitudGuarderia);
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio con tipo Animal gato")
    void deberiaActualizarCorrectamenteEnElRepositorioEditandoAnimalYDiasEstadia2() {
        // arrange
        SolicitudGuarderia solicitudGuarderia = new SolicitudGuarderiaTestDataBuilder()
                .conId(1L).conDiasEstadia(4L).conTipoAnimal("GATO").build();
        RepositorioSolicitudGuarderia repositorioSolicitudGuarderia = Mockito.mock(RepositorioSolicitudGuarderia.class);
        DaoSolicitudGuarderia daoSolicitudGuarderia = Mockito.mock(DaoSolicitudGuarderia.class);
        Mockito.when(repositorioSolicitudGuarderia.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarSolicitudGuarderia servicioActualizarUsuario = new ServicioActualizarSolicitudGuarderia(repositorioSolicitudGuarderia, daoSolicitudGuarderia);
        // act
        servicioActualizarUsuario.ejecutar(solicitudGuarderia);
        //assert
        Mockito.verify(repositorioSolicitudGuarderia,Mockito.times(1)).actualizar(solicitudGuarderia);
    }

    @Test
    @DisplayName("Deberia calcular datos de facturacion y retonar objeto de tipo facturacion")
    void deberiaCalcularDatosFacturacionYRetornarObjetoFacturacion() {
        // arrange
        String str = "2022-03-03 17:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        SolicitudGuarderia solicitudGuarderia = new SolicitudGuarderiaTestDataBuilder()
                .conId(1L).conDiasEstadia(4L).conTipoAnimal("GATO").conFechaIngreso(dateTime).build();
        SolicitudGuarderia solicitudGuarderia2 = new SolicitudGuarderiaTestDataBuilder()
                .conId(2L).conDiasEstadia(4L).conTipoAnimal("GATO").conFechaIngreso(dateTime).build();
        Facturacion facturacionDefault = new FacturacionTestDataBuilder().conDescuento(true).
                conDiasEstadia(4l).conValorDiaRegular(34300.0).conValorDiaFDS(39445.0).conValorFacturado(147490.0).build();
        List<DtoSolicitudGuarderia> dtoSolicitudesGuarderia = new ArrayList<DtoSolicitudGuarderia>();

        dtoSolicitudesGuarderia.add(new DtoSolicitudGuarderia(solicitudGuarderia.getId(),
                solicitudGuarderia.getNombrePropietario(),
                solicitudGuarderia.getIdPropietario(),
                solicitudGuarderia.getTipoAnimal(),
                solicitudGuarderia.getFechaIngreso(),
                solicitudGuarderia.getDiasEstadia()));
        dtoSolicitudesGuarderia.add(new DtoSolicitudGuarderia(solicitudGuarderia2.getId(),
                solicitudGuarderia2.getNombrePropietario(),
                solicitudGuarderia2.getIdPropietario(),
                solicitudGuarderia2.getTipoAnimal(),
                solicitudGuarderia2.getFechaIngreso(),
                solicitudGuarderia2.getDiasEstadia()));

        RepositorioSolicitudGuarderia repositorioSolicitudGuarderia = Mockito.mock(RepositorioSolicitudGuarderia.class);
        DaoSolicitudGuarderia daoSolicitudGuarderia = Mockito.mock(DaoSolicitudGuarderia.class);
        ServicioActualizarSolicitudGuarderia servicioActualizarSolicitudGuarderia = new ServicioActualizarSolicitudGuarderia(repositorioSolicitudGuarderia, daoSolicitudGuarderia);

        Facturacion facturacion = servicioActualizarSolicitudGuarderia.calcularDescuentosCostosFacturacion(solicitudGuarderia2,dtoSolicitudesGuarderia);

        Mockito.when(repositorioSolicitudGuarderia.existePorId(Mockito.anyLong())).thenReturn(true);

        //- assert
        assertEquals(facturacionDefault,facturacion);
//        Mockito.verify(repositorioSolicitudGuarderia, Mockito.times(1)).actualizar(solicitudGuarderia);
    }
}
