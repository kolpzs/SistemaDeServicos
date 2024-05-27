create database mensalDB;

use mensalDB;

create table if not exists usuario(
	user_id bigint not null primary key auto_increment,
    username varchar(256) not null,
    senha varchar(256) not null
);

create table if not exists funcionario(
	func_id bigint not null primary key auto_increment,
    func_nome varchar(256) not null,
    func_cpf varchar(12) not null,
    func_telefone varchar(12) not null,
    func_ativo boolean default true,
    usuario_id_fk bigint not null,
    foreign key (usuario_id_fk) references usuario(user_id)
);

create table if not exists cliente(
	cliente_id bigint not null primary key auto_increment,
    cliente_nome varchar(256) not null,
    cliente_cpf varchar(12) not null,
    cliente_telefone varchar(12) not null
);

create table if not exists endereco(
	endereco_id bigint not null primary key auto_increment,
    rua varchar(151) not null,
    numeroCasa int not null,
    bairro varchar(101) not null,
    cep varchar(10) not null,
    cidade varchar(51) not null,
    cliente_id_fk bigint not null,
    foreign key (cliente_id_fk) references cliente(cliente_id)
);

create table if not exists servico (
	servico_id bigint not null primary key auto_increment,
    servico_dia DATE,
    servico_ativo boolean default true,
    servico_descricao varchar(241) not null,
    cliente_id_fk bigint not null,
    foreign key (cliente_id_fk) references cliente(cliente_id)
);

create table if not exists funcionario_servico(
	func_id_fk bigint not null,
    foreign key (func_id_fk) references funcionario(func_id),
    servico_id_fk bigint not null,
    foreign key (servico_id_fk) references servico(servico_id)
);