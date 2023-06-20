create table topico (
                id bigint not null auto_increment,
                autor varchar(100) not null,
                data_criacao datetime not null,
                curso varchar(100) not null,
                titulo varchar(100) not null,
                mensagem varchar(100) not null,
                estado varchar(9) not null,

                primary key(id),
                unique(titulo, mensagem)
);