create table vehicle(
	id serial,
	brand varchar(40) not null,
	model varchar(50) not null,
	year integer not null,
	price float not null,
	description varchar(500),
	isnew boolean not null,
	regiter date not null,
	update date 
);
