
-- Comenzamos con CRUD: create(insertar), read(leer), update(actualizar), delete(eliminar)
-- Listar los estudiantes (read)
SELECT * FROM estudiantes2024 order by idestudiantes2024;
-- Insertar estudiantes
INSERT INTO estudiantes2024 (nombre, apellido, telefono , email) VALUES ("Juan", "Perez","26145456","juan@mail.com");
-- Update  (modificar)
UPDATE estudiantes2024 SET nombre="Juan Carlos", apellido= "Garcia" WHERE idestudiantes2024= 1;
-- Delete (eliminar)
DELETE FROM estudiantes2024 WHERE idestudiantes2024=3;
-- Para modificar el idestudiantes 2024 y comience en 1
ALTER TABLE estudiantes2024 AUTO_INCREMENT = 1;