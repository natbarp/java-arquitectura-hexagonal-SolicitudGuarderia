update usuario
set nombre_Propietario = :nombrePropietario,
    id_Propietario = :idPropietario,
    tipo_Animal = :tipoAnimal,
	fecha_Ingreso = :fechaIngreso,
	dias_Estadia = :diasEstadia
where id = :id