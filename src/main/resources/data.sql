INSERT INTO Usuario (id, clave, rol)
VALUES ('JPerez','$2a$10$nRFxtOZnlJPSJjYp9PLhL.lCeAUxetRlsmLlVSJXSG8G1lZynfzaO','CLI');

INSERT INTO Usuario (id, clave, rol)
VALUES ('MMata','$2a$10$a6lGFWnRb6jMiYB7hQkmzOVTvvR/py/JQ1MIA4DdCLx91vJLsBsb.','CLI');

INSERT INTO Tipo (id, nombre) VALUES ('001','Personas Juridicas');
INSERT INTO Tipo (id, nombre) VALUES ('002','Bienes Inmuebles');
INSERT INTO Tipo (id, nombre) VALUES ('003','Bienes Muebles');
INSERT INTO Tipo (id, nombre) VALUES ('004','Catastro');
INSERT INTO Tipo (id, nombre) VALUES ('005','Placas');
INSERT INTO Tipo (id, nombre) VALUES ('006','Propiedad Intelectual');
INSERT INTO Tipo (id, nombre) VALUES ('007','Alerta Registral');

INSERT INTO Documento (id, descripcion, monto, timbres, tipo)
VALUES ('001','Afectacion',2785,315,'001');
INSERT INTO Documento (id, descripcion, monto, timbres, tipo)
VALUES ('002','Cedula Juridica',2785,315,'001');
INSERT INTO Documento (id, descripcion, monto, timbres, tipo)
VALUES ('003','Historica de Movimientos',2785,315,'001');
INSERT INTO Documento (id, descripcion, monto, timbres, tipo)
VALUES ('004','Historica de Propiedades',2515,115,'002');
INSERT INTO Documento (id, descripcion, monto, timbres, tipo)
VALUES ('005','Literal de Inmuebles',2785,315,'002');
INSERT INTO Documento (id, descripcion, monto, timbres, tipo)
VALUES ('006','Indice de Personas',2515,115,'002');
INSERT INTO Documento (id, descripcion, monto, timbres, tipo)
VALUES ('007','Solicitud de Placas de Motos y remolques',10900,0,'005');
INSERT INTO Documento (id, descripcion, monto, timbres, tipo)
VALUES ('008','Solicitud de Placas de Vehiculo',17600,0,'005');