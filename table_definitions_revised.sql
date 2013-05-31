SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`User` (
  `UID` INT NOT NULL ,
  `Fname` VARCHAR(45) NULL ,
  `Lname` VARCHAR(45) NULL ,
  `Email` VARCHAR(45) NULL ,
  PRIMARY KEY (`UID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Group`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Group` (
  `GroupID` INT NOT NULL ,
  `GroupName` VARCHAR(45) NULL ,
  `OwnerID` INT NULL ,
  `Date_Of_Creation` DATE NULL ,
  `UserList_UID` INT NOT NULL ,
  PRIMARY KEY (`GroupID`) ,
  INDEX `fk_Group_UserList1` (`UserList_UID` ASC) ,
  CONSTRAINT `fk_Group_UserList1`
    FOREIGN KEY (`UserList_UID` )
    REFERENCES `mydb`.`User` (`UID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Authentication`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Authentication` (
  `UID_Tracker` INT NOT NULL ,
  `UID_Trackee` INT NOT NULL ,
  `Data` DATE NULL ,
  `Time` TIME NULL ,
  `User_UID` INT NOT NULL ,
  INDEX `fk_Authentication_User1` (`User_UID` ASC) ,
  CONSTRAINT `fk_Authentication_User1`
    FOREIGN KEY (`User_UID` )
    REFERENCES `mydb`.`User` (`UID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Track_History`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Track_History` (
  `UID` INT NOT NULL ,
  `Data` DATE NULL ,
  `Time` TIME NULL ,
  `Location_x` DECIMAL NULL ,
  `Location_y` DECIMAL NULL ,
  `UserList_UID` INT NOT NULL ,
  INDEX `fk_Track_History_UserList1` (`UserList_UID` ASC) ,
  CONSTRAINT `fk_Track_History_UserList1`
    FOREIGN KEY (`UserList_UID` )
    REFERENCES `mydb`.`User` (`UID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Group_Lists`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Group_Lists` (
  `Group_ID` INT NOT NULL ,
  `UID` INT NULL ,
  `Group_GroupID` INT NOT NULL ,
  INDEX `fk_Group_Lists_Group1` (`Group_GroupID` ASC) ,
  CONSTRAINT `fk_Group_Lists_Group1`
    FOREIGN KEY (`Group_GroupID` )
    REFERENCES `mydb`.`Group` (`GroupID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Placeholder table for view `mydb`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`view1` (`id` INT);

-- -----------------------------------------------------
-- View `mydb`.`view1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`view1`;
USE `mydb`;
;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
