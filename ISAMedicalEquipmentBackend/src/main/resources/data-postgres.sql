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
	'1708991620486', 'FEMALE', 'Administrator kompanije', 'Apotekarska ustanova Janković', 
	2, 0, 0, 1);

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

-- Loyalty programs:
INSERT INTO Loyalty_programs(id, name, necessary_points, 
	points_gained_for_each_successful_exchange) VALUES (1, 'Osnovni', 0, 2);
INSERT INTO Loyalty_programs(id, name, necessary_points, 
	points_gained_for_each_successful_exchange) VALUES (2, 'Srebrni', 6, 3);

-- Medical equipment companies:
-- REFERENCE: https://stackoverflow.com/questions/26638615/insert-line-break-in-postgresql-when-updating-text-field
INSERT INTO Medical_equipment_companies(id, name, address, description, average_grade) VALUES (1, 
	'Galenika', 'Batajnički drum b.b., 11080 Beograd - Zemun', 
	CONCAT('Galenika je najstarija farmaceutska kompanija u regionu. Galenika je osnovana 1945. ', 
	'godine. Ime je dobila po Galenu, začetniku naučne farmacije. Galenika je danas deo brazilske', 
	' NC Grupe, koja obuhvata i EMS – najveću farmaceutsku kompaniju u Brazilu i jednu od ', 
	'najvećih farmaceutskih kompanija u celoj Latinskoj Americi.', CHR(10), 
	'Galenika na dlanu Portfolio proizvoda: Uz portfolio od više od 250 proizvoda, Galenika ', 
	'proizvodi najsavremenije generičke lekove, dijetetske suplemente, kozmetičke proizvode i ', 
	'medicinska sredstva.'), 4.0, 'Ponedeljak – petak: 08:00 – 17:00');
INSERT INTO Medical_equipment_companies(id, name, address, description, average_grade) VALUES (2, 
	'Apotekarska ustanova Janković', 'Dr. Ilije Đuričića 2A, 21000 Novi Sad', 
	CONCAT('Poverenje, sigurnost i dostupnost su, već skoro 30 godina, glavna obeležja ', 
	'Apotekarske ustanove "Janković". Podrška koju svakodnevno dobijamo od vernih klijenata ', 
	'omogućila je da postanemo jedna od najpoznatijih i najposećenijih apoteka.', CHR(10), 
	'Apotekarsku ustanovu "Janković" možete posetiti na čak 150 lokacija, širom Vojvodine i ', 
	'dela centralne Srbije.', CHR(10), 
	'Naš ceo stručni tim od preko 600 članova, koji se sastoji od farmaceutskih tehničara, ', 
	'diplomiranih farmaceuta, magistara farmacije, specijalista i doktora nauka, stoji Vam na ', 
	'raspolaganju. Konstantnim edukacijama, stručnim usavršavanjima, praćenjem inovacija na ', 
	'globalnom nivou, trudimo se da prenesemo znanje, pomognemo u očuvanju i poboljšanju ', 
	'zdravlja, ali i da povećamo svest o zdravom životu.', CHR(10), 
	'Pored izuzetne snabdevenosti lekovima i medicinskim sredstvima, nudimo i širok asortiman ', 
	'dijetetskih suplemenata, kozmetike, opreme za bebe. Izdajemo lekove na recept civilnim i ', 
	'vojnim osiguranicima, izrađujemo magistralne lekove, podeljene praškove i galenske lekove. ', 
	'Svakodnevno Vam nudimo pregršt akcija, promocija i specijalnih ponuda.'), 4.5, 
	CONCAT('Ponedeljak - petak: 07:30 - 21:30;', CHR(10), 'Subota: 07:30 - 21:30;', CHR(10), 
	'Nedelja: 07:30 - 21:30'));

-- Medical equipment:
INSERT INTO Medical_equipment(id, type_id, name, price, amount, company_id) VALUES (1, 1, 
	'Borna voda 250ml', 199.99, 900, 1);
INSERT INTO Medical_equipment(id, type_id, name, price, amount, company_id) VALUES (2, 2, 
	'Komprese 10 x 10 sterilne Niva', 149.99, 7500, 1);
INSERT INTO Medical_equipment(id, type_id, name, price, amount, company_id) VALUES (3, 3, 
	'Hydrocyclin mast 20g', 349.99, 600, 1);
INSERT INTO Medical_equipment(id, type_id, name, price, amount, company_id) VALUES (4, 4, 
	'Diklofen gastrorezistentna tableta 20 x 50mg', 299.99, 13000, 1);
INSERT INTO Medical_equipment(id, type_id, name, price, amount, company_id) VALUES (5, 3, 
	'Jekoderm mast 25g', 249.99, 400, 2);
INSERT INTO Medical_equipment(id, type_id, name, price, amount, company_id) VALUES (6, 5, 
	'Asepsol rastvor 1% 1l', 149.12, 22000, 2);
INSERT INTO Medical_equipment(id, type_id, name, price, amount, company_id) VALUES (7, 6, 
	'Scholl Party feet gel jastučići', 577.44, 500, 2);
INSERT INTO Medical_equipment(id, type_id, name, price, amount, company_id) VALUES (8, 7, 
	'Bebicol Forte 10ml probiotske kapi', 1157.91, 750, 2);

-- Types of medical equipment:
INSERT INTO Types_of_medical_equipment(id, name) VALUES (1, 
	'Kozmetički proizvodi');
INSERT INTO Types_of_medical_equipment(id, name) VALUES (2, 
	'Prva pomoć');
INSERT INTO Types_of_medical_equipment(id, name) VALUES (3, 
	'Lekovi za lečenje kože i potkožnog tkiva');
INSERT INTO Types_of_medical_equipment(id, name) VALUES (4, 
	'Lekovi za lečenje bolesti mišićno-koštanog sistema');
INSERT INTO Types_of_medical_equipment(id, name) VALUES (5, 
	'Sredstva za dezinfekciju');
INSERT INTO Types_of_medical_equipment(id, name) VALUES (6, 
	'Ulošci za stopala');
INSERT INTO Types_of_medical_equipment(id, name) VALUES (7, 
	'Apoteka za bebe');

-- Complaints:
INSERT INTO Complaints(id, content, status, answer, procurement_manager_id, company_id, 
	company_administrator_id) VALUES (1, 'Popusti prekratko traju.', 'UNRESOLVED', null, 
	1, 1, null);

-- Exchange terms:
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (1, '2023-11-15 08:00:00.508-07', '2023-11-15 08:30:00.508-07', 
	1, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (2, '2023-11-27 08:00:00.508-07', '2023-11-27 08:30:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (3, '2023-11-27 08:30:00.508-07', '2023-11-27 09:00:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (4, '2023-11-27 09:00:00.508-07', '2023-11-27 09:30:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (5, '2023-11-27 09:30:00.508-07', '2023-11-27 10:00:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (6, '2023-11-27 10:00:00.508-07', '2023-11-27 10:30:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (7, '2023-11-27 10:30:00.508-07', '2023-11-27 11:00:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (8, '2023-11-27 11:00:00.508-07', '2023-11-27 11:30:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (9, '2023-11-27 11:30:00.508-07', '2023-11-27 12:00:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (10, '2023-11-27 13:00:00.508-07', '2023-11-27 13:30:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (11, '2023-11-27 13:30:00.508-07', '2023-11-27 14:00:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (12, '2023-11-27 14:00:00.508-07', '2023-11-27 14:30:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (13, '2023-11-27 14:30:00.508-07', '2023-11-27 15:00:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (14, '2023-11-27 15:00:00.508-07', '2023-11-27 15:30:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (15, '2023-11-27 15:30:00.508-07', '2023-11-27 16:00:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (16, '2023-11-27 16:00:00.508-07', '2023-11-27 16:30:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (17, '2023-11-27 16:30:00.508-07', '2023-11-27 17:00:00.508-07', 
	null, 1, 3);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (18, '2023-11-27 07:30:00.508-07', '2023-11-27 08:00:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (19, '2023-11-27 08:00:00.508-07', '2023-11-27 08:30:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (20, '2023-11-27 08:30:00.508-07', '2023-11-27 09:00:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (21, '2023-11-27 09:00:00.508-07', '2023-11-27 09:30:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (22, '2023-11-27 09:30:00.508-07', '2023-11-27 10:00:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (23, '2023-11-27 10:00:00.508-07', '2023-11-27 10:30:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (24, '2023-11-27 10:30:00.508-07', '2023-11-27 11:00:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (25, '2023-11-27 11:00:00.508-07', '2023-11-27 11:30:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (26, '2023-11-27 12:00:00.508-07', '2023-11-27 12:30:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (27, '2023-11-27 12:30:00.508-07', '2023-11-27 13:00:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (28, '2023-11-27 13:00:00.508-07', '2023-11-27 13:30:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (29, '2023-11-27 13:30:00.508-07', '2023-11-27 14:00:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (30, '2023-11-27 14:00:00.508-07', '2023-11-27 14:30:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (31, '2023-11-27 14:30:00.508-07', '2023-11-27 15:00:00.508-07', 
	null, 2, 4);
INSERT INTO Exchange_terms(id, starting_time, ending_time, procurement_manager_id, company_id, 
	administrator_id) VALUES (32, '2023-11-27 15:00:00.508-07', '2023-11-27 15:30:00.508-07', 
	null, 2, 4);
