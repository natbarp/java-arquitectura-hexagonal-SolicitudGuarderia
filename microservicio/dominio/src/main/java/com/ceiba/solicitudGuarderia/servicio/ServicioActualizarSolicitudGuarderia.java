package com.ceiba.solicitudGuarderia.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.solicitudGuarderia.modelo.dto.DtoSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.modelo.entidad.Facturacion;
import com.ceiba.solicitudGuarderia.modelo.entidad.SolicitudGuarderia;
import com.ceiba.solicitudGuarderia.puerto.dao.DaoSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.puerto.repositorio.RepositorioSolicitudGuarderia;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import static com.ceiba.solicitudGuarderia.servicio.ServicioCrearSolicitudGuarderia.*;

public class ServicioActualizarSolicitudGuarderia {

    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El usuario no existe en el sistema";

    private final RepositorioSolicitudGuarderia repositorioSolicitudGuarderia;
    private final DaoSolicitudGuarderia daoSolicitudGuarderia;

    public ServicioActualizarSolicitudGuarderia(RepositorioSolicitudGuarderia repositorioSolicitudGuarderia, DaoSolicitudGuarderia daoSolicitudGuarderia) {
        this.repositorioSolicitudGuarderia = repositorioSolicitudGuarderia;
        this.daoSolicitudGuarderia =  daoSolicitudGuarderia;
    }

    public Facturacion ejecutar(SolicitudGuarderia solicitudGuarderia) {
        validarExistenciaPrevia(solicitudGuarderia);
        Facturacion facturacion = calcularDescuentosCostosFacturacion(solicitudGuarderia);
        this.repositorioSolicitudGuarderia.actualizar(solicitudGuarderia);
        return facturacion;
    }

    private void validarExistenciaPrevia(SolicitudGuarderia solicitudGuarderia) {
        boolean existe = this.repositorioSolicitudGuarderia.existePorId(solicitudGuarderia.getId());
        if(!existe) {
            throw new ExcepcionDuplicidad(EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private Facturacion calcularDescuentosCostosFacturacion(SolicitudGuarderia solicitudGuarderia){
        int contador = 0;
        int posicionRegistro = 0;
        List<DtoSolicitudGuarderia> dtoSolicitudGuarderia = this.daoSolicitudGuarderia.listarPorIdPropietario(solicitudGuarderia.getIdPropietario());
        for (DtoSolicitudGuarderia solicitud:dtoSolicitudGuarderia) {
            if(solicitud.getId() == solicitudGuarderia.getId()){
                posicionRegistro = contador;
            }
            contador++;
        }
        int cantidadSolicitudes = dtoSolicitudGuarderia.size();
        Double porcentajeDescuento = posicionRegistro*DESCUENTO_POR_MASCOTA;
        Double valorDiaRegular = 0.0;
        Double valorDiaFDS = 0.0;
        switch (solicitudGuarderia.getTipoAnimal()){
            case "PERRO":
                valorDiaRegular = COSTO_DIA_PERRO-(COSTO_DIA_PERRO*porcentajeDescuento);
                break;
            case "GATO":
                valorDiaRegular = COSTO_DIA_GATO-(COSTO_DIA_GATO*porcentajeDescuento);
        }
        valorDiaFDS = valorDiaRegular + valorDiaRegular*PORCENTAJE_AUMENTO_DIA_FDS;
        Double valorFacturado = generarValorFacturado(valorDiaRegular, valorDiaFDS, solicitudGuarderia.getFechaIngreso(), solicitudGuarderia.getDiasEstadia());

        return new Facturacion(
                (porcentajeDescuento>0)?true:false,
                solicitudGuarderia.getDiasEstadia(),
                valorDiaRegular,
                valorDiaFDS,
                valorFacturado
        );
    }

    public Double generarValorFacturado(Double valorDiaRegular, Double valorDiaFDS, LocalDateTime fechaIngreso, Long days) {
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
