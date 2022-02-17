package com.ceiba.solicitudguarderia.consulta;

import com.ceiba.solicitudguarderia.modelo.dto.DtoSolicitudGuarderia;
import com.ceiba.solicitudguarderia.puerto.dao.DaoSolicitudGuarderia;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarSolicitudGuarderia {

    private final DaoSolicitudGuarderia daoUsuario;

    public ManejadorListarSolicitudGuarderia(DaoSolicitudGuarderia daoUsuario){
        this.daoUsuario = daoUsuario;
    }

    public List<DtoSolicitudGuarderia> ejecutar(){ return this.daoUsuario.listar(); }

    public List<DtoSolicitudGuarderia> ejecutarPorIdPropietario(Long idPropietario){ return this.daoUsuario.listarPorIdPropietario(idPropietario);}
}
