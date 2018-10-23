create schema projetoProgramacao;

use projetoProgramacao;

create table TipoPagamento(
	codigo bigint(20) primary key auto_increment not null,
    descricao varchar(45) not null,
    desconto decimal(12,2)
);


create table TipoAluguel(
	codigo bigint(20) primary key auto_increment not null,
    descricao varchar(45) not null,
    valor decimal(12,2) not null,
	taxa decimal(12,2) not null
);

create table Cliente(
	codigo bigint(20) primary key auto_increment not null,
    nome varchar(100) not null,
    sobrenome varchar(100) not null,
    dataNascimento datetime not null,
    telefone varchar(11),
    cpf varchar(14) not null,
    email varchar(150) not null,
    dataCadastro datetime not null default now(),
    cnh varchar(11) not null    
);

create table Filial(
	codigo bigint(20) primary key auto_increment not null,
    nome varchar(100) not null,
    cidade varchar(100) not null,
    uf varchar(100)
);


create table Carro(
	codigo bigint(20) primary key auto_increment not null,
    marca varchar(45) not null,
    modelo varchar(45) not null,
    valor decimal(12,2) not null,
    cor varchar(45) not null,
    ano datetime not null,
    placa varchar(8) not null,
    disponivel boolean,
	dataAquisicao datetime not null,
    dataDesapropriacao datetime default null,
    codFilial bigint(20) not null
    

);

select * from Carro;

create table AquisicaoVeiculo(
	codigo bigint(20) primary key auto_increment not null,
    descricao varchar(100),
    dataModificacao datetime,
    codCarro bigint(20) not null,
    codFilial bigint(20) not null,
    
    constraint fk_AquisicaoVeiculo_Carro foreign key(codCarro) references Carro(codigo),
    constraint fk_AquisicaoVeiculo_Filial foreign key(codFilial) references Filial(codigo)
);

create table Funcionario(
	codigo bigint(20) primary key auto_increment not null,
    nome varchar(100) not null,
    sobrenome varchar(100) not null,
    dataNascimento datetime not null,
    telefone varchar(11),
    cpf varchar(14) not null,
    email varchar(150) not null,
    senha varchar(100) not null,
    salario decimal(12,2),
    dataAdmissao datetime default null,
    dataDemissao datetime null default null,
    codFilial bigint(20) not null
);


select * from Funcionario;

update Funcionario set dataDemissao = now() where codigo = 1;

select now();

create table ControleFuncionarios(
	codigo bigint(20) primary key auto_increment not null,
    descricao varchar(100),
	dataModificacao datetime,    
	codFilial bigint(20) not null,
    codFuncionario bigint(20) not null,
    
    constraint fk_ControleFuncionarios_Funcionario foreign key(codFuncionario) references Funcionario(codigo),
    constraint fk_ControleFuncionarios_Filial foreign key(codFilial) references Filial(codigo)
);

create table Aluguel(
	codigo bigint(20) primary key auto_increment not null,
    dataAluguel datetime not null default now(),
    quilometroSaida decimal(12,2) not null,
    codTipoAluguel bigint(20) not null,
    codCliente bigint(20) not null,
    codCarro bigint(20) not null,
    codFuncionario bigint(20) not null,
    codFilial bigint(20) not null,
    
    constraint fk_Aluguel_TipoAluguel foreign key(codTipoAluguel) references TipoAluguel(codigo),
    constraint fk_Aluguel_Cliente foreign key(codCliente) references Cliente(codigo),
    constraint fk_Aluguel_Carro foreign key(codCarro) references Carro(codigo),
    constraint fk_Aluguel_Funcionario foreign key(codFuncionario) references Funcionario(codigo),
    constraint fk_Aluguel_Filial foreign key(codFilial) references Filial(codigo)
    
);

select * from aluguel;

create table Devolucao(
	codigo bigint(20) primary key auto_increment not null,
    dataChegada datetime not null default now(),
    quilometragemChegada decimal(12,2) not null,
    codAluguel bigint(20) not null,
    codTipoPagamento bigint(20) not null,
    valorTotal decimal(12,2) not null,
    
	constraint fk_Devolucao_Aluguel foreign key(codAluguel) references Aluguel(codigo),
	constraint fk_Devolucao_TipoPagamento foreign key(codTipoPagamento) references TipoPagamento(codigo)
    
);


update Funcionario set nome = "nome", sobrenome= "sobrenome", dataNascimento= "1990/08/08", telefone="3444333", cpf="1234567", email="@", senha="123", 
salario = "2000", codFilial ="1" where codigo = "";


drop table TipoPagamento;
drop table Aluguel;
drop table TipoAluguel;
drop table Cliente;
drop table Devolucao;

drop table Funcionario;
drop table Carro;
drop table Filial;
drop table ControleFuncionarios;
drop table AquisicaoVeiculo;


select * from Funcionario f join ControleFuncionarios cf on f.codigo = cf.codFuncionario
where cf.dataDemissao = null;

select * from Carro c join AquisicaoVeiculo a on c.codigo = a.codCarro where a.dataDesapropriacao = null;

update ControleFuncionarios set dataDemissao = now() where codFuncionario = ?;

-- view com alugueis em andamento
create or replace view alugueis_em_andamento as select a.codigo as codigo, car.placa as placa, a.dataAluguel as dataAluguel, t.descricao as descricao, 
c.nome as nome, a.quilometroSaida as  quilometroSaida
from Aluguel a join Devolucao d on d.codAluguel = a.codigo
						join Cliente c on c.codigo = a.codCliente
                        join Carro car on car.codigo = a.codCarro
                        join TipoAluguel t on t.codigo = a.codTipoAluguel
where d.dataChegada = null;



-- viewnome de fucionarios ativos
create or replace view funcionarios_ativos as select f.nome as nome, f.sobrenome as sobrenome, f.dataNascimento as dt_nasc, f.telefone 
as tel, f.email as email, c.dataAdmissao as data_contratacao
from Funcionario f join ControleFuncionarios c on f.codigo = c.codFuncionario
where dataDemissao = null;

-- FALTA:

-- CRIAR VIEW PARA MOSTRAR DADOS DE CONTROLE FUNCIONARIO E AQUISICAO
create or replace view controle_de_funcionarios as 
select f.nome as nome, f.sobrenome as sobrenome, f.cpf as cpf, f.dataAdmissao as data_admissao,
fil.nome as nome_filial, fil.uf as uf, c.dataModificacao as ultima_modificacao 
from Funcionario f join ControleFuncionarios c on f.codigo = c.codFuncionario 
							join Filial fil on c.codFilial = fil.codigo 
group by f.codigo, nome_filial, uf, ultima_modificacao;

select nome, sobrenome, cpf, data_admissao,nome_filial,uf, ultima_modificacao from controle_de_funcionarios;



create or replace view aquisicao_veiculo as select c.marca as marca, c.modelo as modelo, c.placa as placa, c.dataAquisicao as data_aquisicao, 
fil.nome as nome_filial, fil.uf as uf, a.dataModificacao as ultima_modificacao 
from Carro c join AquisicaoVeiculo a on c.codigo = a.codCarro
					join Filial fil on a.codFilial = fil.codigo 
group by a.codigo
order by uf, nome_filial;

select marca, modelo, placa, data_aquisicao, nome_filial, uf, ultima_modificacao from aquisicao_veiculo;
                                        

-- trigger para controle e aquisicao
delimiter $
create trigger trg_insere_controle_funcionario after insert on Funcionarios for each row
begin

    declare _codFilial bigint(20);
    declare _codFuncionario bigint(20);
    
    set _codFuncionario = NEW.codigo;
    set _codFilial = NEW.codFilial;
    
    insert into ControleFuncionarios(descricao, dataModificacao, codFuncionario, codFilial) values ("Insere",now(), _codFuncionario, _codFilial);
	
end$
delimiter ;

delimiter $
create trigger trg_insere_controle_funcionario after update on Funcionarios for each row
begin

    declare _codFilial bigint(20);
    declare _codFuncionario bigint(20);
    
    set _codFuncionario = NEW.codigo;
    set _codFilial = NEW.codFilial;
    
    insert into ControleFuncionarios(descricao, dataModificacao, codFuncionario, codFilial) values ("Atualiza",now(), _codFuncionario, _codFilial);
	
end$
delimiter ;

delimiter $
create trigger trg_insere_controle_funcionario after delete on Funcionarios for each row
begin

    declare _codFilial bigint(20);
    declare _codFuncionario bigint(20);
    
    set _codFuncionario = OLD.codigo;
    set _codFilial = OLD.codFilial;
    
    insert into ControleFuncionarios(descricao, dataModificacao, codFuncionario, codFilial) values ("Deleta",now(), _codFuncionario, _codFilial);
	
end$
delimiter ;

delimiter $
create trigger trg_insere_aquisicao_carro after insert on Carro for each row
begin

    declare _codFilial bigint(20);
    declare _codCarro bigint(20);
    
    set _codCarro = NEW.codigo;
    set _codFilial = NEW.codFilial;
    
    insert into AquisicaoVeiculo(descricao, dataModificacao, codCarro, codFilial) values ("Insere",now(), _codCarro, _codFilial);
	
end$
delimiter ;

delimiter $
create trigger trg_insere_aquisicao_carro after update on Carro for each row
begin

    declare _codFilial bigint(20);
    declare _codCarro bigint(20);
    
    set _codCarro = NEW.codigo;
    set _codFilial = NEW.codFilial;
    
    insert into AquisicaoVeiculo(descricao, dataModificacao, codCarro, codFilial) values ("atualiza",now(), _codCarro, _codFilial);
	
end$
delimiter ;

delimiter $
create trigger trg_insere_aquisicao_carro after delete on Carro for each row
begin

    declare _codFilial bigint(20);
    declare _codCarro bigint(20);
    
    set _codCarro = OLD.codigo;
    set _codFilial = OLD.codFilial;
    
    insert into AquisicaoVeiculo(descricao, dataModificacao, codCarro, codFilial) values ("Deleta", now(), _codCarro, _codFilial);
	
end$
delimiter ;

-- function
drop function if exists carro_mais_alugado;

delimiter $
create function carro_mais_barato()
returns bigint(20)
begin
	declare _codCarro bigint(20);
    declare contador decimal(12,2);
	
    select min(valor) into contador from carro;
    select codigo into _codCarro from carro where contador = valor;
    
    return _codCarro;
end$
delimiter ;

select * from funcionario;

select carro_mais_alugado();

select * from carro;
-- Procedure

drop procedure reajusta_taxa;

delimiter $
create procedure reajusta_taxa(IN reajuste decimal(12,2))
begin
	
    declare acabou integer default 0;
    
    declare _idTipoAluguel bigint(20);
    declare _descricao varchar(45);
    declare _valor decimal(12,2);
    declare _taxa decimal(12,2);
    
    declare _novaTaxa decimal(12,2);
    
    declare listatipo cursor for(
		select codigo, descricao, valor, taxa from TipoAluguel
    );
    
    declare continue handler for not found set acabou = 1;
    
    open listatipo;
    
    repeat
		fetch listatipo into _idTipoAluguel, _descricao, _valor, _taxa;
        
        if not acabou then
        
			select coalesce(sum((taxa * reajuste)/100), 0) into _novaTaxa from TipoAluguel where codigo = _idTipoAluguel;
            
			update TipoAluguel set taxa = _taxa + _novaTaxa where codigo = _idTipoAluguel;
            
        end if;
    until acabou end repeat;
    
    close listatipo;
	
end$
delimiter ;

call reajusta_taxa();

