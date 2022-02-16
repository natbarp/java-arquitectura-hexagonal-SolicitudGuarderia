create table usuario (
 id int(11) not null auto_increment,
 nombre_Propietario varchar(100) null,
 id_Propietario int(11) not null,
 tipo_Animal varchar(100) not null,
 fecha_Ingreso datetime null,
 dias_Estadia int(11) not null,
 primary key (id)
);