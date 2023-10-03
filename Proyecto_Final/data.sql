CREATE DATABASE bdsustitos;
use bdsustitos;
CREATE TABLE USUARIO (
    id int(11) AUTO_INCREMENT,
    nombre varchar(50) NOT NULL,
    apellido varchar(50) NOT NULL,
    contrasena varchar(50) NOT NULL,
    imagen varchar(100),
    descripcion varchar(100),
    direccion varchar(100),
    cp INT(5),
    municipio varchar(100),
    provincia varchar(50),
    pais varchar(100),
    telefono int(9),
    email varchar(100),
    validado int(1),
    strValidado varchar(100),
    CONSTRAINT PK_USUARIO PRIMARY KEY (id)
);
INSERT INTO USUARIO (nombre, apellido, contrasena, imagen, descripcion, direccion, cp, municipio, provincia, pais, telefono, email, validado, strValidado) VALUES ('admin', 'Super', 'admin', null, 'Cuenta Super Admin', 'Calle 12/Bermuda', '01010', 'Alaba', 'Vitoria', 'España', '693482193', 'admin@gmail.com', '1', 'ADAX90Cnjshd5!S');
INSERT INTO USUARIO (nombre, apellido, contrasena, imagen, descripcion, direccion, cp, municipio, provincia, pais, telefono, email, validado, strValidado) VALUES ('juan', 'Gutierrez', 'juan', null, 'Cuenta Juan Gutierrez', 'Calle 9/Nantes', '01010', 'Alaba', 'Vitoria', 'España', '64759312', 'juangutierrez@gmail.com', '1', 'sdje3342SDHJ!A');
CREATE TABLE CATEGORIA (
    id int(11),
    nombre varchar(50) NOT NULL, 
    CONSTRAINT PK_CATEGORIA PRIMARY KEY (id) 
);
INSERT INTO CATEGORIA (id, nombre) VALUES (1, 'DOCENTES');
INSERT INTO CATEGORIA (id, nombre) VALUES (2, 'POLITICOS'); 
INSERT INTO CATEGORIA (id, nombre) VALUES (3, 'EX PAREJAS');
CREATE TABLE PRODUCTO (
    id int(11) AUTO_INCREMENT,
    nombre varchar(50) NOT NULL,
    descripcion varchar(500),
    precio double,
    idCategoria int(11),
    CONSTRAINT PK_PRODUCTO PRIMARY KEY (id),
    CONSTRAINT fk_categoria_producto_idcat FOREIGN KEY (idCategoria) REFERENCES CATEGORIA(id)
);
INSERT INTO PRODUCTO (nombre, precio, descripcion, idCategoria) VALUES ('Doctor Muerte', 745.19, 'Doctor muerte practica el sadismo con sus victimas. Prefiere hacer sufrir a tu ex pareja. Cuando el sadismo tiene carácter predominantemente sexual es una parafilia, le gusta humillar a sus victimas.', 3);
INSERT INTO PRODUCTO (nombre, precio, descripcion, idCategoria) VALUES ('Barba Azul', 1245.29, 'Barba Azul practica el asesinato ritual con sus victimas. Odia a los politicos y en estos tienes un 30% de descuento. Una vez obtenido el objetivo a asesinar le hara pasar un buen rato antes de dejarlo descansar.', 2);
INSERT INTO PRODUCTO (nombre, precio, descripcion, idCategoria) VALUES ('Ángel de la Muerte', 725.99, 'El Ángel de la Muerte practica el asesinato en serie. Usualmente empleado como un cuidador o enfermero. Este esperara a cada politico en el hospital y aprovechara para meterle una vacuna que lo llevara a Marte!', 2);
INSERT INTO PRODUCTO (nombre, precio, descripcion, idCategoria) VALUES ('El Asesino del Turbante', 945.29, 'El Ángel del Turbante practica el asesinato en serie. A menudo, el ángel del Turbante se encuentra en una posición de poder o son motivados por mantener el control y eso lo logran matando politicos.', 2);
INSERT INTO PRODUCTO (nombre, precio, descripcion, idCategoria) VALUES ('El Carnicero de Rostov', 1645.39, 'El Carnicero de Rostov practica el vampirismo. El vampirismo clínico es un raro trastorno mental, una parafilia caracterizada por ser asociada con una necesidad de ingerir la sangre como un vampiro.', 1);
INSERT INTO PRODUCTO (nombre, precio, descripcion, idCategoria) VALUES ('Alfredo Romperalph', 1345.39, 'Alfredo Romperalph practica el vampirismo y tiene una gran obsesion con los docentes. El vampirismo clínico es un raro trastorno mental, una parafilia caracterizada por ser asociada con una necesidad compulsiva de sangre.', 1);

CREATE TABLE PEDIDO (
    id int(11) AUTO_INCREMENT, 
    total double,
    fecha datetime DEFAULT CURRENT_TIMESTAMP,
    idUsuario int(11),
    CONSTRAINT PK_PEDIDO PRIMARY KEY (id),
    CONSTRAINT fk_usuario_pedido_idUser FOREIGN KEY (idUsuario) REFERENCES USUARIO(id) 
);
CREATE TABLE LINEASPEDIDO (
    id int(11) AUTO_INCREMENT,
    cantidad int(10), 
    idPedido int(11),
    idProducto int(11),
    CONSTRAINT PK_LINEASPEDIDO PRIMARY KEY (id), 
    CONSTRAINT fk_producto_lineasPedido_idPedido FOREIGN KEY (idPedido) REFERENCES PEDIDO(id),
    CONSTRAINT fk_producto_lineasPedido_idProducto FOREIGN KEY (idProducto) REFERENCES PRODUCTO(id) 
);

