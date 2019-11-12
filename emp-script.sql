-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- --------------------------------------------------
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
  `dept_id` INT(11) NOT NULL,
  `dept_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `emp_management`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `emp_management`.`employee` (
  `emp_id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `birth_date` DATE NULL DEFAULT NULL,
  `hire_date` DATE NULL DEFAULT NULL,
  `dept_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `emp_management`.`emp_dept`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `emp_management`.`emp_dept` (
  `dept_id` INT(11) NOT NULL,
  `emp_id` INT(11) NOT NULL,
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
-- Table `emp_management`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `emp_management`.`hibernate_sequence` (
  `next_val` BIGINT(20) NULL DEFAULT NULL)
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `emp_management`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `emp_management`.`role` (
  `role_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = latin1;


/*
-- Query: select * from emp_management.role
LIMIT 0, 1000

-- Date: 2019-11-12 21:10
*/
INSERT INTO `role` (`role_id`,`name`) VALUES (1,'CUSTOMER');
INSERT INTO `role` (`role_id`,`name`) VALUES (2,'MANAGER');
INSERT INTO `role` (`role_id`,`name`) VALUES (32,'EMPLOYEE');
INSERT INTO `role` (`role_id`,`name`) VALUES (34,'ADMIN');

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
  `enabled` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 62
DEFAULT CHARACTER SET = latin1;


/*
-- Query: SELECT * FROM emp_management.user_info
LIMIT 0, 1000


*/
INSERT INTO `user_info` (`user_id`,`user_name`,`password`,`first_name`,`last_name`,`creation_date`,`email`,`enabled`) VALUES (59,'jasvinder','$2a$10$FamucyX9fIS4qaMDgc0kceIzpoFhKafDLsWFiNLVPTM5b4HalDLBy','Jasvinder','singh','2019-10-12',NULL,NULL);
INSERT INTO `user_info` (`user_id`,`user_name`,`password`,`first_name`,`last_name`,`creation_date`,`email`,`enabled`) VALUES (60,'lucky','$2a$10$RMTQGF5s0Q.0LvREY8JLk.VW14P7QOEggzHn45y2hL8Bqaj8cgHyG','lucky','singh','2019-10-12',NULL,NULL);
INSERT INTO `user_info` (`user_id`,`user_name`,`password`,`first_name`,`last_name`,`creation_date`,`email`,`enabled`) VALUES (61,'jassi',NULL,'jassi','singh','2019-10-12',NULL,NULL);


-- -----------------------------------------------------
-- Table `emp_management`.`users_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `emp_management`.`users_roles` (
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_user_info_has_role_role1_idx` (`role_id` ASC),
  INDEX `fk_user_info_has_role_user_info1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_info_has_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `emp_management`.`role` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_info_has_role_user_info1`
    FOREIGN KEY (`user_id`)
    REFERENCES `emp_management`.`user_info` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

/*
-- Query: select * from emp_management.users_roles
LIMIT 0, 1000

-- Date: 2019-11-12 21:10
*/
INSERT INTO `users_roles` (`user_id`,`role_id`) VALUES (59,1);
INSERT INTO `users_roles` (`user_id`,`role_id`) VALUES (61,2);
INSERT INTO `users_roles` (`user_id`,`role_id`) VALUES (60,32);
INSERT INTO `users_roles` (`user_id`,`role_id`) VALUES (61,32);
INSERT INTO `users_roles` (`user_id`,`role_id`) VALUES (59,34);
INSERT INTO `users_roles` (`user_id`,`role_id`) VALUES (61,34);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
