package com.ceiba.solicitudguarderia.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.solicitudguarderia.modelo.dto.DtoSolicitudGuarderia;
import com.ceiba.solicitudguarderia.modelo.entidad.Facturacion;
import com.ceiba.solicitudguarderia.modelo.entidad.SolicitudGuarderia;
import com.ceiba.solicitudguarderia.puerto.dao.DaoSolicitudGuarderia;
import com.ceiba.solicitudguarderia.puerto.repositorio.RepositorioSolicitudGuarderia;

import java.util.List;

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
        List<DtoSolicitudGuarderia> dtoSolicitudGuarderia = this.daoSolicitudGuarderia.listarPorIdPropietario(solicitudGuarderia.getIdPropietario());
        Facturacion factura = new Facturacion();
        Facturacion facturacion = factura.calcularDescuentosCostosFacturacion(solicitudGuarderia, dtoSolicitudGuarderia);
        this.repositorioSolicitudGuarderia.actualizar(solicitudGuarderia);
        return facturacion;
    }

    private void validarExistenciaPrevia(SolicitudGuarderia solicitudGuarderia) {
        boolean existe = this.repositorioSolicitudGuarderia.existePorId(solicitudGuarderia.getId());
        if(!existe) {
            throw new ExcepcionDuplicidad(EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }


}
