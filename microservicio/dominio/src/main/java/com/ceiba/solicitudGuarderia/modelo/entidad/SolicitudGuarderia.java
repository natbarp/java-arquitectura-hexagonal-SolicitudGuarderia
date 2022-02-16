package com.ceiba.solicitudGuarderia.modelo.entidad;


import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class SolicitudGuarderia {

    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_INGRESO = "Se debe ingresar la fecha de ingreso";
    private static final String EL_TIPO_DE_ANIMAL_NO_ES_VALIDO = "El tipo de animal no es válido";
    private static final String SE_DEBE_INGRESAR_LA_IDENTIFICACION_DEL_USUARIO = "Se debe ingresar la identificación del usuario";
    private static final String SE_DEBE_INGRESAR_EL_TIPO_DE_ANIMAL = "Se debe ingresar el tipo de animal";
    private static final String SE_DEBE_INGRESAR_EL_NUMERO_DE_DIAS_DE_ESTADIA = "Se debe ingresar el numero de días de estadía";
    private static final String[] ANIMALES_VALIDOS = {"PERRO", "GATO"};
    private static final int LONGITUD_MAXIMA_ID_PROPIETARIO= 10;
    private static final String LA_IDENTIFICACION_DEBE_TENER_UNA_LONGITUD_MAXIMA_DE = "La identificación debe tener una longitud máxima de %s digitos";

    private Long id;
    private String nombrePropietario;
    private Long idPropietario;
    private String tipoAnimal;
    private LocalDateTime fechaIngreso;
    private Long diasEstadia;

    public SolicitudGuarderia(Long id, String nombrePropietario, Long idPropietario, String tipoAnimal, LocalDateTime fechaIngreso, Long diasEstadia) {
        validarObligatorio(idPropietario, SE_DEBE_INGRESAR_LA_IDENTIFICACION_DEL_USUARIO);
        validarObligatorio(tipoAnimal, SE_DEBE_INGRESAR_EL_TIPO_DE_ANIMAL);
        validarObligatorio(fechaIngreso, SE_DEBE_INGRESAR_LA_FECHA_DE_INGRESO);
        validarObligatorio(diasEstadia,SE_DEBE_INGRESAR_EL_NUMERO_DE_DIAS_DE_ESTADIA);
        validarValoresValidos(tipoAnimal,ANIMALES_VALIDOS,EL_TIPO_DE_ANIMAL_NO_ES_VALIDO);
        validarLongitud(String.valueOf(idPropietario),LONGITUD_MAXIMA_ID_PROPIETARIO,String.format(LA_IDENTIFICACION_DEBE_TENER_UNA_LONGITUD_MAXIMA_DE,LONGITUD_MAXIMA_ID_PROPIETARIO));

        this.id = id;
        this.nombrePropietario = nombrePropietario;
        this.idPropietario = idPropietario;
        this.tipoAnimal = tipoAnimal;
        this.fechaIngreso = fechaIngreso;
        this.diasEstadia = diasEstadia;
    }

}
