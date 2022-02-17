package com.ceiba.servicio.testdatabuilder;

import com.ceiba.solicitudguarderia.modelo.entidad.Facturacion;

public class FacturacionTestDataBuilder {

    private Boolean descuento;
    private Long diasEstadia;
    private Double valorDiaRegular;
    private Double valorDiaFDS;
    private Double valorFacturado;

    public FacturacionTestDataBuilder() {
        this.descuento = false;
        this.diasEstadia = 2L;
        this.valorDiaRegular = 30000.0;
        this.valorDiaFDS = 34500.0;
        this.valorFacturado = 60000.0;
    }


    public FacturacionTestDataBuilder conDescuento(Boolean descuento) {
        this.descuento = descuento;
        return this;
    }

    public FacturacionTestDataBuilder conDiasEstadia(Long diasEstadia) {
        this.diasEstadia = diasEstadia;
        return this;
    }

    public FacturacionTestDataBuilder conValorDiaRegular(Double valorDiaRegular) {
        this.valorDiaRegular = valorDiaRegular;
        return this;
    }

    public FacturacionTestDataBuilder conValorDiaFDS(Double valorDiaFDS) {
        this.valorDiaFDS = valorDiaFDS;
        return this;
    }

    public FacturacionTestDataBuilder conValorFacturado(Double valorFacturado) {
        this.valorFacturado = valorFacturado;
        return this;
    }

    public Facturacion build() {
        return new Facturacion(descuento,diasEstadia,valorDiaRegular,valorDiaFDS,valorFacturado);
    }

}
