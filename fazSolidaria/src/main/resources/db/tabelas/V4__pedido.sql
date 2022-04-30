create table pedido(
	id bigint auto_increment,
	sub_total decimal(5,2)not null, 
	preco_total decimal(5,2)not null,
	data_pedido timestamp null default current_timestamp,
	cliente_id bigint not null,
	
	primary key (id)
)engine=InnoDB default charset=utf8;
