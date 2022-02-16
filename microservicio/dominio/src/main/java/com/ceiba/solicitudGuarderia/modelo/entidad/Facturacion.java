package com.ceiba.solicitudGuarderia.modelo.entidad;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Facturacion {
    private Boolean descuento;
    private Long diasEstadia;
    private Double valorDiaRegular;
    private Double valorDiaFDS;
    private Double valorFacturado;
}
