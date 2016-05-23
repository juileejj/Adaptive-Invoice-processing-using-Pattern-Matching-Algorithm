-- MySQL Script generated by MySQL Workbench
-- 12/07/15 20:26:17
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`header_mstr`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`header_mstr` (
  `transmission_date` DATE NOT NULL COMMENT '',
  `invoice_num` VARCHAR(10) NOT NULL COMMENT '',
  `control_num` VARCHAR(10) NOT NULL COMMENT '',
  `sender_code` VARCHAR(10) NOT NULL COMMENT '',
  `receiver_code` VARCHAR(10) NOT NULL COMMENT '',
  `invoice_header_number` VARCHAR(10) NOT NULL COMMENT '',
  `invoice_header_supplier` VARCHAR(10) NOT NULL COMMENT '',
  PRIMARY KEY (`invoice_num`)  COMMENT '')
ENGINE = InnoDB;

CREATE UNIQUE INDEX `sender_code_UNIQUE` ON `mydb`.`header_mstr` (`sender_code` ASC)  COMMENT '';


-- -----------------------------------------------------
-- Table `mydb`.`invoice_header`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`invoice_header` (
  `invoice_num` VARCHAR(10) NOT NULL COMMENT '',
  `sender_code` VARCHAR(10) NOT NULL COMMENT '',
  `bil_lto_code` VARCHAR(10) NOT NULL COMMENT '',
  `purchase_order_number` VARCHAR(10) NOT NULL COMMENT '',
  `date` DATE NOT NULL COMMENT '',
  `remarks` VARCHAR(10) NULL COMMENT '',
  PRIMARY KEY (`invoice_num`, `sender_code`)  COMMENT '',
  CONSTRAINT `fk_invoice_num_number`
    FOREIGN KEY (`invoice_num`)
    REFERENCES `mydb`.`header_mstr` (`invoice_num`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk`
    FOREIGN KEY (`sender_code`)
    REFERENCES `mydb`.`header_mstr` (`sender_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_idx` ON `mydb`.`invoice_header` (`sender_code` ASC)  COMMENT '';


-- -----------------------------------------------------
-- Table `mydb`.`address_mstr`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`address_mstr` (
  `type` VARCHAR(10) NOT NULL COMMENT '',
  `sender_code` VARCHAR(10) NOT NULL COMMENT '',
  `address_line` VARCHAR(45) NOT NULL COMMENT '',
  `zip` INT(5) NOT NULL COMMENT '',
  `phone` VARCHAR(10) NULL COMMENT '',
  `email` VARCHAR(10) NULL COMMENT '',
  PRIMARY KEY (`sender_code`)  COMMENT '',
  CONSTRAINT `fk_sender_code_code`
    FOREIGN KEY (`sender_code`)
    REFERENCES `mydb`.`header_mstr` (`sender_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `zip_UNIQUE` ON `mydb`.`address_mstr` (`zip` ASC)  COMMENT '';


-- -----------------------------------------------------
-- Table `mydb`.`Detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Detail` (
  `invoice_num` VARCHAR(10) NOT NULL COMMENT '',
  `line_number` VARCHAR(10) NOT NULL COMMENT '',
  `item_number` VARCHAR(10) NOT NULL COMMENT '',
  `quantity` INT NOT NULL COMMENT '',
  `line_price` DECIMAL(10,2) NOT NULL COMMENT '',
  PRIMARY KEY (`invoice_num`, `line_number`)  COMMENT '',
  CONSTRAINT `fk_header_invoice_number`
    FOREIGN KEY (`invoice_num`)
    REFERENCES `mydb`.`header_mstr` (`invoice_num`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `itemnumber_UNIQUE` ON `mydb`.`Detail` (`item_number` ASC)  COMMENT '';


-- -----------------------------------------------------
-- Table `mydb`.`summary_mstr`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`summary_mstr` (
  `invoice_num` VARCHAR(10) NOT NULL COMMENT '',
  `invoice_total` VARCHAR(45) NOT NULL COMMENT '',
  `total_tax` VARCHAR(45) NOT NULL COMMENT '',
  `discount_pct` DECIMAL(3,2) NULL COMMENT '',
  `discount_amount` DECIMAL(10,2) NULL COMMENT '',
  `payment_duedate` DATE NULL COMMENT '',
  `tax_pct` DECIMAL(3,2) NULL COMMENT '',
  PRIMARY KEY (`invoice_num`)  COMMENT '',
  CONSTRAINT `fk_invoice_num_invoice_num`
    FOREIGN KEY (`invoice_num`)
    REFERENCES `mydb`.`header_mstr` (`invoice_num`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = dec8
COLLATE = dec8_bin;


-- -----------------------------------------------------
-- Table `mydb`.`supplier_mstr`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`supplier_mstr` (
  `sender_code` VARCHAR(10) NOT NULL COMMENT '',
  `ship_to` VARCHAR(10) NOT NULL COMMENT '',
  `taxable` TINYINT(1) NULL COMMENT '',
  `name` VARCHAR(45) NULL COMMENT '',
  `salesperson` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`sender_code`)  COMMENT '',
  CONSTRAINT `fk_soldto_code`
    FOREIGN KEY (`sender_code`)
    REFERENCES `mydb`.`header_mstr` (`sender_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`zipcode_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`zipcode_info` (
  `zip` INT(5) NOT NULL COMMENT '',
  `city` VARCHAR(45) NULL COMMENT '',
  `state` VARCHAR(45) NULL COMMENT '',
  `country` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`zip`)  COMMENT '',
  CONSTRAINT `fk_zcode_zip`
    FOREIGN KEY (`zip`)
    REFERENCES `mydb`.`address_mstr` (`zip`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`item_mstr`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`item_mstr` (
  `item_number` VARCHAR(10) NOT NULL COMMENT '',
  `unit_of_measurement` VARCHAR(3) NULL COMMENT '',
  `price_per_qty` DECIMAL(10,2) NULL COMMENT '',
  PRIMARY KEY (`item_number`)  COMMENT '',
  CONSTRAINT `fk_code_itemnumber`
    FOREIGN KEY (`item_number`)
    REFERENCES `mydb`.`Detail` (`item_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`document_mstr`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`document_mstr` (
  `doc_id` INT NOT NULL COMMENT '',
  `doc_name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`doc_id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`table_mstr`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`table_mstr` (
  `tbl_id` INT NOT NULL COMMENT '',
  `tbl_name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`tbl_id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`column_mstr`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`column_mstr` (
  `col_id` INT NOT NULL COMMENT '',
  `col_name` VARCHAR(45) NOT NULL COMMENT '',
  `col_tab_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`col_id`)  COMMENT '',
  CONSTRAINT `fk_tbl_id_col_tab_id`
    FOREIGN KEY (`col_tab_id`)
    REFERENCES `mydb`.`table_mstr` (`tbl_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `FK_idx` ON `mydb`.`column_mstr` (`col_tab_id` ASC)  COMMENT '';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
