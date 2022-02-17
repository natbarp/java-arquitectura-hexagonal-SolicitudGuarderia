package com.ceiba.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.servicio.testdatabuilder.SolicitudGuarderiaTestDataBuilder;
import com.ceiba.solicitudguarderia.modelo.entidad.SolicitudGuarderia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolicitudGuarderiaTest {

    @Test
    @DisplayName("Deberia crear correctamente el usuario")
    void deberiaCrearCorrectamenteElUsusuario() {
        // arrange
        String str = "2022-03-03 17:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        //act
        SolicitudGuarderia solicitudGuarderia = new SolicitudGuarderiaTestDataBuilder().conFechaIngreso(dateTime).conId(1L).build();
        //assert
        assertEquals(1, solicitudGuarderia.getId());
        assertEquals("test", solicitudGuarderia.getNombrePropietario());
        assertEquals(1234, solicitudGuarderia.getIdPropietario());
        assertEquals("PERRO", solicitudGuarderia.getTipoAnimal());
        assertEquals(dateTime, solicitudGuarderia.getFechaIngreso());
        assertEquals(2, solicitudGuarderia.getDiasEstadia());
    }

    @Test
    void deberiaFallarSinNombreDeUsuario() {

        //Arrange
        SolicitudGuarderiaTestDataBuilder solicitudGuarderiaTestDataBuilder = new SolicitudGuarderiaTestDataBuilder().conIdPropietario(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    solicitudGuarderiaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la identificación del usuario");
    }

    @Test
    void deberiaFallarSinFechaIngreso() {

        //Arrange
        SolicitudGuarderiaTestDataBuilder solicitudGuarderiaTestDataBuilder = new SolicitudGuarderiaTestDataBuilder().conFechaIngreso(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    solicitudGuarderiaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de ingreso");
    }

    @Test
    @DisplayName("Deberia fallar cuando la longitud de la clave sea mayor a 10")
    void deberiaFallarCuandoLaLongitudDeLalaveEsMayorA10() {
        // arrange
        SolicitudGuarderiaTestDataBuilder solicitudGuarderiaTestDataBuilder = new SolicitudGuarderiaTestDataBuilder().conIdPropietario(11221332167L);
        // act - assert

        BasePrueba.assertThrows(() -> {
                    solicitudGuarderiaTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "La identificación debe tener una longitud máxima de 10 digitos");
    }

    @Test
    void deberiaFallarSinDiasDeEstadia() {

        //Arrange
        SolicitudGuarderiaTestDataBuilder solicitudGuarderiaTestDataBuilder = new SolicitudGuarderiaTestDataBuilder().conDiasEstadia(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    solicitudGuarderiaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el numero de días de estadía");
    }

    @Test
    void deberiaFallarSinTipoAnimal() {

        //Arrange
        SolicitudGuarderiaTestDataBuilder solicitudGuarderiaTestDataBuilder = new SolicitudGuarderiaTestDataBuilder().conTipoAnimal(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    solicitudGuarderiaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el tipo de animal");
    }

    @Test
    void deberiaFallarConTipoAnimalDiferenteACondicion() {

        //Arrange
        SolicitudGuarderiaTestDataBuilder solicitudGuarderiaTestDataBuilder = new SolicitudGuarderiaTestDataBuilder().conTipoAnimal("RATON");
        //act-assert
        BasePrueba.assertThrows(() -> {
                    solicitudGuarderiaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El tipo de animal no es válido");
    }


}
