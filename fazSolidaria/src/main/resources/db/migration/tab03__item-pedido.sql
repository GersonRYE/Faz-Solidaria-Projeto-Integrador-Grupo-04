create table itemPedido(
	id bigint auto_increment,
	pedido_id bigint not null,
	produto_id bigint not null, 
	precoUnitario decimal(5,2) not null, 
	precoTotal decimal(5,2) not null, 
	quantidade smallint not null, 
	
	primary key(id)
)engine=InnoDB default charset=utf8;