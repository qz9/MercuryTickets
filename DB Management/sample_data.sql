-- Clear Data from all tables
alert table user_roles
	disable constraint user_authority_fk ;
alter table orders
	disable constraint orders_user_fk;
alter table orders
	disable constraint orders_ticket_fk;
alter table ticket
	disable constraint ticket_from_station_fk;
alter table ticket
	disable constraint ticket_to_station_fk;
alter table card
	disable constraint card_user_fk;
	
truncate table all_user;
truncate table user_roles ;
truncate table card;
truncate table station;
truncate table ticket;
truncate table orders;

alert table user_roles
	enable constraint user_authority_fk ;
alter table card
	enable constraint card_user_fk;
alter table ticket
	enable constraint ticket_to_station_fk;
alter table ticket
	enable constraint ticket_from_station_fk;
alter table orders
	enable constraint orders_ticket_fk;
alter table orders
	enable constraint orders_user_fk;

-- all_user Sample data
insert into all_user
	values(1, 'user1', 'test@gmail.com', '2014449999',
			'Flish', 'Doe', 'mercury', 
			'08540', '5 Independence Way','Princeton' ,'NJ', 1);
insert into all_user
	values(2, 'user2', 'admin@gmail.com', '1043332222',
			'John', 'Smith', 'mercury', 
			'07030', '245 5th Street', 'Hoboken', 'NJ', 1);
insert into all_user
	values(3, 'admin', 'admin@aol.com', '2220004444',
			'Dell', 'Howard', 'mercury', 
			'08540', '5 Independence Way','Princeton' ,'NJ', 1);
			

-- ueser roles Sample data
insert into user_roles
	values(100, 'ROLE_USER', 1) ;

insert into user_roles
	values(200, 'ROLE_USER', 2) ;

insert into user_roles
	values(300, 'ROLE_ADMIN', 3) ;

-- card Sample data
insert into card
	values('3714496353984312', 1, '5', '2015', 'VISA');
insert into card
	values('4111111111111111', 2, '6', '2020', 'MASTER');
insert into card
	values('5105105105105100', 1, '12', '2017', 'MASTER');
insert into card
	values('378282246310005', 2, '8', '2040', 'AMERICAN EXPRESS');

-- station sample data
insert into station
	values(0, 'Princeton Junction', 'NJ', 'Princeton');
insert into station
	values(1, 'Hoboken Terminal', 'NJ', 'Hoboken');
insert into station
	values(2, 'Penn Station', 'NY', 'New York');
insert into station
	values(3, 'New Brunswick', 'NJ', 'New Brunswick');
insert into station
	values(4, 'Hudson', 'NY', 'Hudson');
insert into station
	values(5, 'Buffalo–Depew', 'NY', 'Buffalo');

-- ticket sample data
insert into ticket
	values(0, 0, 5, 50, 70, 
		(select current_timestamp from dual), null);
insert into ticket
	values(1, 0, 1, 100, 10, null , null);
insert into ticket
	values(2, 0, 2, 50, 25, null , null);
insert into ticket
	values(3, 0, 3, 20, 7.5, null , null);
insert into ticket
	values(4, 2, 4, 20, 20, null , null);
insert into ticket
	values(5, 2, 5, 40, 55, null , null);
insert into ticket
	values(6, 3, 4, 20, 40, null , null);

-- orders sample data
insert into orders
	values (0, 1, 4, 'ADAO42', 2,
		(select current_timestamp from dual));
insert into orders
	values (1, 2, 2, 'IHsB3D', 1, null);
insert into orders 
	values (2, 1, 0, '84NBY3', 1, null);
insert into orders 
	values (3, 2, 3, '0JB84S', 3, null);
insert into orders 
	values (4, 1, 6, 'MX9SS3', 2, null);
insert into orders 
	values (5, 2, 2, '9HVOQD', 1, null);

commit;

-- Display all results properly.
set numwidth 5;

column username format a10;
column email format a10;
column first_name heading FNAME;
column last_name heading LNAME;
column first_name format a8;
column last_name format a8;
column address_id heading ADDRESS;
column password format a10;
select * from all_user;

set numwidth 5;
select * from card;

select * from station;

column start_time format a15;
column arrive_time format a15;
select * from ticket;

column order_time format a30;
select * from orders;

-- Clear certain column format
column column_name clear;

-- Clear all columns
clear columns;

commit ;
