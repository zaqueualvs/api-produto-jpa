insert into produto ( nome, preco, data_criacao, descricao) values ( 'Kindle', 499.0, date_sub(sysdate(), interval 1 day), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into produto (id, nome, preco, data_criacao, descricao) values (3, 'Câmera GoPro Hero 7', 1400.0, date_sub(sysdate(), interval 1 day), 'Desempenho 2x melhor.');

insert into cliente ( nome) values ( 'Fernando Medeiros');
insert into cliente ( nome) values ( 'Marcos Mariano');

insert into pedido (id, cliente_id, data_criacao, status) values (1, 1, sysdate(), 'AGUARDANDO');
insert into pedido (id, cliente_id, data_criacao, status) values (2, 1, sysdate(), 'AGUARDANDO');

insert into item_pedido (pedido_id, produto_id, quantidade) values (1, 1, 2);
insert into item_pedido (pedido_id, produto_id, quantidade) values (2, 1, 1);

insert into pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras) values (2, 'PROCESSANDO', 'cartao', '123', null);

insert into categoria (id, nome) values (1, 'Eletrônicos');