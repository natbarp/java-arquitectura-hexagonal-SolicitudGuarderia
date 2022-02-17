package com.ceiba.solicitudguarderia.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoSolicitudGuarderia {
    private Long id;
    private String nombrePropietario;
    private Long idPropietario;
    private String tipoAnimal;
    private LocalDateTime fechaIngreso;
    private Long diasEstadia;

}
