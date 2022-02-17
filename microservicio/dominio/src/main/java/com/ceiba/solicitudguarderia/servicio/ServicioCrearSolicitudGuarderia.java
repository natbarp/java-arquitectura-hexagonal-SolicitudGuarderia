package com.ceiba.solicitudguarderia.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.solicitudguarderia.modelo.entidad.Facturacion;
import com.ceiba.solicitudguarderia.modelo.entidad.SolicitudGuarderia;
import com.ceiba.solicitudguarderia.puerto.repositorio.RepositorioSolicitudGuarderia;



public class ServicioCrearSolicitudGuarderia {

    private static final int CANTIDAD_DE_REGISTROS = 5;
    private static final String EL_USUARIO_SUPERA_LOS_REGISTROS_PERMITIDOS = String.format("El usuario supera los registros permitidos: %s",CANTIDAD_DE_REGISTROS);

    private final RepositorioSolicitudGuarderia repositorioSolicitudGuarderia;

    public ServicioCrearSolicitudGuarderia(RepositorioSolicitudGuarderia repositorioSolicitudGuarderia) {
        this.repositorioSolicitudGuarderia = repositorioSolicitudGuarderia;
    }

    public Facturacion ejecutar(SolicitudGuarderia solicitudGuarderia) {
        Long totalRegistros = cantidadDeRegistrosPrevios(solicitudGuarderia);
        this.repositorioSolicitudGuarderia.crear(solicitudGuarderia);
        Facturacion factura = new Facturacion();
        return factura.calcularDescuentosCostosFacturacion(solicitudGuarderia,totalRegistros);
    }

    private Long cantidadDeRegistrosPrevios(SolicitudGuarderia solicitudGuarderia) {
        Long totalRegistros = this.repositorioSolicitudGuarderia.totalDeRegistros(solicitudGuarderia.getIdPropietario());
        if(totalRegistros>=CANTIDAD_DE_REGISTROS) {
            throw new ExcepcionDuplicidad(EL_USUARIO_SUPERA_LOS_REGISTROS_PERMITIDOS);
        }
        return totalRegistros;
    }
}
