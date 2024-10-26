-- Eliminar DB si existe
DROP DATABASE IF EXISTS `ms-db-dao-bganos`;

-- Crear BD
CREATE DATABASE `ms-db-dao-bganos`;

-- Usar BD
USE `ms-db-dao-bganos`;

-- Tabla sistema_de_riego
CREATE TABLE sistema_de_riego (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    potencia_riego INT NOT NULL,
    cantidad_agua INT NOT NULL,
    frecuencia INT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    id_fabricante INT,
    FOREIGN KEY (id_fabricante) REFERENCES fabricante(id)
);

-- Tabla invernadero_riego
CREATE TABLE invernadero_riego (
    id_invernadero INT,
    id_sistema_de_riego INT,
    PRIMARY KEY (id_invernadero, id_sistema_de_riego),
    FOREIGN KEY (id_invernadero) REFERENCES invernadero(id),
    FOREIGN KEY (id_sistema_de_riego) REFERENCES sistema_de_riego(id)
);
