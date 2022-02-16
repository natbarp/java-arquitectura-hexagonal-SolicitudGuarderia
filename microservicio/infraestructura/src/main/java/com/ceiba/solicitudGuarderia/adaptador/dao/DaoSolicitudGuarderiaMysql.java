package com.ceiba.solicitudGuarderia.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.solicitudGuarderia.modelo.dto.DtoSolicitudGuarderia;
import com.ceiba.solicitudGuarderia.puerto.dao.DaoSolicitudGuarderia;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoSolicitudGuarderiaMysql implements DaoSolicitudGuarderia {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="usuario", value="listar")
    private static String sqlListar;

    @SqlStatement(namespace="usuario", value="listarPorIdPropietario")
    private static String sqlListarPorIdPropietario;

    public DaoSolicitudGuarderiaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoSolicitudGuarderia> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoSolicitudGuarderia());
    }

    @Override
    public List<DtoSolicitudGuarderia> listarPorIdPropietario(Long idPropietario) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idPropietario", idPropietario);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarPorIdPropietario, paramSource, new MapeoSolicitudGuarderia());
    }
}
