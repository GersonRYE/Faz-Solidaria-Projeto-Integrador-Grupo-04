create table produto(
	id bigint auto_increment,
	nome varchar(50) not null, 
	preco decimal(5,2) not null,
	estoque smallint not null,
	imagem varchar(255),
	categoria_id bigint not null,

	primary key(id)
)engine=InnoDB default charset=utf8;