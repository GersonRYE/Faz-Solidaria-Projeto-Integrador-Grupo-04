create table endereco(
	id bigint auto_increment,
	cep varchar(10) not null, 
	logradouro varchar(50) not null,
	complemento varchar(50) not null, 
	bairro varchar(50) not null, 
	localidade varchar(50) not null, 
	uf varchar(50)not null,
	numero varchar(50) not null, 
	pais varchar(50) not null,
	
	primary key(id)
)engine=InnoDB default charset=utf8;