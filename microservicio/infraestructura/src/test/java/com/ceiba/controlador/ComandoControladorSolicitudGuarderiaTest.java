package com.ceiba.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.solicitudGuarderia.comando.ComandoSolicitudGuarderia;
import com.ceiba.servicio.testdatabuilder.ComandoSolicitudGuarderiaTestDataBuilder;
import com.ceiba.solicitudGuarderia.controlador.ComandoControladorSolicitudGuarderia;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorSolicitudGuarderia.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorSolicitudGuarderiaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia crear un solicitud guarderia")
    void deberiaCrearUnaSolicitudGuarderia() throws Exception{
        // arrange
        String str = "2022-03-03 11:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        ComandoSolicitudGuarderia solicitudGuarderia = new ComandoSolicitudGuarderiaTestDataBuilder()
                .conIdPropietario(123456L)
                .conTipoAnimal("PERRO")
                .conFechaIngreso(dateTime)
                .conDiasEstadia(5L)
                .build();

        // act - assert
        mocMvc.perform(post("/registro/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitudGuarderia)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor.descuento", is(false)))
                .andExpect(jsonPath("$.valor.diasEstadia", is(5)))
                .andExpect(jsonPath("$.valor.valorDiaRegular", is(30000.0)))
                .andExpect(jsonPath("$.valor.valorDiaFDS", is(34500.0)))
                .andExpect(jsonPath("$.valor.valorFacturado", is(159000.0)))
                .andReturn();
    }

    @Test
    @DisplayName("Deberia indicar que no es posible crear un solicitud guarderia con un mismo usuario mas de cinco veces")
    void deberiaIndicarQueNoEsPosibleCrearUnaSolicitudGuarderiaConUnMismoUsuarioMasDeCincoVeces() throws Exception{
        // arrange
        String str = "2022-03-03 11:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        final int CANTIDAD_DE_REGISTROS = 5;

        ComandoSolicitudGuarderia solicitudGuarderia = new ComandoSolicitudGuarderiaTestDataBuilder()
                .conIdPropietario(123456L)
                .conTipoAnimal("PERRO")
                .conFechaIngreso(dateTime)
                .conDiasEstadia(5L)
                .build();

        // act - assert
        for (int i = 0; i<CANTIDAD_DE_REGISTROS;i++){
            mocMvc.perform(post("/registro/crear")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(solicitudGuarderia)))
                    .andExpect(status().isOk());
        }

        mocMvc.perform(post("/registro/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitudGuarderia)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje", is("El usuario supera los registros permitidos: 5")));
    }

    @Test
    @DisplayName("Deberia crear segundo y tercer registro con descuento")
    void deberiaCrearSegundoYTercerRegistroConDescuento() throws Exception{
        // arrange
        String str = "2022-03-03 11:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        ComandoSolicitudGuarderia solicitudGuarderia = new ComandoSolicitudGuarderiaTestDataBuilder()
                .conIdPropietario(123456L)
                .conTipoAnimal("PERRO")
                .conFechaIngreso(dateTime)
                .conDiasEstadia(5L)
                .build();

        // act - assert
        mocMvc.perform(post("/registro/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitudGuarderia)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor.descuento", is(false)))
                .andExpect(jsonPath("$.valor.diasEstadia", is(5)))
                .andExpect(jsonPath("$.valor.valorDiaRegular", is(30000.0)))
                .andExpect(jsonPath("$.valor.valorDiaFDS", is(34500.0)))
                .andExpect(jsonPath("$.valor.valorFacturado", is(159000.0)))
                .andReturn();

        mocMvc.perform(post("/registro/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitudGuarderia)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor.descuento", is(true)))
                .andExpect(jsonPath("$.valor.diasEstadia", is(5)))
                .andExpect(jsonPath("$.valor.valorDiaRegular", is(29400.0)))
                .andExpect(jsonPath("$.valor.valorDiaFDS", is(33810.0)))
                .andExpect(jsonPath("$.valor.valorFacturado", is(155820.0)))
                .andReturn();

        mocMvc.perform(post("/registro/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitudGuarderia)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor.descuento", is(true)))
                .andExpect(jsonPath("$.valor.diasEstadia", is(5)))
                .andExpect(jsonPath("$.valor.valorDiaRegular", is(28800.0)))
                .andExpect(jsonPath("$.valor.valorDiaFDS", is(33120.0)))
                .andExpect(jsonPath("$.valor.valorFacturado", is(152640.0)))
                .andReturn();
    }


    @Test
    @DisplayName("Deberia crear segundo registro con descuento y tercero sin descuento")
    void deberiaCrearSegundoRegistroConDescuentoYTerceroSinDescuento() throws Exception{
        // arrange
        String str = "2022-03-03 11:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        ComandoSolicitudGuarderia solicitudGuarderia = new ComandoSolicitudGuarderiaTestDataBuilder()
                .conIdPropietario(123456L)
                .conTipoAnimal("PERRO")
                .conFechaIngreso(dateTime)
                .conDiasEstadia(5L)
                .build();
        ComandoSolicitudGuarderia solicitudGuarderia2 = new ComandoSolicitudGuarderiaTestDataBuilder()
                .conIdPropietario(12345678L)
                .conTipoAnimal("GATO")
                .conFechaIngreso(dateTime)
                .conDiasEstadia(7L)
                .build();

        // act - assert
        mocMvc.perform(post("/registro/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitudGuarderia)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor.descuento", is(false)))
                .andExpect(jsonPath("$.valor.diasEstadia", is(5)))
                .andExpect(jsonPath("$.valor.valorDiaRegular", is(30000.0)))
                .andExpect(jsonPath("$.valor.valorDiaFDS", is(34500.0)))
                .andExpect(jsonPath("$.valor.valorFacturado", is(159000.0)))
                .andReturn();

        mocMvc.perform(post("/registro/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitudGuarderia)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor.descuento", is(true)))
                .andExpect(jsonPath("$.valor.diasEstadia", is(5)))
                .andExpect(jsonPath("$.valor.valorDiaRegular", is(29400.0)))
                .andExpect(jsonPath("$.valor.valorDiaFDS", is(33810.0)))
                .andExpect(jsonPath("$.valor.valorFacturado", is(155820.0)))
                .andReturn();

        mocMvc.perform(post("/registro/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitudGuarderia2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor.descuento", is(false)))
                .andExpect(jsonPath("$.valor.diasEstadia", is(7)))
                .andExpect(jsonPath("$.valor.valorDiaRegular", is(35000.0)))
                .andExpect(jsonPath("$.valor.valorDiaFDS", is(40250.0)))
                .andExpect(jsonPath("$.valor.valorFacturado", is(255500.0)))
                .andReturn();
    }

    @Test
    @DisplayName("Deberia actualizar un usuario")
    void deberiaActualizarUnUsuarioyRecalcularFactura() throws Exception{
        // arrange
        Long id = 1L;
        String str = "2022-03-03 11:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        ComandoSolicitudGuarderia solicitudGuarderia = new ComandoSolicitudGuarderiaTestDataBuilder()
                .conIdPropietario (1234l)
                .conTipoAnimal("GATO")
                .conFechaIngreso(dateTime)
                .conDiasEstadia(3L)
                .build();

        // act - assert
        mocMvc.perform(put("/registro/actualizar/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitudGuarderia)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor.descuento", is(false)))
                .andExpect(jsonPath("$.valor.diasEstadia", is(3)))
                .andExpect(jsonPath("$.valor.valorDiaRegular", is(35000.0)))
                .andExpect(jsonPath("$.valor.valorDiaFDS", is(40250.0)))
                .andExpect(jsonPath("$.valor.valorFacturado", is(110250.0)))
                .andReturn();
    }

    @Test
    @DisplayName("Deberia actualizar un usuario")
    void deberiaActualizarUsuarioConVariosRegistrosRespetandoSiHayONoDescuentoAnterior() throws Exception{
        // arrange
        Long id = 1L;
        final int CANTIDAD_DE_REGISTROS = 4;
        String str = "2022-03-03 11:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        ComandoSolicitudGuarderia solicitudGuarderia = new ComandoSolicitudGuarderiaTestDataBuilder()
                .conIdPropietario (1234l)
                .conTipoAnimal("GATO")
                .conFechaIngreso(dateTime)
                .conDiasEstadia(3L)
                .build();

        // act - assert
        for (int i = 0; i<CANTIDAD_DE_REGISTROS;i++){
            mocMvc.perform(post("/registro/crear")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(solicitudGuarderia)))
                    .andExpect(status().isOk());
        }

        mocMvc.perform(put("/registro/actualizar/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(solicitudGuarderia)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor.descuento", is(false)))
                .andExpect(jsonPath("$.valor.diasEstadia", is(3)))
                .andExpect(jsonPath("$.valor.valorDiaRegular", is(35000.0)))
                .andExpect(jsonPath("$.valor.valorDiaFDS", is(40250.0)))
                .andExpect(jsonPath("$.valor.valorFacturado", is(110250.0)))
                .andReturn();
    }


    @Test
    @DisplayName("Deberia eliminar un usuario")
    void deberiaEliminarUnUsuario() throws Exception {
        // arrange
        Long id = 1L;
        // act - assert
        mocMvc.perform(delete("/registro/borrar/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mocMvc.perform(get("/listarRegistrosGuarderia")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("Deberia indicar que no es posible eliminar un usuario porque no existe ")
    void deberiaIndicarQueNoEsPosibleEliminarUnUsuarioPorqueNoExiste() throws Exception {
        // arrange
        Long id = 1L;
        // act - assert
        mocMvc.perform(delete("/registro/borrar/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mocMvc.perform(delete("/registro/borrar/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje", is("El registro no existe en el sistema")));
    }

}
