-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema emp_management
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema emp_management
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `emp_management` DEFAULT CHARACTER SET latin1 ;
USE `emp_management` ;

-- -----------------------------------------------------
-- Table `emp_management`.`departments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `emp_management`.`departments` (
  `dept_id` INT NOT NULL,
  `dept_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `emp_management`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `emp_management`.`employee` (
  `emp_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `birth_date` DATE NULL DEFAULT NULL,
  `hire_date` DATE NULL,
  `dept_name` VARCHAR(45) NULL,
  PRIMARY KEY (`emp_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `emp_management`.`emp_dept`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `emp_management`.`emp_dept` (
  `dept_id` INT NOT NULL,
  `emp_id` INT NOT NULL,
  `from_date` DATE NULL DEFAULT NULL,
  `to_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`, `emp_id`),
  INDEX `fk_departments_has_employee_employee1_idx` (`emp_id` ASC),
  INDEX `fk_departments_has_employee_departments_idx` (`dept_id` ASC),
  CONSTRAINT `fk_departments_has_employee_departments`
    FOREIGN KEY (`dept_id`)
    REFERENCES `emp_management`.`departments` (`dept_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_departments_has_employee_employee1`
    FOREIGN KEY (`emp_id`)
    REFERENCES `emp_management`.`employee` (`emp_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `emp_management`.`user_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `emp_management`.`user_info` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(60) NULL DEFAULT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `creation_date` DATE NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
