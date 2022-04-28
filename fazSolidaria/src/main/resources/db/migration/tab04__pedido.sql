create table pedido(
	id bigint auto_increment,
	subTotal decimal(5,2)not null, 
	precoTotal decimal(5,2)not null,
	dataPedido timestamp null default current_timestamp,
	cliente_id bigint not null,
	
	primary key (id)
)engine=InnoDB default charset=utf8;
