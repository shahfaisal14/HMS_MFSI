USE `HospitalManagement`;

SET @saltShah := CONCAT(CONV(FLOOR(RAND() * 9999999999999999999), 10, 36), CONV(FLOOR(RAND() * 9999999999999999999), 10, 36), CONV(FLOOR(RAND() * 9999999999999999999), 10, 36));

-- Default roles

INSERT INTO `role` (`created_by`, `created_date`, `modified_by`, `modified_date`, `system_of_recordx`, `version_number`, `description`, `name`, `role_id`) VALUES ("SYSTEM", NOW(), "SYSTEM", NOW(), "HMS_MFSI", 0, "Default data for roles", "Administrator", "administrator");

INSERT INTO `role` (`created_by`, `created_date`, `modified_by`, `modified_date`, `system_of_recordx`, `version_number`, `description`, `name`, `role_id`) VALUES ("SYSTEM", NOW(), "SYSTEM", NOW(), "HMS_MFSI", 0, "Default data for roles", "Hospital Head", "head");

INSERT INTO `role` (`created_by`, `created_date`, `modified_by`, `modified_date`, `system_of_recordx`, `version_number`, `description`, `name`, `role_id`) VALUES ("SYSTEM", NOW(), "SYSTEM", NOW(), "HMS_MFSI", 0, "Default data for roles", "Doctor", "doctor");

-- Default users

INSERT INTO `user` (`created_by`, `created_date`, `modified_by`, `modified_date`, `system_of_recordx`, `version_number`, `description`, `first_name`,`middle_name`, `last_name`, `is_active`, `is_terminated`, `user_id`, `email`, `role`) VALUES ("SYSTEM", NOW(), "SYSTEM", NOW(), "HMS_MFSI", 0, "Default user data", "Shah", null , "Faisal", 1, 0, "shahf", "shahf@mindfiresolutions.com", (SELECT `role`.`data_store_id` FROM `role` WHERE `role_id` = 'administrator'));

INSERT INTO `login` (`created_by`, `created_date`, `modified_by`, `modified_date`, `system_of_recordx`, `version_number`, `user`, `password`,`pass_salt`, `temp_auth_code`, `auth_code_created_time`, `expiry_duration`, `secret_key`) VALUES ("SYSTEM", NOW(), "SYSTEM", NOW(), "HMS_MFSI", 0, (SELECT `user`.`data_store_id` FROM `user` WHERE `user_id` = 'shahf'), SHA2(CONCAT('testFA@123$', @saltShah), 256), @saltShah, null, null, null, null);

-- Default configurations

INSERT INTO `configuration` (`created_by`, `created_date`, `modified_by`, `modified_date`, `system_of_recordx`, `version_number`, `code`, `description`, `value`) VALUES ("SYSTEM", NOW(), "SYSTEM", NOW(), "HMS_MFSI", 0, "max.wrong.login.attemps", "Max wrong login attempts", "10");

