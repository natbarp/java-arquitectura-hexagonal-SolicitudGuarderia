package com.ceiba.solicitudguarderia.servicio;

import com.ceiba.solicitudguarderia.modelo.entidad.Facturacion;
import com.ceiba.solicitudguarderia.modelo.entidad.SolicitudGuarderia;
import com.ceiba.solicitudguarderia.puerto.repositorio.RepositorioSolicitudGuarderia;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

import java.time.DayOfWeek;
import java.time.LocalDateTime;



public class ServicioCrearSolicitudGuarderia {

    private static final int CANTIDAD_DE_REGISTROS = 5;
    public static final double DESCUENTO_POR_MASCOTA = 0.02;
    public static final double COSTO_DIA_PERRO = 30000.0;
    public static final double COSTO_DIA_GATO = 35000.0;
    public static final double PORCENTAJE_AUMENTO_DIA_FDS = 0.15;
    private static final String EL_USUARIO_SUPERA_LOS_REGISTROS_PERMITIDOS = String.format("El usuario supera los registros permitidos: %s",CANTIDAD_DE_REGISTROS);

    private final RepositorioSolicitudGuarderia repositorioSolicitudGuarderia;

    public ServicioCrearSolicitudGuarderia(RepositorioSolicitudGuarderia repositorioSolicitudGuarderia) {
        this.repositorioSolicitudGuarderia = repositorioSolicitudGuarderia;
    }

    public Facturacion ejecutar(SolicitudGuarderia solicitudGuarderia) {
        Double porcentajeDescuento = Double.valueOf(cantidadDeRegistrosPrevios(solicitudGuarderia))*DESCUENTO_POR_MASCOTA;
        String tipoAnimal = solicitudGuarderia.getTipoAnimal().toUpperCase();
        
        this.repositorioSolicitudGuarderia.crear(solicitudGuarderia);
        
        return constructorDeRespuesta(porcentajeDescuento, tipoAnimal, 
                solicitudGuarderia.getFechaIngreso(), solicitudGuarderia.getDiasEstadia());
    }

    private Facturacion constructorDeRespuesta(Double porcentajeDescuento, String tipoAnimal, LocalDateTime fechaIngreso, Long diasEstadia){
            Double valorDiaRegular = 0.0;
            Double valorDiaFDS = 0.0;
            switch (tipoAnimal){
                case "PERRO":
                    valorDiaRegular = COSTO_DIA_PERRO-(COSTO_DIA_PERRO*porcentajeDescuento);
                    break;
                case "GATO":
                    valorDiaRegular = COSTO_DIA_GATO-(COSTO_DIA_GATO*porcentajeDescuento);
            }
            valorDiaFDS = valorDiaRegular + valorDiaRegular*PORCENTAJE_AUMENTO_DIA_FDS;
            Double valorFacturado = generarValorTotal(valorDiaRegular, valorDiaFDS, fechaIngreso, diasEstadia);

            return new Facturacion(
                    (porcentajeDescuento>0)?true:false,
                    diasEstadia,
                    valorDiaRegular,
                    valorDiaFDS,
                    valorFacturado
            );
    }


    private Long cantidadDeRegistrosPrevios(SolicitudGuarderia solicitudGuarderia) {
        Long totalRegistros = this.repositorioSolicitudGuarderia.totalDeRegistros(solicitudGuarderia.getIdPropietario());
        if(totalRegistros>=CANTIDAD_DE_REGISTROS) {
            throw new ExcepcionDuplicidad(EL_USUARIO_SUPERA_LOS_REGISTROS_PERMITIDOS);
        }
        return totalRegistros;
    }

    public Double generarValorTotal(Double valorDiaRegular, Double valorDiaFDS, LocalDateTime fechaIngreso, Long days) {
        Double sumarDias = 0.0;
        int addedDays = 0;
        while (addedDays < days) {
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
