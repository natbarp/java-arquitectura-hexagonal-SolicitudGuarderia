package com.ceiba.solicitudguarderia.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.configuracion.solicitudguarderia.controlador.ConsultaControladorSolicitudGuarderia;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorSolicitudGuarderia.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorSolicitudGuarderiaTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia listar usuarios")
    void deberiaListarUsuarios() throws Exception {
        // arrange
        // act - assert

        mocMvc.perform(get("/listarRegistrosGuarderia")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombrePropietario", is("test")))
                .andExpect(jsonPath("$[0].idPropietario", is(1234)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].tipoAnimal", is("PERRO")))
                .andExpect(jsonPath("$[0].fechaIngreso", is("2022-03-03 17:00:00")))
                .andExpect(jsonPath("$[0].diasEstadia", is(2)));
    }

    @Test
    @DisplayName("Deberia listar usuarios")
    void deberiaListarUsuariosPorIdentificacion() throws Exception {
        // arrange
        Long id = 1234L;
        // act - assert

        mocMvc.perform(get("/listarRegistrosGuarderia/{id}",id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombrePropietario", is("test")))
                .andExpect(jsonPath("$[0].idPropietario", is(1234)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].tipoAnimal", is("PERRO")))
                .andExpect(jsonPath("$[0].fechaIngreso", is("2022-03-03 17:00:00")))
                .andExpect(jsonPath("$[0].diasEstadia", is(2)));
    }
}
