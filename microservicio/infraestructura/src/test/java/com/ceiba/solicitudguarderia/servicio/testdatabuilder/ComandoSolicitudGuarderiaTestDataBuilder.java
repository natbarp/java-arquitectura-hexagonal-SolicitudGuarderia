package com.ceiba.solicitudguarderia.servicio.testdatabuilder;

import com.ceiba.solicitudguarderia.comando.ComandoSolicitudGuarderia;

import java.time.LocalDateTime;

public class ComandoSolicitudGuarderiaTestDataBuilder {

    private Long id;
    private String nombrePropietario;
    private Long idPropietario;
    private String tipoAnimal;
    private LocalDateTime fechaIngreso;
    private Long diasEstadia;

    public ComandoSolicitudGuarderiaTestDataBuilder() {
        this.nombrePropietario = "test";
        this.idPropietario = 1234L;
        this.tipoAnimal = "PERRO";
        this.fechaIngreso = LocalDateTime.now();
        this.diasEstadia = 7L;
    }

    public ComandoSolicitudGuarderiaTestDataBuilder conIdPropietario(Long idPropietario) {
        this.idPropietario = idPropietario;
        return this;
    }

    public ComandoSolicitudGuarderiaTestDataBuilder conTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
        return this;
    }

    public ComandoSolicitudGuarderiaTestDataBuilder conFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
        return this;
    }

    public ComandoSolicitudGuarderiaTestDataBuilder conDiasEstadia(Long diasEstadia) {
        this.diasEstadia = diasEstadia;
        return this;
    }

    public ComandoSolicitudGuarderia build() {
        return new ComandoSolicitudGuarderia(id,nombrePropietario,
                idPropietario,tipoAnimal, fechaIngreso, diasEstadia);
    }
}
