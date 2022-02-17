package com.ceiba.servicio.testdatabuilder;

import com.ceiba.solicitudguarderia.modelo.entidad.SolicitudGuarderia;

import java.time.LocalDateTime;

public class SolicitudGuarderiaTestDataBuilder {

    private Long id;
    private String nombrePropietario;
    private Long idPropietario;
    private String tipoAnimal;
    private LocalDateTime fechaIngreso;
    private Long diasEstadia;

    public SolicitudGuarderiaTestDataBuilder() {
        this.nombrePropietario = "test";
        this.idPropietario = 1234L;
        this.tipoAnimal = "PERRO";
        this.fechaIngreso = LocalDateTime.now();
        this.diasEstadia = 2L;
    }

    public SolicitudGuarderiaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public SolicitudGuarderiaTestDataBuilder conNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
        return this;
    }

    public SolicitudGuarderiaTestDataBuilder conIdPropietario(Long idPropietario) {
        this.idPropietario = idPropietario;
        return this;
    }

    public SolicitudGuarderiaTestDataBuilder conTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
        return this;
    }

    public SolicitudGuarderiaTestDataBuilder conFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
        return this;
    }

    public SolicitudGuarderiaTestDataBuilder conDiasEstadia(Long diasEstadia) {
        this.diasEstadia = diasEstadia;
        return this;
    }

    public SolicitudGuarderia build() {
        return new SolicitudGuarderia(id,nombrePropietario,
                idPropietario,tipoAnimal, fechaIngreso, diasEstadia);
    }
}
