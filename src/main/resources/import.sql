insert into Doctor(doc_id,Name) values (1,'John');
insert into user_entity(user_id,Username,Password,Name,Type,doctor_id) VALUES (11,'Eilish','pass','Eilish Ni Thuama',1,1);
insert into user_entity(user_id,Username,Password,Name,Type,doctor_id) VALUES (12,'doc','pass','John McCarthy',2,1);
insert into user_entity(user_id,Username,Password,Name,Type,doctor_id) VALUES (13,'Mary','pass','Mary Murphy',3,1);
insert into user_entity(user_id,Username,Password,Name,Type,doctor_id) VALUES (14,'Joe','pass','Joe Murphy',3,1);
insert into user_entity(user_id,Username,Password,Name,Type,doctor_id) VALUES (15,'Ruth','pass','Ruth O''Riordan',3,1);
insert into Carer(carer_id,user_id,Name,Mobile) VALUES(1,11,'Mary Murphy','0867311035');
insert into Carer(carer_id,user_id,Name,Mobile) VALUES(2,11,'Joe Murphy','0867311035');
insert into Carer(carer_id,user_id,Name,Mobile) VALUES(3,11,'Ruth O''Riordan','0867311035');