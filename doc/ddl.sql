-- MySQL Script generated by MySQL Workbench
-- Thu Nov  2 20:56:11 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema store
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema store
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `store` DEFAULT CHARACTER SET utf8 ;
USE `store` ;

-- -----------------------------------------------------
-- Table `store`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` NVARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` NVARCHAR(45) NULL,
  `category_id` INT NULL,
  `price` DECIMAL NULL,
  `info` NVARCHAR(45) NULL,
  `quantity` INT NULL,
  `sales` INT NULL,
  `pic` NVARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_category_idx` (`category_id` ASC),
  CONSTRAINT `fk_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `store`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`accounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` NVARCHAR(45) NULL,
  `password` CHAR(32) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`address` (
  `province` NVARCHAR(45) NULL,
  `city` NVARCHAR(45) NULL,
  `street` NVARCHAR(45) NULL,
  `name` NVARCHAR(45) NULL,
  `phone` NVARCHAR(45) NULL,
  `account_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `store`.`accounts` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`carts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`carts` (
  `account_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NULL DEFAULT 1,
  PRIMARY KEY (`account_id`, `product_id`),
  INDEX `fk_cart_product_idx` (`product_id` ASC),
  CONSTRAINT `fk_cart_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `store`.`accounts` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `store`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `account_id` INT NOT NULL,
  `time` DATETIME NOT NULL DEFAULT now(),
  `total` DECIMAL NOT NULL,
  `province` NVARCHAR(45) NOT NULL,
  `city` NVARCHAR(45) NOT NULL,
  `street` NVARCHAR(45) NOT NULL,
  `phone` CHAR(11) NOT NULL,
  `name` NVARCHAR(45) NOT NULL,
  `state` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_order_account_idx` (`account_id` ASC),
  CONSTRAINT `fk_order_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `store`.`accounts` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`items` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NULL,
  `num` INT NULL,
  `total` DECIMAL NULL,
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX `fk_item_product_idx` (`product_id` ASC),
  CONSTRAINT `fk_item_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `store`.`orders` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `store`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
