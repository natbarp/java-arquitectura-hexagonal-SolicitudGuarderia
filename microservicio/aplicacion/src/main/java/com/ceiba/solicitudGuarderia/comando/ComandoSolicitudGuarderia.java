package com.ceiba.solicitudGuarderia.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudGuarderia {

    private Long id;
    private String nombrePropietario;
    private Long idPropietario;
    private String tipoAnimal;
    private LocalDateTime fechaIngreso;
    private Long diasEstadia;

}
