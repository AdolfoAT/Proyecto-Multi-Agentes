-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sidecar2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sidecar2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sidecar2` DEFAULT CHARACTER SET utf8 ;
USE `sidecar2` ;

-- -----------------------------------------------------
-- Table `sidecar2`.`area`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`area` (
  `idArea` INT(11) NOT NULL,
  `NombreArea` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`idArea`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`aspirante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`aspirante` (
  `idAspirante` INT(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL DEFAULT 'null',
  `ApellidoPaterno` VARCHAR(45) NULL DEFAULT 'null',
  `ApellidoMaterno` VARCHAR(45) NULL DEFAULT 'null',
  `Edad` INT(2) NULL DEFAULT '0',
  `Sexo` VARCHAR(45) NULL DEFAULT 'null',
  `Telefono` VARCHAR(15) NULL DEFAULT 'null',
  `CorreoElectronico` VARCHAR(45) NULL DEFAULT 'null',
  `MaximoNiveldeEstudios` VARCHAR(45) NULL DEFAULT 'null',
  `TituloObtenido` VARCHAR(45) NULL DEFAULT 'null',
  `usuario` VARCHAR(45) NULL DEFAULT NULL,
  `contraseña` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idAspirante`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`cartamotivos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`cartamotivos` (
  `idCartaExposicionMotivos` INT(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `Carta` VARCHAR(2000) NULL DEFAULT NULL,
  `Aspirante_idAspirante` INT(6) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  PRIMARY KEY (`idCartaExposicionMotivos`),
  INDEX `fk_Carta_Exposicion_Motivos_Aspirante1_idx` (`Aspirante_idAspirante` ASC),
  CONSTRAINT `fk_Carta_Exposicion_Motivos_Aspirante1`
    FOREIGN KEY (`Aspirante_idAspirante`)
    REFERENCES `sidecar2`.`aspirante` (`idAspirante`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`disciplina` (
  `idDisciplina` INT(11) NOT NULL,
  `NombreDisciplina` VARCHAR(100) NULL DEFAULT NULL,
  `Area_idArea` INT(11) NOT NULL,
  PRIMARY KEY (`idDisciplina`),
  INDEX `fk_Disciplina_Area1_idx` (`Area_idArea` ASC),
  CONSTRAINT `fk_Disciplina_Area1`
    FOREIGN KEY (`Area_idArea`)
    REFERENCES `sidecar2`.`area` (`idArea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`especialidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`especialidad` (
  `idEspecialidad` INT(11) NOT NULL,
  `NombreEspecialidad` VARCHAR(60) NULL DEFAULT NULL,
  `area_idArea` INT(11) NOT NULL,
  PRIMARY KEY (`idEspecialidad`),
  INDEX `fk_Especialidad_area1_idx` (`area_idArea` ASC),
  CONSTRAINT `fk_Especialidad_area1`
    FOREIGN KEY (`area_idArea`)
    REFERENCES `sidecar2`.`area` (`idArea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`lineadeinvestigación`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`lineadeinvestigación` (
  `idLineaDeInvestigación` INT(11) NOT NULL,
  `NombreLineaDeInvestigación` VARCHAR(100) NULL DEFAULT NULL,
  `Disciplina_idDisciplina` INT(11) NOT NULL,
  PRIMARY KEY (`idLineaDeInvestigación`),
  INDEX `fk_LineaDeInvestigación_Disciplina_idx` (`Disciplina_idDisciplina` ASC),
  CONSTRAINT `fk_LineaDeInvestigación_Disciplina`
    FOREIGN KEY (`Disciplina_idDisciplina`)
    REFERENCES `sidecar2`.`disciplina` (`idDisciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`ptc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`ptc` (
  `idPTC` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL DEFAULT NULL,
  `ApellidoPaterno` VARCHAR(45) NULL DEFAULT NULL,
  `ApellidoMaterno` VARCHAR(45) NULL DEFAULT NULL,
  `Grado` VARCHAR(45) NULL DEFAULT NULL,
  `NivelSNI` VARCHAR(45) NULL DEFAULT NULL,
  `Area` INT(11) NOT NULL,
  `DisciplinaExpertise` INT(11) NOT NULL,
  `LineaDeInvestigación` INT(11) NOT NULL,
  `InstitucionAdscripcion` VARCHAR(100) NULL DEFAULT NULL,
  `usuario` VARCHAR(45) NULL DEFAULT NULL,
  `Clave` VARCHAR(10) NULL DEFAULT NULL,
  `Especialidad` INT(11) NOT NULL,
  PRIMARY KEY (`idPTC`),
  INDEX `fk_ptc_Disciplina11_idx` (`DisciplinaExpertise` ASC),
  INDEX `fk_ptc_LineaDeInvestigación11_idx` (`LineaDeInvestigación` ASC),
  INDEX `fk_ptc_area1_idx` (`Area` ASC),
  INDEX `fk_ptc_Especialidad11` (`Especialidad` ASC),
  CONSTRAINT `fk_ptc_area1`
    FOREIGN KEY (`Area`)
    REFERENCES `sidecar2`.`area` (`idArea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ptc_Disciplina11`
    FOREIGN KEY (`DisciplinaExpertise`)
    REFERENCES `sidecar2`.`disciplina` (`idDisciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ptc_Especialidad11`
    FOREIGN KEY (`Especialidad`)
    REFERENCES `sidecar2`.`especialidad` (`idEspecialidad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ptc_LineaDeInvestigación11`
    FOREIGN KEY (`LineaDeInvestigación`)
    REFERENCES `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`comite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`comite` (
  `idComite` INT(11) NOT NULL AUTO_INCREMENT,
  `Dictamen` VARCHAR(45) NULL DEFAULT NULL,
  `PTC_idPTC` INT(11) NOT NULL,
  `Aspirante_idAspirante` INT(6) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`idComite`),
  INDEX `fk_Comite_PTC1_idx` (`PTC_idPTC` ASC),
  INDEX `fk_Comite_Aspirante1_idx` (`Aspirante_idAspirante` ASC),
  CONSTRAINT `fk_Comite_Aspirante1`
    FOREIGN KEY (`Aspirante_idAspirante`)
    REFERENCES `sidecar2`.`aspirante` (`idAspirante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comite_PTC1`
    FOREIGN KEY (`PTC_idPTC`)
    REFERENCES `sidecar2`.`ptc` (`idPTC`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`rubrica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`rubrica` (
  `idRubrica` INT(11) NOT NULL AUTO_INCREMENT,
  `Rubrica` VARCHAR(45) NULL DEFAULT NULL,
  `PTC_idPTC` INT(11) NOT NULL,
  `Cuantos Excelentes` FLOAT NULL DEFAULT NULL,
  `Cuantos Buenos` FLOAT NULL DEFAULT NULL,
  `Cuantos Regularesl` FLOAT NULL DEFAULT NULL,
  `Cuantos Malos` FLOAT NULL DEFAULT NULL,
  `Aspirante_idAspirante` INT(6) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`idRubrica`),
  INDEX `fk_Rubrica_PTC1_idx` (`PTC_idPTC` ASC),
  INDEX `fk_Rubrica_Aspirante1_idx` (`Aspirante_idAspirante` ASC),
  CONSTRAINT `fk_Rubrica_Aspirante1`
    FOREIGN KEY (`Aspirante_idAspirante`)
    REFERENCES `sidecar2`.`aspirante` (`idAspirante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rubrica_PTC1`
    FOREIGN KEY (`PTC_idPTC`)
    REFERENCES `sidecar2`.`ptc` (`idPTC`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`criterio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`criterio` (
  `idCriterio` INT(11) NOT NULL AUTO_INCREMENT,
  `Evaluacion` VARCHAR(45) NULL DEFAULT NULL,
  `Descripcion` VARCHAR(45) NULL DEFAULT NULL,
  `Rubrica_idRubrica` INT(11) NOT NULL,
  PRIMARY KEY (`idCriterio`),
  INDEX `fk_Criterio_Rubrica1_idx` (`Rubrica_idRubrica` ASC),
  CONSTRAINT `fk_Criterio_Rubrica1`
    FOREIGN KEY (`Rubrica_idRubrica`)
    REFERENCES `sidecar2`.`rubrica` (`idRubrica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`darktableaspirante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`darktableaspirante` (
  `idDarkTableAspirante` INT(5) NOT NULL,
  `Operacion` VARCHAR(45) NULL DEFAULT NULL,
  `Tiempo` DATE NULL DEFAULT NULL,
  `aspirante_idAspirante` INT(6) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`idDarkTableAspirante`),
  INDEX `fk_DarkTableAspirante_aspirante1_idx` (`aspirante_idAspirante` ASC),
  CONSTRAINT `fk_DarkTableAspirante_aspirante1`
    FOREIGN KEY (`aspirante_idAspirante`)
    REFERENCES `sidecar2`.`aspirante` (`idAspirante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`darktableptc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`darktableptc` (
  `idDarkTablePTC` INT(5) NOT NULL,
  `Operacion` VARCHAR(45) NULL DEFAULT NULL,
  `Tiempo` DATE NULL DEFAULT NULL,
  `ptc_idPTC` INT(11) NOT NULL,
  PRIMARY KEY (`idDarkTablePTC`),
  INDEX `fk_DarkTablePTC_ptc1_idx` (`ptc_idPTC` ASC),
  CONSTRAINT `fk_DarkTablePTC_ptc1`
    FOREIGN KEY (`ptc_idPTC`)
    REFERENCES `sidecar2`.`ptc` (`idPTC`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`dictamen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`dictamen` (
  `idDictamen` INT(11) NOT NULL AUTO_INCREMENT,
  `Calificacion` FLOAT NULL DEFAULT NULL,
  `Decision en Base a Calificacion` VARCHAR(45) NULL DEFAULT NULL,
  `Desicion Basado en Reglas` VARCHAR(45) NULL DEFAULT NULL,
  `Rubrica_idRubrica` INT(11) NOT NULL,
  PRIMARY KEY (`idDictamen`),
  INDEX `fk_Dictamen_Rubrica1_idx` (`Rubrica_idRubrica` ASC),
  CONSTRAINT `fk_Dictamen_Rubrica1`
    FOREIGN KEY (`Rubrica_idRubrica`)
    REFERENCES `sidecar2`.`rubrica` (`idRubrica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`expertisaspirante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`expertisaspirante` (
  `idExAspirante` INT(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `EstudiosPrevios` VARCHAR(500) NULL DEFAULT NULL,
  `ConocimientoenIngles` VARCHAR(500) NULL DEFAULT NULL,
  `ConocimientoenMatematicas` VARCHAR(500) NULL DEFAULT NULL,
  `CertificacionesenProgramacion` VARCHAR(500) NULL DEFAULT NULL,
  `ConocimientosenPlataformasCientiicas` VARCHAR(500) NULL DEFAULT NULL,
  `ExperienciapreviaenTema` VARCHAR(500) NULL DEFAULT NULL,
  `ExperienciaenPublicacionCientifica` VARCHAR(500) NULL DEFAULT NULL,
  `ProyectodeInvestigacionCientifica` VARCHAR(500) NULL DEFAULT NULL,
  `Aspirante_idAspirante` INT(6) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  PRIMARY KEY (`idExAspirante`),
  INDEX `fk_Expertis_Aspirante_Aspirante1_idx` (`Aspirante_idAspirante` ASC),
  CONSTRAINT `fk_Expertis_Aspirante_Aspirante1`
    FOREIGN KEY (`Aspirante_idAspirante`)
    REFERENCES `sidecar2`.`aspirante` (`idAspirante`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`reportedeinvestigacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`reportedeinvestigacion` (
  `idReportedeInvestigacion` INT(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `Tema` VARCHAR(45) NULL DEFAULT NULL,
  `Titulo` VARCHAR(45) NULL DEFAULT NULL,
  `Introduccion` VARCHAR(500) NULL DEFAULT NULL,
  `Resumen` VARCHAR(500) NULL DEFAULT NULL,
  `MarcoTeorico` VARCHAR(1000) NULL DEFAULT NULL,
  `EstadodelArte` VARCHAR(1000) NULL DEFAULT NULL,
  `Problematica` VARCHAR(500) NULL DEFAULT NULL,
  `Aportaciones` VARCHAR(500) NULL DEFAULT NULL,
  `Aspirante_idAspirante` INT(6) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `Status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idReportedeInvestigacion`),
  INDEX `fk_Reporte_de_Investigacion_Aspirante1_idx` (`Aspirante_idAspirante` ASC),
  CONSTRAINT `fk_Reporte_de_Investigacion_Aspirante1`
    FOREIGN KEY (`Aspirante_idAspirante`)
    REFERENCES `sidecar2`.`aspirante` (`idAspirante`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 29
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`ptc-asp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`ptc-asp` (
  `idPtc-Asp` INT(11) NOT NULL AUTO_INCREMENT,
  `ptc_idPTC` INT(11) NOT NULL,
  `aspirante_idAspirante` INT(6) UNSIGNED ZEROFILL NOT NULL,
  `reportedeinvestigacion_idReportedeInvestigacion` INT(6) UNSIGNED ZEROFILL NOT NULL,
  `Status_reporte` VARCHAR(45) NULL DEFAULT NULL,
  `Calificacion` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`idPtc-Asp`),
  INDEX `fk_ptc-asp_ptc1_idx` (`ptc_idPTC` ASC),
  INDEX `fk_ptc-asp_aspirante1_idx` (`aspirante_idAspirante` ASC),
  INDEX `fk_ptc-asp_reportedeinvestigacion1_idx` (`reportedeinvestigacion_idReportedeInvestigacion` ASC),
  CONSTRAINT `fk_ptc-asp_aspirante1`
    FOREIGN KEY (`aspirante_idAspirante`)
    REFERENCES `sidecar2`.`aspirante` (`idAspirante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ptc-asp_ptc1`
    FOREIGN KEY (`ptc_idPTC`)
    REFERENCES `sidecar2`.`ptc` (`idPTC`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ptc-asp_reportedeinvestigacion1`
    FOREIGN KEY (`reportedeinvestigacion_idReportedeInvestigacion`)
    REFERENCES `sidecar2`.`reportedeinvestigacion` (`idReportedeInvestigacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`sinonimos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`sinonimos` (
  `idSinonimos` INT(11) NOT NULL,
  `NombreSinonimos` VARCHAR(45) NULL DEFAULT NULL,
  `area_idArea` INT(11) NOT NULL,
  PRIMARY KEY (`idSinonimos`),
  INDEX `fk_Sinonimos_area1_idx` (`area_idArea` ASC),
  CONSTRAINT `fk_Sinonimos_area1`
    FOREIGN KEY (`area_idArea`)
    REFERENCES `sidecar2`.`area` (`idArea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`sinonimosarea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`sinonimosarea` (
  `idsinonimosarea` INT(11) NOT NULL,
  `sinonimo` VARCHAR(45) NULL DEFAULT NULL,
  `area_idArea` INT(11) NOT NULL,
  PRIMARY KEY (`idsinonimosarea`),
  INDEX `fk_sinonimosarea_area1_idx` (`area_idArea` ASC),
  CONSTRAINT `fk_sinonimosarea_area1`
    FOREIGN KEY (`area_idArea`)
    REFERENCES `sidecar2`.`area` (`idArea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`sinonimosdisciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`sinonimosdisciplina` (
  `idsinonimosdisciplina` INT(11) NOT NULL,
  `sinonimo` VARCHAR(45) NULL DEFAULT NULL,
  `disciplina_idDisciplina` INT(11) NOT NULL,
  PRIMARY KEY (`idsinonimosdisciplina`),
  INDEX `fk_sinonimosdisciplina_disciplina1_idx` (`disciplina_idDisciplina` ASC),
  CONSTRAINT `fk_sinonimosdisciplina_disciplina1`
    FOREIGN KEY (`disciplina_idDisciplina`)
    REFERENCES `sidecar2`.`disciplina` (`idDisciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sidecar2`.`sinonimosinvestigacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sidecar2`.`sinonimosinvestigacion` (
  `idsinonimosinvestigacion` INT(11) NOT NULL,
  `sinonimos` VARCHAR(45) NULL DEFAULT NULL,
  `lineadeinvestigación_idLineaDeInvestigación` INT(11) NOT NULL,
  PRIMARY KEY (`idsinonimosinvestigacion`),
  INDEX `fk_sinonimosinvestigacion_lineadeinvestigación1_idx` (`lineadeinvestigación_idLineaDeInvestigación` ASC),
  CONSTRAINT `fk_sinonimosinvestigacion_lineadeinvestigación1`
    FOREIGN KEY (`lineadeinvestigación_idLineaDeInvestigación`)
    REFERENCES `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `sidecar2`.`area`
-- -----------------------------------------------------
START TRANSACTION;
USE `sidecar2`;
INSERT INTO `sidecar2`.`area` (`idArea`, `NombreArea`) VALUES (1, 'Área Académica de Computación y Eletrónica');
INSERT INTO `sidecar2`.`area` (`idArea`, `NombreArea`) VALUES (2, 'Área Académica de Biología');
INSERT INTO `sidecar2`.`area` (`idArea`, `NombreArea`) VALUES (3, 'Área Académica de Ciencias de la Tierra y Materiales');
INSERT INTO `sidecar2`.`area` (`idArea`, `NombreArea`) VALUES (4, 'Área Académica de Ingeniería y Arquitectura');
INSERT INTO `sidecar2`.`area` (`idArea`, `NombreArea`) VALUES (5, 'Área Académica de Matemáticas y Física');
INSERT INTO `sidecar2`.`area` (`idArea`, `NombreArea`) VALUES (6, 'Área Académica de Química');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sidecar2`.`aspirante`
-- -----------------------------------------------------
START TRANSACTION;
USE `sidecar2`;
INSERT INTO `sidecar2`.`aspirante` (`idAspirante`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Edad`, `Sexo`, `Telefono`, `CorreoElectronico`, `MaximoNiveldeEstudios`, `TituloObtenido`, `usuario`, `contraseña`) VALUES (000001, 'Roberto', 'Flores', 'Sánchez', 22, 'Masculino', '7712354895', 'robetosanchez@gmail.com', 'Licenciatura', 'Titulo', 'Roberto', '12345');
INSERT INTO `sidecar2`.`aspirante` (`idAspirante`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Edad`, `Sexo`, `Telefono`, `CorreoElectronico`, `MaximoNiveldeEstudios`, `TituloObtenido`, `usuario`, `contraseña`) VALUES (000002, 'Fernanda', 'García', 'Ramirez', 26, 'Femenino', '7717454585', 'fernandaramirez@hotmail.com', 'Maestria', 'Titulo', 'Fernanda', '1234');
INSERT INTO `sidecar2`.`aspirante` (`idAspirante`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Edad`, `Sexo`, `Telefono`, `CorreoElectronico`, `MaximoNiveldeEstudios`, `TituloObtenido`, `usuario`, `contraseña`) VALUES (000003, 'Sebastián', 'Sotelo', 'Ruelas', 30, 'Masculino', '7719526847', 'ruelas@hotmail.com', 'Doctorado', 'Titulo', 'Sebastian', '12345');
INSERT INTO `sidecar2`.`aspirante` (`idAspirante`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Edad`, `Sexo`, `Telefono`, `CorreoElectronico`, `MaximoNiveldeEstudios`, `TituloObtenido`, `usuario`, `contraseña`) VALUES (000004, 'Adolfo', 'Ascencio', 'Trejo', 22, 'Masculino', '77583156', 'afsdfsdf@livve.com.mx', 'Licenciatura ', 'dsfsdgs', 'Adolfo', '12345');
INSERT INTO `sidecar2`.`aspirante` (`idAspirante`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Edad`, `Sexo`, `Telefono`, `CorreoElectronico`, `MaximoNiveldeEstudios`, `TituloObtenido`, `usuario`, `contraseña`) VALUES (000005, 'Rodrigo', 'Flores', 'Perez', 26, 'Masculino', '8528628', 'asfsdfsdf@live.com.mx', 'Licenciatura ', 'sdgsdgfg', 'Rodrigo', '12345');
INSERT INTO `sidecar2`.`aspirante` (`idAspirante`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Edad`, `Sexo`, `Telefono`, `CorreoElectronico`, `MaximoNiveldeEstudios`, `TituloObtenido`, `usuario`, `contraseña`) VALUES (000006, 'Fernando', 'Montiel', 'Guzman', 26, 'Masculino', '858945845', 'fsdgdsfs@live.com.mx', 'Doctorado', 'fdgds', 'Fernando', '12345');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sidecar2`.`disciplina`
-- -----------------------------------------------------
START TRANSACTION;
USE `sidecar2`;
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (1, 'Computacion Inteligente', 1);
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (2, 'Computacion Educativa', 1);
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (3, 'Electronica y Control', 1);
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (4, 'Área Académica de Biología', 2);
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (5, 'Materiales', 3);
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (6, 'Ciencias de la Tierra', 3);
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (7, 'Metalurgia', 3);
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (8, 'Procesos Químicos y Físicos del Estado Sólido', 3);
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (9, 'Tecnologías Avanzadas en Ingeniería', 4);
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (10, 'Ingeniería de Sistemas Organizacionales', 4);
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (11, 'Ingeniería Civil Sustentable y Tecnología de Materiales', 4);
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (12, 'Arquitectura, Urbanismo y Sustentabilidad', 4);
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (13, 'Área Académica de Matemáticas y Física', 5);
INSERT INTO `sidecar2`.`disciplina` (`idDisciplina`, `NombreDisciplina`, `Area_idArea`) VALUES (14, 'Área Académica de Química', 6);

COMMIT;

-- -----------------------------------------------------
-- Data for table `mydb`.`Especialidad`
-- -----------------------------------------------------
START TRANSACTION;
USE `sidecar2`;
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (1, 'Diseño y Gestión de Páginas Web', 1);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (2, 'Desarrollo de Software', 1);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (3, 'Tecnólogo Mecatrónico', 1);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (4, 'Informática Educativa', 1);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (5, 'Informática', 1);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (6, 'Tecnología Educativa Online', 1);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (7, 'Seguridad Informática y Tecnologías de la Informacion', 1);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (8, 'Redes y Sistemas Integrados', 1);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (9, 'Tecnologías Móviles', 1);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (10, 'Biología marina', 2);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (11, 'Biología molecular', 2);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (12, 'Botánica', 2);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (13, 'Fisiología', 2);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (14, 'Genética', 2);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (15, 'Microbiología', 2);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (16, 'Zoología', 2);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (17, 'Aerobiología', 2);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (18, 'Astrobiología', 2);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (19, 'Bacteriología', 2);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (20, 'Geofísica de la Tierra Sólida', 3);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (21, 'Aguas Subterráneas, Exploración y Modelación', 3);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (22, 'Sismología', 3);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (23, 'Vulcanología', 3);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (24, 'Percepción Remota', 3);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (25, 'Ciencias Espaciales', 3);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (26, 'Geología Ambiental', 3);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (27, 'Físico Química Amtosférica', 3);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (28, 'Geoquímica y Petrología', 3);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (29, 'Ciencias Planetarias', 3);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (30, 'Meteorología Tropical', 3);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (31, 'Arquitectura 3D', 4);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (32, 'Arquitectura Acústica', 4);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (33, 'Arquitectura Bioclimática', 4);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (34, 'Arquitectura Comercial y Publicitaria', 4);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (35, 'Arquitectura en Tierra y Vernácula', 4);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (36, 'Arquitectura Escolar', 4);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (37, 'Arquitectura Hospitalaria', 4);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (38, 'Arquitectura Industrial', 4);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (39, 'Arquitectura Legal', 4);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (40, 'Diseño BIM', 4);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (41, 'Física Teórica', 5);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (42, 'Astrofísica', 5);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (43, 'Física Nuclear y Partículas', 5);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (44, 'Fotónica', 5);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (45, 'Aritmética', 5);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (46, 'Álgebra', 5);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (47, 'Geometría y topología', 5);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (48, 'Geometría diferencial', 5);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (49, 'Matemática discreta', 5);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (50, 'Matemáticas aplicadas', 5);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (51, 'Química Inorgánica', 6);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (52, 'Química Orgánica', 6);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (53, 'Química Analítica', 6);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (54, 'Química Física', 6);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (55, 'Química Aplicada', 6);
INSERT INTO `sidecar2`.`Especialidad` (`idEspecialidad`, `NombreEspecialidad`, `area_idArea`) VALUES (56, 'Ciencias en Ingeniería Química', 6);

COMMIT;

-- -----------------------------------------------------
-- Data for table `sidecar2`.`lineadeinvestigación`
-- -----------------------------------------------------
START TRANSACTION;
USE `sidecar2`;
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (1, 'Sistemas Distribuidos y Paralelos', 1);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (2, 'Data Mining', 1);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (3, 'Innovación y Evaluación Educativa', 2);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (4, 'Desarrollo de Software Educativo con Técnicas de Realidad Virtual y Multimedia', 2);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (5, 'Sistemas Distribuidos', 3);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (6, 'Sistemas Lineales', 3);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (7, 'Sistemas No Lineales', 3);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (8, 'Conservación Biológica', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (9, 'Ecología de Comunidades', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (10, 'Ecología del Paisaje y Ordenamiento Ambiental', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (11, 'Ecología de Poblaciones', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (12, 'Etnobotánica', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (13, 'Genética', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (14, 'Historía y Educación', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (15, 'Interacciones Biológicas', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (16, 'Micología', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (17, 'Modificación Estructura y Modelado de Materiales ', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (18, 'Sintesis de Caracterización de Polímeros', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (19, 'Geodinámica ', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (20, 'Morfofisiología Animal', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (21, 'Morfofisiología Vegetal', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (22, 'Paleozoología', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (23, 'Sistemática Animal', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (24, 'Sistemática Molecular', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (25, 'Sistemática Vegetal', 4);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (26, 'Materiales Compuestos', 5);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (27, 'Modificación Estructura y Modelado de Materiales', 5);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (28, 'Sintesis y Caracterización de Polímeros ', 5);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (29, 'Geodinámica y Recursos Minerales', 6);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (30, 'Hidrogeología', 6);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (31, 'Geología Ambiental', 6);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (32, 'Geoquímica', 6);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (33, 'Corrosión y Recubrimientos', 7);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (34, 'Procesos de Metalurgia Extractiva', 7);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (35, 'Sólidos Particulados', 8);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (36, 'Procesamiento de Sólidos Nanoestructurados', 8);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (37, 'Automatización y optimización de sistemas de manufactura', 9);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (38, 'Análisis, diseño y optimización de sistemas sociotécnicos', 10);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (39, 'Gestión integral de los recursos hídricos de los materiales', 11);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (40, 'Corrosión y patologías de los materiales', 11);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (41, 'Arquitectura, Urbanismo y Sustentabilidad', 12);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (42, 'Física Matemática', 13);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (43, 'Biomatemáticas', 13);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (44, 'Modelación y Análisis Numérico', 13);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (45, 'Uso de Tecnologías en el Aprendizaje de las Matemáticas', 13);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (46, 'Resolución de Problemas en Educación Matemática', 13);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (47, 'Teoría de Operadores', 13);
INSERT INTO `sidecar2`.`lineadeinvestigación` (`idLineaDeInvestigación`, `NombreLineaDeInvestigación`, `Disciplina_idDisciplina`) VALUES (48, 'Área Académica de Química', 14);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sidecar2`.`ptc`
-- -----------------------------------------------------
START TRANSACTION;
USE `sidecar2`;
INSERT INTO `sidecar2`.`ptc` (`idPTC`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Grado`, `NivelSNI`, `Area`, `DisciplinaExpertise`, `LineaDeInvestigación`, `InstitucionAdscripcion`, `usuario`, `Clave`, `Especialidad`) VALUES (1, 'Jessica', 'Contrera', 'Isals', 'Doctorado', 'NNN', 1, 1, 1, 'UAEH', 'Jessica', '12345', 1);
INSERT INTO `sidecar2`.`ptc` (`idPTC`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Grado`, `NivelSNI`, `Area`, `DisciplinaExpertise`, `LineaDeInvestigación`, `InstitucionAdscripcion`, `usuario`, `Clave`, `Especialidad`) VALUES (2, 'Pablo', 'Lopéz', 'Salinas', 'Maestria', 'MMM', 1, 1, 1, 'UAEH', 'Pablo', '12345', 1);
INSERT INTO `sidecar2`.`ptc` (`idPTC`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Grado`, `NivelSNI`, `Area`, `DisciplinaExpertise`, `LineaDeInvestigación`, `InstitucionAdscripcion`, `usuario`, `Clave`, `Especialidad`) VALUES (3, 'Abigail', 'Fernandez', 'Cabrera', 'Doctorado', 'KKK', 1, 1, 1, 'UAEH', 'Abigail', '12345', 1);
INSERT INTO `sidecar2`.`ptc` (`idPTC`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Grado`, `NivelSNI`, `Area`, `DisciplinaExpertise`, `LineaDeInvestigación`, `InstitucionAdscripcion`, `usuario`, `Clave`, `Especialidad`) VALUES (4, 'Adolfo', 'Ascencio ', 'Trejo', 'Doctorado', 'KKK', 1, 1, 1, 'UAEH', 'Adolfo', '12345', 1);
INSERT INTO `sidecar2`.`ptc` (`idPTC`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Grado`, `NivelSNI`, `Area`, `DisciplinaExpertise`, `LineaDeInvestigación`, `InstitucionAdscripcion`, `usuario`, `Clave`, `Especialidad`) VALUES (5, 'Juan ', 'Hernandez', 'Soto', 'Doctorado', 'HHF', 3, 6, 27, 'UAEH', 'Juan', '12345', 1);
INSERT INTO `sidecar2`.`ptc` (`idPTC`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Grado`, `NivelSNI`, `Area`, `DisciplinaExpertise`, `LineaDeInvestigación`, `InstitucionAdscripcion`, `usuario`, `Clave`, `Especialidad`) VALUES (6, 'Juan ', 'Perez', 'Perez', 'Doctorado', 'DFDF', 3, 6, 28, 'FDFS', 'Juan', '12345', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sidecar2`.`sinonimos`
-- -----------------------------------------------------
START TRANSACTION;
USE `sidecar2`;
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (1, 'Computación', 1);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (2, 'Informatica', 1);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (3, 'Computación', 1);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (4, 'Computadora', 1);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (5, 'Ordanador', 1);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (6, 'Calculadora', 1);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (7, 'Procesador', 1);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (8, 'Cerebro Electrónico', 1);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (9, 'Elasticidad', 1);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (10, 'Electrodo', 1);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (11, 'Elástico', 1);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (12, 'Elástica', 1);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (13, 'Elucidar', 1);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (14, 'Anatómico', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (15, 'Corporal', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (16, 'Estructurado', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (17, 'Fisiológico', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (18, 'Jerarquizado', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (19, 'Orgánico', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (20, 'Organizado', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (21, 'Somático', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (22, 'Viviente', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (23, 'Vivo', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (24, 'Cardinal', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (25, 'Clave', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (26, 'Crítico', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (27, 'Crucial', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (28, 'Decisicivo', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (29, 'Desecar', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (30, 'Esencial', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (31, 'Neurálgico', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (32, 'Primordial', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (33, 'Secar', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (34, 'Vital', 2);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (35, 'Lección', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (36, 'Disertación', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (37, 'Clase', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (38, 'Conferencia', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (39, 'Asignatura', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (40, 'Aleccionamiento', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (41, 'Enseñanza', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (42, 'Erudicción', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (43, 'Cultura', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (44, 'Sabiduría', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (45, 'Saber', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (46, 'Instrucción', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (47, 'Conocimiento', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (48, 'Estudios', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (49, 'Sapiencia', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (50, 'Universo', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (51, 'Mundo', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (52, 'Globo', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (53, 'Planeta', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (54, 'Suelo', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (55, 'Firme', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (56, 'Piso', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (57, 'Pavimento', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (58, 'Barro', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (59, 'Polvo', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (60, 'Greda', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (61, 'Arcilla', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (62, 'Marga', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (63, 'Patria', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (64, 'País', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (65, 'Nación', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (66, 'Región', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (67, 'Pueblo', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (68, 'Territorio', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (69, 'Comarca', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (70, 'Terreno', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (71, 'Campo', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (72, 'Finca', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (73, 'Granja', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (74, 'Cultivo', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (75, 'Hacienda', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (76, 'Palpable', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (77, 'Tangible', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (78, 'Corpóreo', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (79, 'Físico', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (80, 'Natural', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (81, 'Real', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (82, 'Terrenal', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (83, 'Materia', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (84, 'Elemento', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (85, 'Ingrediente', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (86, 'Componente', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (87, 'Utilitario', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (88, 'Práctico', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (89, 'Pragmático', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (90, 'Materialista', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (91, 'Realista', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (92, 'Instrumental', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (93, 'Instrumento', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (94, 'Herramienta', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (95, 'Maquinaria', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (96, 'Equipo', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (97, 'Aperos', 3);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (98, 'Especialista', 4);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (99, 'Experto', 4);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (100, 'Perito', 4);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (101, 'Técnico', 4);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (102, 'Profesional', 4);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (103, 'Diplomado', 4);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (104, 'Mecánica', 5);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (105, 'Industria', 5);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (106, 'Mecanización', 5);
INSERT INTO `sidecar2`.`sinonimos` (`idSinonimos`, `NombreSinonimos`, `area_idArea`) VALUES (107, 'Automatismo', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sidecar2`.`sinonimosarea`
-- -----------------------------------------------------
START TRANSACTION;
USE `sidecar2`;
INSERT INTO `sidecar2`.`sinonimosarea` (`idsinonimosarea`, `sinonimo`, `area_idArea`) VALUES (1, 'Computación', 1);
INSERT INTO `sidecar2`.`sinonimosarea` (`idsinonimosarea`, `sinonimo`, `area_idArea`) VALUES (2, 'Electronica', 1);
INSERT INTO `sidecar2`.`sinonimosarea` (`idsinonimosarea`, `sinonimo`, `area_idArea`) VALUES (3, 'Informatica ', 1);
INSERT INTO `sidecar2`.`sinonimosarea` (`idsinonimosarea`, `sinonimo`, `area_idArea`) VALUES (4, 'Biologia', 2);
INSERT INTO `sidecar2`.`sinonimosarea` (`idsinonimosarea`, `sinonimo`, `area_idArea`) VALUES (5, 'Ciencias', 3);
INSERT INTO `sidecar2`.`sinonimosarea` (`idsinonimosarea`, `sinonimo`, `area_idArea`) VALUES (6, 'Tierra', 3);
INSERT INTO `sidecar2`.`sinonimosarea` (`idsinonimosarea`, `sinonimo`, `area_idArea`) VALUES (7, 'Materiales ', 3);
INSERT INTO `sidecar2`.`sinonimosarea` (`idsinonimosarea`, `sinonimo`, `area_idArea`) VALUES (8, 'Ingenieria', 4);
INSERT INTO `sidecar2`.`sinonimosarea` (`idsinonimosarea`, `sinonimo`, `area_idArea`) VALUES (9, 'Arquitectura', 4);
INSERT INTO `sidecar2`.`sinonimosarea` (`idsinonimosarea`, `sinonimo`, `area_idArea`) VALUES (10, 'Matematicas', 5);
INSERT INTO `sidecar2`.`sinonimosarea` (`idsinonimosarea`, `sinonimo`, `area_idArea`) VALUES (11, 'Fisica', 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sidecar2`.`sinonimosdisciplina`
-- -----------------------------------------------------
START TRANSACTION;
USE `sidecar2`;
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (1, 'Computacion', 1);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (2, 'Inteligente', 1);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (3, 'Computacion', 2);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (4, 'Educativa', 2);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (5, 'Educacion', 2);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (6, 'Electronica', 3);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (7, 'Control', 3);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (8, 'Biologia', 4);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (9, 'Materiales', 5);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (10, 'Ciencias', 6);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (11, 'Tierra', 6);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (12, 'Metalurgica', 7);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (13, 'Metalurgia', 7);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (14, 'Procesos', 8);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (15, 'Qimicos', 8);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (16, 'Quimica', 8);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (17, 'Fisicos', 8);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (18, 'Fisica', 8);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (19, 'Tecnologia', 9);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (20, 'Ingenieria', 9);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (21, 'Ingenieria', 10);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (22, 'Sistemas', 10);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (23, 'Organizacionales ', 10);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (24, 'Organizacion', 10);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (25, 'Ingenieria', 11);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (26, 'Civil', 11);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (27, 'Sustentable', 11);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (28, 'Arquitectura ', 12);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (29, 'Urbanismo', 12);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (30, 'Urbano', 12);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (31, 'Sustentabiliadad', 12);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (32, 'Matematicas', 13);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (33, 'Fisica', 13);
INSERT INTO `sidecar2`.`sinonimosdisciplina` (`idsinonimosdisciplina`, `sinonimo`, `disciplina_idDisciplina`) VALUES (34, 'Quimica', 14);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sidecar2`.`sinonimosinvestigacion`
-- -----------------------------------------------------
START TRANSACTION;
USE `sidecar2`;
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (1, 'Distribuido', 1);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (2, 'Paralelo ', 1);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (3, 'Data', 2);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (4, 'Minining', 2);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (5, 'Mineria ', 2);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (6, 'Datos ', 2);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (7, 'Inovacion ', 3);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (8, 'Evaluacion ', 3);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (9, 'Educativa', 3);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (10, 'Educacion', 3);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (11, 'Desarrollo ', 4);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (12, 'Software', 4);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (13, 'Educativo', 4);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (14, 'Distribuido', 5);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (15, 'Lineal', 6);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (16, 'No Lineal', 7);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (17, 'Conservacion ', 8);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (18, 'Biologica', 8);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (19, 'Comunidades', 9);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (20, 'Paisaje', 10);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (21, 'Ordenamiento ', 10);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (22, 'Ambiental ', 10);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (23, 'Poblaciones', 11);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (24, 'Pueblo', 11);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (25, 'Etnobotanica', 12);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (26, 'Genetica', 13);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (27, 'Historia', 14);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (28, 'Educacion ', 14);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (29, 'Interacciones ', 15);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (30, 'Biologica', 15);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (31, 'Micrologia', 16);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (32, 'Modificacion ', 17);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (33, 'Estructura', 17);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (34, 'Modelado', 17);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (35, 'Materiales ', 17);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (36, 'Sintesis ', 18);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (37, 'Carecterizacion', 18);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (38, 'Polimeros', 18);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (39, 'Geodinamica', 19);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (40, 'Morfisologia ', 20);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (41, 'Animal', 20);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (42, 'Morfisologia ', 21);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (43, 'Vegetal', 21);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (44, 'Paleozoología', 22);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (45, 'Animal', 23);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (46, 'Molecular', 24);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (47, 'Vegetal', 25);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (48, 'Compuestos', 26);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (49, 'Geodinamica', 29);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (50, 'Recursos ', 29);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (51, 'Minerales', 29);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (52, 'Hidrogeologia', 30);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (53, 'Geologia', 31);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (54, 'Ambiental', 31);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (55, 'Geoquimica', 32);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (56, 'Corrosion', 33);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (57, 'Recubrimientos', 34);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (58, 'Procesos ', 35);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (59, 'Metalurgia ', 35);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (60, 'Extractiva', 35);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (61, 'Sólidos', 36);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (62, 'Particulados', 36);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (63, 'Procesamiento ', 37);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (64, 'Nanoestructurados', 37);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (65, 'Automatización ', 38);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (66, 'Optimización ', 38);
INSERT INTO `sidecar2`.`sinonimosinvestigacion` (`idsinonimosinvestigacion`, `sinonimos`, `lineadeinvestigación_idLineaDeInvestigación`) VALUES (67, 'Manufactura', 38);

COMMIT;

