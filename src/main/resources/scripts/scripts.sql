create schema if not EXISTS Banco_Vacina;
use Banco_Vacina;

create table Pessoa(
	idPessoa int not null auto_increment,
    nome varchar(255) not null,
    dataNascimento date not null,
    sexo char not null,
    categoria varchar(50) not null,
	primary key(idPessoa)
);

create table vacina (
id_vacina INTEGER auto_increment NOT NULL,
pais varchar(255) not null,
estagio_pesquisa int not null,
data_inicio date null,
responsavel varchar(255),
CONSTRAINT Vacina_pk PRIMARY KEY (id_vacina)
);

create table aplicacao (
id_aplicacao INTEGER auto_increment NOT NULL,
avaliacao int not null,
data_aplicacao date not null,
CONSTRAINT Aplicacao_pk PRIMARY KEY (id_aplicacao),
CONSTRAINT fk_vacina FOREIGN KEY (id_vacina) REFERENCES vacina(id_vacina),
CONSTRAINT fk_pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);

-- ####################### RASCUNHO ###################### --

alter table Pessoa add column cpf varchar(11) unique not null;

UPDATE Pessoa
SET cpf = (FLOOR(RAND() * 899999999) + 100000000)
WHERE cpf = '';

drop table Pessoa;

create table Pessoa(
	idPessoa int not null auto_increment,
    nome varchar(255) not null,
    cpf varchar(11) unique not null,
    dataNascimento date not null,
    sexo varchar(1) not null,
    categoria varchar(50) not null,
	primary key(idPessoa)
);

drop table Pessoa;

create table Pessoa(
	idPessoa int not null auto_increment,
    nome varchar(255) not null,
    cpf varchar(11) unique not null,
    dataNascimento date not null,
    sexo varchar(1) not null,
    categoria varchar(50) not null,
	primary key(idPessoa)
);

drop table Pessoa;

select * from pessoa;

create table vacina (
id_vacina integer not null auto_increment
, nome varchar(255) not null
, pais_origem varchar(255)
, id_pesquisador integer not null
, estagio integer not null
, data_inicio_pesquisa date not null
, primary key(id_vacina) 
, constraint id_pesquisador foreign key(id_pesquisador) references pessoa(idPessoa)
);

create table aplicacao_vacina (
id_aplicacao_vacina int not null auto_increment
, id_pessoa int not null
, id_vacina int not null
, data date not null
, avaliacao integer not null
, primary key(id_aplicacao_vacina)
, foreign key(id_pessoa) references pessoa
, foreign key(id_vacina) references vacina
);
