-- Procurement managers of hospitals:
-- Password for this user is 'pBorisavljevic1999'.
INSERT INTO Procurement_managers(id, is_enabled, user_code, email_address, username, password, 
	last_password_reset_date, first_name, last_name, residence, populated_place, country, 
	phone_number, personal_identity_number, gender, profession, company_name, penalty_points, 
	loyalty_points, loyalty_program_id) VALUES (1, true, 
	'2210b5da85e7867d0b4186f510ca4f6d0629012bc23f477cc405076b32028b3e', 
	'pavle.borisavljevic@gmail.com', 'PavleBorisavljevic1999', 
	'$2a$10$4MBoCfFOa8U0OrT8ZC34ZOhFAwh8MHpF7fMnXFurJX1VpbVvUsktq', '2023-11-11 09:35:00.508-07', 
	'Pavle', 'Borisavljević', 'Fruškogorska 37', 'Novi Sad', 'Srbija', '+381 63569456', 
	'0601999710135', 'MALE', 'Šef nabavke', 'Klinički centar Vojvodine', 0, 0, 1);
-- Password for this user is 'dMilosevic2000'.
INSERT INTO Procurement_managers(id, is_enabled, user_code, email_address, username, password, 
	last_password_reset_date, first_name, last_name, residence, populated_place, country, 
	phone_number, personal_identity_number, gender, profession, company_name, penalty_points, 
	loyalty_points, loyalty_program_id) VALUES (2, true, 
	'1e8a7e71cd25d51fd5d4c3a0b8d931db9db113dc9ad235129618c0085987615d', 
	'darko.milosevic@gmail.com', 'DarkoMilosevic2000', 
	'$2a$10$sBvg6L9Xk9iRHY7tnTd42OsCW25IctZGgD6YiNnMxXp/Dtzm/hLWW', '2023-11-12 12:58:00.508-07', 
	'Darko', 'Milošević', 'Vatroslava Jagića 9', 'Novi Sad', 'Srbija', '+381 649398427', 
	'3105000710391', 'MALE', 'Šef nabavke', 'Dom zdravlja "Milorad Mika Pavlović" Inđija', 0, 0, 1);

-- Company administrators:
-- Password for this user is 'eRajkovic1986'.
INSERT INTO Company_administrators(id, is_enabled, user_code, email_address, username, password, 
	last_password_reset_date, first_name, last_name, residence, populated_place, country, 
	phone_number, personal_identity_number, gender, profession, company_name, company_id, 
	penalty_points, loyalty_points, loyalty_program_id) VALUES (3, true, 
	'9ebde0d60831490561dc3d0e33896d46e2945374385d518f191a1d44aee586d1', 
	'emil.rajkovic@gmail.com', 'EmilRajkovic1986', 
	'$2a$10$jMY/yxEvMO239YWOSJL6f.7oRR7DQIbNEX89NmPOn1ee3dln9ELty', '2023-11-12 20:03:00.508-07', 
	'Emil', 'Rajković', 'Almaška 14', 'Novi Sad', 'Srbija', '+381 623859070', '2702986710117', 
	'MALE', 'Administrator kompanije', 'Galenika', 1, 0, 0, 1);

-- Password for this user is 'mUrosevic1991'.
INSERT INTO Company_administrators(id, is_enabled, user_code, email_address, username, password, 
	last_password_reset_date, first_name, last_name, residence, populated_place, country, 
	phone_number, personal_identity_number, gender, profession, company_name, company_id, 
	penalty_points, loyalty_points, loyalty_program_id) VALUES (4, true, 
	'8c54b356f293664914dd32b26df9e17649c127400d8bbf691d5ee9557d4730d9', 
	'milana.urosevic@gmail.com', 'MilanaUrosevic1991', 
	'$2a$10$ioMKVjvTZ2qqh4fKKOZnIu8IbqdeGjyEWRKynikFNWVOldEzBPE3u', '2023-11-13 14:27:00.508-07', 
	'Milana', 'Urošević', 'Laze Nančića 22', 'Novi Sad', 'Srbija', '+381 63682738', 
	'1708991620486', 'FEMALE', 'Administrator kompanije', 'Janković', 2, 0, 0, 1);

-- System administrators:
-- Password for this user is 'oLukic1990'.
INSERT INTO System_administrators(id, is_enabled, user_code, email_address, username, password, 
	last_password_reset_date, first_name, last_name, residence, populated_place, country, 
	phone_number, personal_identity_number, gender, profession, company_name, employed_since) 
	VALUES (5, true, '2615b98b4a5b7ed324639b59ad01730f3faf188942ea7cfa132d3addf1f43df4', 
	'olivera.lukic@gmail.com', 'OliveraLukic1990', 
	'$2a$10$LaO2qmww6/xwEVh1/rgL9Ol/7e6XZ5JosLddngJo8khkYC8QYGe36', '2023-11-14 11:53:00.508-07', 
	'Olivera', 'Lukić', 'Šumadijska 5', 'Novi Sad', 'Srbija', '+381 63770422', '2410990620808', 
	'FEMALE', 'Administrator sistema', 'Medicinska oprema', '2023-11-10 07:00:00.508-07');

-- User roles:
INSERT INTO User_roles(id, name) VALUES (1, 'ROLE_PROCUREMENT_MANAGER');
INSERT INTO User_roles(id, name) VALUES (2, 'ROLE_COMPANY_ADMINISTRATOR');
INSERT INTO User_roles(id, name) VALUES (3, 'ROLE_SYSTEM_ADMINISTRATOR');

-- Join table for users and user roles:
INSERT INTO User_roles_join_table(user_id, role_id) VALUES (1, 1);
INSERT INTO User_roles_join_table(user_id, role_id) VALUES (2, 1);
INSERT INTO User_roles_join_table(user_id, role_id) VALUES (3, 2);
INSERT INTO User_roles_join_table(user_id, role_id) VALUES (4, 2);
INSERT INTO User_roles_join_table(user_id, role_id) VALUES (5, 3);

-- REFERENCE: https://dba.stackexchange.com/questions/46125/why-does-postgres-generate-an-already-used-pk-value
-- REFERENCE: https://dba.stackexchange.com/a/90522
-- REFERENCE: https://commandprompt.com/education/is-nvl-function-same-as-coalesce-in-postgresql/
SELECT SETVAL('user_ids_sequence', (
		SELECT MAX(id) FROM (
				SELECT COALESCE(MAX(PM.id), 1) AS id FROM Procurement_managers PM
				UNION ALL
				SELECT COALESCE(MAX(CA.id), 1) AS id FROM Company_administrators CA
				UNION ALL
				SELECT COALESCE(MAX(SA.id), 1) AS id FROM System_administrators SA
		) AS max_id
));
