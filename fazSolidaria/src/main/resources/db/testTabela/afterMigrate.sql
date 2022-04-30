/*Inicio - Inserir valores tabela categoria*/
insert into categoria (tipo_alimento) values ('Frutas');
insert into categoria (tipo_alimento) values ('Verduras');
insert into categoria (tipo_alimento) values ('Legumes');
insert into categoria (tipo_alimento) values ('Grãos');
/*Fim - Inserir valores tabela categoria*/

/*Inicio - Inserir valores tabela endereco*/
insert into endereco (cep, logradouro, complemento, bairro, localidade, uf, numero, pais) values ('03414-010', 'logradouro', 'complemento', 'Vila Mafra', 'Localidade','SP', '666', 'Brasil');
/*Fim - Inserir valores tabela endereco*/

/*Inicio - Inserir valores tabela itemPedido*/
/*Fim - Inserir valores tabela itemPedido*/

/*Inicio - Inserir valores tabela pedido*/
/*Fim - Inserir valores tabela pedido*/

/*Inicio - Inserir valores tabela produto*/
insert into produto (nome, preco, estoque, imagem, categoria_id) values ('Maçã Gaia', 7.89, 100, 'link', 1);
insert into produto (nome, preco, estoque, imagem, categoria_id) values ('Banana Prata', 4.49, 100, 'link', 1);
insert into produto (nome, preco, estoque, imagem, categoria_id) values ('Banana Nanica', 5.79, 100, 'link', 1);
insert into produto (nome, preco, estoque, imagem, categoria_id) values ('Abacate', 16.90, 100, 'link', 1);
/*Fim - Inserir valores tabela produto*/

/*Inicio - Inserir valores tabela usuario*/
insert into usuario (cpf, nome, email, senha, data_nasc, telefone, foto, endereco_id) values('825.155.790-94', 'root', 'root@email.com', '123456789', '03/04/1995', '(11)99280-1817', 'link', 1);
/*Fim - Inserir valores tabela usuario*/



