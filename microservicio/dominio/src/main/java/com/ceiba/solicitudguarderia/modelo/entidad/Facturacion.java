package com.ceiba.solicitudguarderia.modelo.entidad;


import com.ceiba.solicitudguarderia.modelo.dto.DtoSolicitudGuarderia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Facturacion {
    private Boolean descuento;
    private Long diasEstadia;
    private Double valorDiaRegular;
    private Double valorDiaFDS;
    private Double valorFacturado;

    private static final double DESCUENTO_POR_MASCOTA = 0.02;
    private static final double COSTO_DIA_PERRO = 30000.0;
    private static final double COSTO_DIA_GATO = 35000.0;
    private static final double PORCENTAJE_AUMENTO_DIA_FDS = 0.15;

    public Facturacion calcularDescuentosCostosFacturacion(SolicitudGuarderia solicitudGuarderia, List<DtoSolicitudGuarderia> dtoSolicitudGuarderia){
        int contador = 0;
        int posicionRegistro = 0;
        for (DtoSolicitudGuarderia solicitud:dtoSolicitudGuarderia) {
            if(solicitud.getId().equals(solicitudGuarderia.getId())){
                posicionRegistro = contador;
            }
            contador++;
        }

        Double porcentajeDescuento = posicionRegistro*DESCUENTO_POR_MASCOTA;

        valorFacturado = generarValorFacturado(solicitudGuarderia,porcentajeDescuento);

        return new Facturacion(
                (porcentajeDescuento>0),
                solicitudGuarderia.getDiasEstadia(),
                valorDiaRegular,
                valorDiaFDS,
                valorFacturado
        );
    }

    public Facturacion calcularDescuentosCostosFacturacion(SolicitudGuarderia solicitudGuarderia, Long totalRegistros){

        Double porcentajeDescuento = Double.valueOf(totalRegistros)*DESCUENTO_POR_MASCOTA;
        valorFacturado = generarValorFacturado(solicitudGuarderia,porcentajeDescuento);

        return new Facturacion(
                (porcentajeDescuento>0),
                solicitudGuarderia.getDiasEstadia(),
                valorDiaRegular,
                valorDiaFDS,
                valorFacturado
        );
    }

    public Double generarValorFacturado(SolicitudGuarderia solicitudGuarderia, Double porcentajeDescuento) {
        LocalDateTime fechaIngreso = solicitudGuarderia.getFechaIngreso();
        Double sumarDias = 0.0;
        String tipoAnimal = solicitudGuarderia.getTipoAnimal().toUpperCase();

        if ("PERRO".equals(tipoAnimal)) {
            valorDiaRegular = COSTO_DIA_PERRO - (COSTO_DIA_PERRO * porcentajeDescuento);
        } else if ("GATO".equals(tipoAnimal)) {
            valorDiaRegular = COSTO_DIA_GATO - (COSTO_DIA_GATO * porcentajeDescuento);
        }
        valorDiaFDS = valorDiaRegular + valorDiaRegular*PORCENTAJE_AUMENTO_DIA_FDS;

        int addedDays = 0;
        while (addedDays < solicitudGuarderia.getDiasEstadia()) {
            if (!(fechaIngreso.getDayOfWeek() == DayOfWeek.SATURDAY || fechaIngreso.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                sumarDias = sumarDias + valorDiaRegular;
            }else {
                sumarDias = sumarDias + valorDiaFDS;
            }
            fechaIngreso = fechaIngreso.plusDays(1);
            ++addedDays;
        }
        return sumarDias;
    }
}


