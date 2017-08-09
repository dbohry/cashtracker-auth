CREATE TABLE users (
	id bigint AUTO_INCREMENT,
	email varchar(255) not null,
	username varchar(255) not null,
	password varchar(255) not null,
	primary key (id)
);