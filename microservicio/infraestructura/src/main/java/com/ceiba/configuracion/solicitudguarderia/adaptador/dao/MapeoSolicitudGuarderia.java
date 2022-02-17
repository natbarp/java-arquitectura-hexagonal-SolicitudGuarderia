package com.ceiba.configuracion.solicitudguarderia.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.solicitudguarderia.modelo.dto.DtoSolicitudGuarderia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoSolicitudGuarderia implements RowMapper<DtoSolicitudGuarderia>, MapperResult {

    @Override
    public DtoSolicitudGuarderia mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombrePropietario = resultSet.getString("nombre_Propietario");
        Long idPropietario = resultSet.getLong("id_Propietario");
        String tipoAnimal = resultSet.getString("tipo_Animal");
        LocalDateTime fechaIngreso = extraerLocalDateTime(resultSet, "fecha_Ingreso");
        Long diasEstadia = resultSet.getLong("dias_Estadia");

        return new DtoSolicitudGuarderia(id,nombrePropietario,idPropietario,tipoAnimal,fechaIngreso,diasEstadia);
    }

}
