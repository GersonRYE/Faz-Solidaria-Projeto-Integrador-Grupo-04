create table usuario (
	id bigint auto_increment,
	cpf varchar(50) not null, 
	nome varchar(100) not null, 
	email varchar(100) not null, 
	senha varchar(15) not null, 
	dataNasc varchar(10) not null,
	telefone varchar(10) not null,
	foto varchar(255) not null,
	endereco_id bigint not null,
	
	primary key(id)
)engine=InnoDB default charset=utf8;