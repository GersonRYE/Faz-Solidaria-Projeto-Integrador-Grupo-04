create table item_pedido(
	id bigint auto_increment,
	pedido_id bigint not null,
	produto_id bigint not null, 
	preco_unitario decimal(5,2) not null, 
	preco_total decimal(5,2) not null, 
	quantidade smallint not null, 
	
	primary key(id)
)engine=InnoDB default charset=utf8;