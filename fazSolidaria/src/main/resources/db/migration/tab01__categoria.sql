create table categoria(

	id bigint auto_increment,
	tipoAlimento varchar(10) not null, 
	
	primary key (id)
)engine=InnoDB default charset=utf8;