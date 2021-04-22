-- borra la bd si existe
DROP DATABASE IF EXISTS ciberfarma;
-- creamos la bd
CREATE DATABASE ciberfarma;
-- activamos la bd
USE ciberfarma;

create table tb_tipos(
idtipo		int not null primary key,
descripcion varchar(15)
);

CREATE TABLE tb_usuarios(
codigo  int auto_increment,
nombre varchar(15),
apellido varchar(25),
usuario  char(45) NOT NULL,
clave    char(5),
facceso date  null,
tipo    int DEFAULT 2,
estado  int(1) DEFAULT 1,
primary key (codigo),
foreign key (tipo) references tb_tipos(idtipo)
);
-- tipo : administrativo  : 1
--        cliente  : 2
-- estado : activo : 1
--          suspendido : 2
--          eliminado  : 3
            
create table tb_categorias(
idcategoria	int not null primary key,
descripcion varchar(45)
);

create table tb_productos(
idprod char(5) not null,
descripcion varchar(45),
stock int,
precio decimal(8,2),
idcategoria int,
estado boolean,
primary key (idprod), 
foreign key (idcategoria) references tb_categorias(idcategoria)
);

create table tb_ventas(
numvta      char(5) not null,
idprod      char(5) not null,
-- descripcion varchar(30),
cantidad    int,
preciovta   decimal(8,2),
fechavta    date,
codvendedor int not null,
primary key (numvta,idprod),
foreign key (idprod) references tb_productos(idprod),
foreign key (codvendedor) references tb_usuarios(codigo)
);

-- inserts
insert into tb_tipos values (1, 'administrativo');
insert into tb_tipos values (2, 'cliente');

insert into tb_usuarios values (null,'Tito', 'Ciber','U001@gmail.com', '10001', curdate(),1,1);
insert into tb_usuarios values (null,'Zoila', 'Baca','U002@gmail.com', '10002', curdate(),2,1);
insert into tb_usuarios values (null,'Willy', 'Wonka','U003@gmail.com', '10003', curdate(),2,2);
insert into tb_usuarios values (null,'Giuseppe', 'Atuncar','U004@hotmail.com', '10004', curdate(),2,3);

insert into tb_categorias values (1, 'Pastillas');
insert into tb_categorias values (2, 'Jarabe');
insert into tb_categorias values (3, 'Cremas');
insert into tb_categorias values (4, 'Tocador');
insert into tb_categorias values (5, 'Cuidado');
insert into tb_categorias values (6, 'Otros');

insert into tb_productos values ('P0001','Panadol cj 10',20,1.85,1,1);
insert into tb_productos values ('P0002','Curitas unidad',100,1.0,3,1);
insert into tb_productos values ('P0003','Kita tos',80,15.0,2,1);
insert into tb_productos values ('P0004','Achiz',120,1.0,1,1);
insert into tb_productos values ('P0005','Jaboncillo cj',120,1.0,3,1);
insert into tb_productos values ('P0006','Termometro',80,2.8,5,1);
insert into tb_productos values ('P0007','Panadol jarabe pq',40,10.5,2,1);
insert into tb_productos values ('P0008','Antalgina',55,1.8,1,1);
insert into tb_productos values ('P0009','Ibuprofeno',60,15.0,4,1);
insert into tb_productos values ('P0010','Mejoralito NiÃ±os',10,1.5,1,1);
insert into tb_productos values ('P0011','Panadol jarabe',10,1.5,2,1);
insert into tb_productos values ('P0012','Jabon Neko',40,8.5,4,1);
insert into tb_productos values ('P0013','Pañales x 24u',10,1.5,5,1);
insert into tb_productos values ('P0014','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0015','Champu Amigo',50,0.99,5,1);
insert into tb_productos values ('P0016','Mejoralito',10,1.5,4,1);
insert into tb_productos values ('P0017','SinDolor',23,1.5,6,1);
insert into tb_productos values ('P0018','Mejoralito Forte',10,1.5,2,1);
insert into tb_productos values ('P0019','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0020','Mejoralito Forte',10,1.5,3,1);
insert into tb_productos values ('P0021','Mejoralito Forte',10,1.5,2,1);
insert into tb_productos values ('P0022','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0023','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0024','Mejoralito Forte',10,1.5,2,1);
insert into tb_productos values ('P0025','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0026','Mejoralito Forte',10,1.5,3,1);
insert into tb_productos values ('P0027','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0028','Mejoralito Forte',10,1.5,4,1);
insert into tb_productos values ('P0029','Mejoralito Forte',10,1.5,1,1);
insert into tb_productos values ('P0030','Mejoralito Forte',10,1.5,5,1);
insert into tb_productos values ('P0031','Mejoralito UForte',10,0.99,5,1);


insert into tb_ventas values ('V0001','P0001', 1, 1.50, '2017/04/15',1 );
insert into tb_ventas values ('V0001','P0004', 2, 1.0,  '2017/04/15',1 );
insert into tb_ventas values ('V0002','P0002', 1, 1.0,  '2017/04/25', 2);
insert into tb_ventas values ('V0003','P0003', 1, 15.0,  '2017/04/26',2 );
insert into tb_ventas values ('V0004','P0002', 1, 1.0,  '2017/05/01',1);
insert into tb_ventas values ('V0004','P0005', 1, 1.0,  '2017/05/01',1 );


-- consultas
SELECT * FROM tb_usuarios;
SELECT * FROM tb_ventas;
SELECT * FROM tb_productos;

select * from tb_categorias;


-- ejemplo de procedimientos almancenados de consulta
DELIMiTER $$
create procedure usp_validaAcceso (usr char(45), pas char(5))
begin
select * from tb_usuarios where usuario = usr and clave = pas;
end$$
DELIMiTER ;

CALL usp_validaAcceso ('U001','10001');

DELIMiTER $$
create procedure usp_reporte1 ()
begin
SELECT numvta, fechavta, p.descripcion as 'Nombre del Producto', 
	concat(u.nombre, " ",u.apellido) as 'Nombre del vendedor', 
    (v.cantidad * v.preciovta) as 'Monto de la venta'
 FROM tb_ventas v
INNER JOIN tb_usuarios u ON v.codvendedor = u.codigo
INNER JOIN tb_productos p ON v.idprod = p.idprod;

end$$
DELIMiTER ;

CALL usp_reporte1 ();

