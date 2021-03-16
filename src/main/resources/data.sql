

--SUBSIDIARIES
INSERT INTO subsidiary values('0001', null, 'Filial 01 AR', 'calle 01', '11111111', 'ARGENTINA');
INSERT INTO subsidiary values('0002', null, 'Filial 02 UR', 'calle 02', '21111111', 'CHILE');
INSERT INTO subsidiary values('0003', null, 'Filial 03 CH', 'calle 03', '31111111', 'URUGUAY');

--PROVIDERS
INSERT INTO providers values(1,'calle','argentina','provedor 1', '22222');
INSERT INTO providers values(2,'calle otra','Uruguay','provedor 2', '23445542');
INSERT INTO providers values(3,'dir 123','Chile','provedor 3', '15672999');
INSERT INTO providers values(4,'street 41','argentina','provedor 4', '22008291');

-- PARTS
insert into parts values('AA10', 'Puerta negra', 10, 20, 30, 10, 1);
insert into parts values('AA11', 'Puerta roja', 10, 20, 30, 10, 1);
insert into parts values('BA21', 'rueda ', 10, 20, 30, 10, 1);
insert into parts values('CD01', 'capo', 10, 20, 30, 10, 2);
insert into parts values('FF10', 'Puerta negra', 10, 20, 30, 10, 2);
insert into parts values('GG31', 'Puerta roja', 10, 20, 30, 10, 3);
insert into parts values('FG21', 'rueda ', 10, 20, 30, 10, 3);
insert into parts values('HG01', 'capo', 10, 20, 30, 10, 4);
insert into parts values('IJ02', 'Luz led', 5, 5, 5, 15, 3);
insert into parts values('JJ89', 'Parabrisa', 15, 10, 40, 50, 3);
insert into parts values('KJ99', 'Ventanilla', 10, 10, 20, 50, 3);
insert into parts values('IJ31', 'Llanta', 25, 30, 30, 60, 3);
insert into parts values('XX32', 'Repuesto 1', 5, 5, 5, 10, 4);
insert into parts values('XY17', 'Paragolpe', 15, 10, 30, 10, 4);
insert into parts values('ZY99', 'Pintura', 10, 10, 30, 10, 4);
insert into parts values('XZ10', 'Carburador', 25, 30, 30, 10, 4);

-- PARTS RECORDS
insert into part_records values('0000000001', '2020-02-10', 1900, 1700, 1200, 'AA10');
insert into part_records values('0000000002', '2020-02-10', 2100, 1800, 1300, 'AA11');
insert into part_records values('0000000003', '2020-02-10', 1755, 1700, 1200, 'BA21');
insert into part_records values('0000000004', '2020-03-10', 1002, 700, 5200, 'CD01');
insert into part_records values('0000000005', '2020-03-10', 175, 170, 120, 'FF10');
insert into part_records values('0000000006', '2020-03-10', 1100, 700, 500, 'GG31');
insert into part_records values('0000000007', '2020-02-12', 3000, 2700, 1700, 'HG01');
insert into part_records values('0000000008', '2020-02-12', 3300, 2850, 2000, 'FG21');
insert into part_records values('0000000009','2020-02-01', 3500, 3200, 1900,'CD01');
insert into part_records values('0000000010','2019-12-01', 2500, 2200, 900,'CD01');
insert into part_records values('0000000011','2019-02-01', 2200, 1800, 700,'CD01');
insert into part_records values('0000000012','2020-02-11', 3000, 2900, 1850,'IJ31');
insert into part_records values('0000000013','2019-12-11', 2000, 1900, 1850,'IJ31');
insert into part_records values('0000000014','2019-02-11', 1000, 900, 850,'IJ31');
insert into part_records values('0000000015','2020-03-01', 3100, 3000, 2800,'JJ89');



--STOCK

insert into stock values(100, '0001', 'AA10');
insert into stock values(30, '0002', 'AA10');
insert into stock values(0, '0003', 'AA10');
insert into stock values(20, '0001', 'AA11');
insert into stock values(15, '0001', 'BA21');
insert into stock values(60, '0001', 'XX32');

insert into stock values(10, '0001', 'JJ89');
insert into stock values(80, '0001', 'IJ31');
insert into stock values(20, '0002', 'AA11');
insert into stock values(15, '0002', 'BA21');
insert into stock values(60, '0003', 'XX32');

insert into stock values(10, '0003', 'JJ89');
insert into stock values(90, '0002', 'IJ31');
insert into stock values(150, '0003', 'BA21');


--DELIVERY STATUS

insert into delivery_status values(1, 'P','Pendientes de entrega');
insert into delivery_status values(2, 'D','Demorado');
insert into delivery_status values(3, 'F','Finalizado');
insert into delivery_status values(4, 'C','Cancelado');

--PART STATUS
insert into part_status (id_part_status, code, description)
values (1, 'N', 'Normal'),
       (2, 'D', 'Demorado');

--ACCOUNT TYPES
insert into account_types
(id_account_type, description)
values (1, 'Repuestos'), (2, 'Garantía');

-- DEALERS

insert into dealers (subsidiary_id, dealer_number, address, country, name, phone)
values ('0001', '0001', 'calle 1', 'argentina', 'Concesionario 01 AR', '1111111'),
       ('0002', '0002', 'calle 2', 'Uruguay', 'Concesionario 01 UY', '222222'),
       ('0003', '0003', 'otra calle', 'Chile', 'Concesionario 01 CH','111111'),
       ('0003', '0004', 'Mas calle', 'Uruguay', 'Concesionario 02 CH','111111'),
       ('0002', '0005', 'street 1', 'Chile', 'Concesionario 02 UY','111111'),
       ('0001', '0006', 'street 2', 'argentina', 'Concesionario 02 AR','111111');

--ORDERS

INSERT INTO `deluxedb`.`orders`
(`id_order_cm`,`days_delayed`,`delivery_date`,`order_date`,`order_number_ce`,`order_number_cm`,`serialNumber`,`id_dealer`,`id_delivery_status`)
VALUES
(1,2,'2021-03-03','2021-03-01','0001-00000001','0001-0001-00000001',1111123,'0001',3),
(2,2,'2021-03-03','2021-03-01','0001-00000002','0001-0001-00000002',1111124,'0001',3),
(3,0,null,'2021-03-16','0001-00000003','0001-0001-00000003',1111125,'0001',1),
(4,7,'2021-03-10','2021-03-03','0001-00000001','0006-0001-00000001',6611123,'0006',2),
(5,0,'2021-02-15','2021-02-15','0001-00000001','0002-0002-00000001',2211123,'0002',3),
(6,1,'2021-03-16','2021-03-15','0001-00000001','0004-0002-00000001',4411123,'0004',1);


--ORDER DETAILS
INSERT INTO `deluxedb`.`order_details_cm`
(`id_order_details_cm`,`quantity`,`reason`,`id_account_type`,`id_order_cm`,`part_code`,`id_part_status`)
VALUES
(1,3,'Sin motivo',1,1,'AA10',1),
(2,1,'Sin motivo',1,1,'XX32',1),
(3,2,'Sin motivo',1,1,'AA11',1),
(4,10,'Sin motivo',1,1,'ZY99',1),
(5,3,'Sin motivo',1,2,'KJ99',1),
(6,3,'Sin motivo',1,2,'FF10',1),
(7,3,'Sin motivo',1,2,'XZ10',1),
(8,3,'Sin motivo',1,3,'AA10',1),
(9,3,'Sin motivo',1,3,'BA21',1),
(10,3,'Sin motivo',1,3,'AA10',1),
(11,3,'Sin motivo',1,4,'IJ31',1),
(12,3,'Sin motivo',1,4,'AA10',1),
(13,3,'Sin motivo',1,4,'CD01',1),
(14,3,'Sin motivo',1,5,'AA10',1),
(15,3,'Sin motivo',1,5,'BA21',1),
(16,3,'Sin motivo',1,5,'KJ99',1),
(17,3,'Sin motivo',1,5,'XZ10',1),
(18,3,'Sin motivo',1,6,'IJ31',1),
(19,3,'Sin motivo',1,6,'IJ02',1),
(20,3,'Sin motivo',1,6,'AA10',1),
(21,3,'Sin motivo',1,6,'CD01',1);
