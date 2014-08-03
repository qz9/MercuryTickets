-- Drop all the tables:
drop table orders;
drop table ticket;
drop table station;
drop table card;
drop table all_user;
drop table statistics;
drop table ads;

-- Create table all_user
-- type: 'c' means 'customers', 'a' means 'administrators'
create table all_user (
	id number(10) primary key,
	username varchar2(20) not null unique,
	email varchar2(40) not null unique,
	phone varchar2(10),
	first_name varchar2(20),
	last_name varchar2(20),
	password varchar2(50) not null,
	zip_code char(5),
	street varchar2(20),
	city varchar2(20),
	state char(2),
	enabled number(1) not null,
	authority varchar2(10) not null;
);

-- Create table card
create table card (
	card_number char(16) primary key,
	user_id number(10),
	ex_month char(2),
	ex_year char(4),
	card_type varchar2(20),
	constraint card_user_fk
		foreign key (user_id) references all_user (id)
);

-- Create table station
create table station (
	id number(10) primary key,
	name varchar2(20) not null,
	state char(2),
	city varchar2(20)
);

-- Create table ticket
create table ticket (
	id number(10) primary key,
	from_id number(10) not null,
	to_id number(10) not null,
	max_num number(4),
	price number(4, 2),
	start_time timestamp,
	arrive_time timestamp,
	constraint ticket_from_station_fk
		foreign key (from_id) references station (id),
	constraint ticket_to_station_fk
		foreign key (to_id) references station (id),
	constraint check_from_to check
		(from_id > to_id or from_id < to_id)
);

-- Create table orders
create table orders (
	id number(10) primary key,
	user_id number(10) not null,
	ticket_id number(10) not null,
	order_code varchar2(10),
	ticket_num number(4),
	order_time timestamp,
	constraint orders_user_fk
		foreign key (user_id) references all_user (id),
	constraint orders_ticket_fk
		foreign key (ticket_id) references ticket (id)
);

create table ads (
id number(10) primary key,
 ad_url varchar2(100)
);



create table statistics (

	id number(10) primary key,
 
	username varchar2(20),
	
	count number(10),
	
	ads_id number(10),
	
	login_time timestamp,
	
	logout_time timestamp,
	
	constraint stat_ads_fk
	
		foreign key (ads_id) references ads(id)

);
