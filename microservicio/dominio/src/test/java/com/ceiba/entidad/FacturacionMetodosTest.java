package com.ceiba.entidad;

import com.ceiba.servicio.testdatabuilder.FacturacionTestDataBuilder;
import com.ceiba.servicio.testdatabuilder.SolicitudGuarderiaTestDataBuilder;
import com.ceiba.solicitudguarderia.modelo.dto.DtoSolicitudGuarderia;
import com.ceiba.solicitudguarderia.modelo.entidad.Facturacion;
import com.ceiba.solicitudguarderia.modelo.entidad.SolicitudGuarderia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FacturacionMetodosTest {


    @Test
    @DisplayName("Deberia calcular datos de facturacion y retonar objeto de tipo facturacion, " +
            "el enviarse un objeto de tipo solicitudGuarderia y lista de dtosolicitudeGuarderia")
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
        List<DtoSolicitudGuarderia> dtoSolicitudesGuarderia = new ArrayList<>();

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

        Facturacion factura = new Facturacion();

        Facturacion facturacion = factura.calcularDescuentosCostosFacturacion(solicitudGuarderia2,dtoSolicitudesGuarderia);

        //- assert
        assertEquals(facturacionDefault.getDescuento(),facturacion.getDescuento());
        assertEquals(facturacionDefault.getValorDiaRegular(),facturacion.getValorDiaRegular());
        assertEquals(facturacionDefault.getValorDiaFDS(),facturacion.getValorDiaFDS());
        assertEquals(facturacionDefault.getDiasEstadia(),facturacion.getDiasEstadia());
        assertEquals(facturacionDefault.getValorFacturado(),facturacion.getValorFacturado());
    }

    @Test
    @DisplayName("Deberia calcular datos de facturacion y retonar objeto de tipo facturacion, " +
            "el enviarse un objeto de tipo solicitudGuarderia y long de cantidad de registros previos")
    void deberiaCalcularDatosFacturacionYRetornarObjetoFacturacion2() {
        // arrange
        String str = "2022-03-03 17:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        SolicitudGuarderia solicitudGuarderia = new SolicitudGuarderiaTestDataBuilder()
                .conId(1L).conDiasEstadia(4L).conTipoAnimal("GATO").conFechaIngreso(dateTime).build();
        Facturacion facturacionDefault = new FacturacionTestDataBuilder().conDescuento(false).
                conDiasEstadia(4l).conValorDiaRegular(35000.0).conValorDiaFDS(40250.0).conValorFacturado(150500.0).build();

        Facturacion factura = new Facturacion();

        Facturacion facturacion = factura.calcularDescuentosCostosFacturacion(solicitudGuarderia,0L);

        //- assert
        assertEquals(facturacionDefault.getDescuento(),facturacion.getDescuento());
        assertEquals(facturacionDefault.getValorDiaRegular(),facturacion.getValorDiaRegular());
        assertEquals(facturacionDefault.getValorDiaFDS(),facturacion.getValorDiaFDS());
        assertEquals(facturacionDefault.getDiasEstadia(),facturacion.getDiasEstadia());
        assertEquals(facturacionDefault.getValorFacturado(),facturacion.getValorFacturado());
    }

    @Test
    @DisplayName("Deberia generar el valor total facturado")
    void deberiaGeneralElValorTotalFacturado() {
        // arrange
        String str = "2022-03-03 17:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        SolicitudGuarderia solicitudGuarderia = new SolicitudGuarderiaTestDataBuilder()
                .conId(1L).conDiasEstadia(4L).conTipoAnimal("GATO").conFechaIngreso(dateTime).build();

        Facturacion factura = new Facturacion();

        Double totalfacturado = factura.generarValorFacturado(solicitudGuarderia,0.0);

        //- assert
        assertEquals(150500.0,totalfacturado);
    }
}
