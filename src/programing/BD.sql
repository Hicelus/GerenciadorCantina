create database Mercadorias;
use Mercadorias;
show tables in Mercadorias;
create table Produtos(
	produto_id int(11) primary key not null auto_increment,
	produto_nome varchar (15) not null,
	produto_descricao varchar (45) not null,
	produto_preco double not null,
	produto_quantidade double not null);
describe Produtos;
create table Relatorio(
	relatorio_id int(11) primary key not null auto_increment,
    relatorio_dia int(2) not null,
    relatorio_mes int(2) not null,
    relatorio_ano int(4) not null,
    relatorio_hora varchar(8) not null,
    relatorio_descricao varchar(5000) not null);
describe Relatorio;
drop table Relatorio;
    
    
    
    