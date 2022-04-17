-- SCRIPT PARA CRIACAO DAS TABELAS NO POSTGRESQL

create table campeonato.time
(
    id         serial4      not null,
    nome       varchar(100) not null,
    localidade varchar(2)   not null,
    constraint time_pkey primary key (id)
);

create table campeonato.jogador
(
    id              serial4      not null,
    nome            varchar(100) not null,
    data_nascimento date         not null,
    id_time         int4         not null,
    nacionalidade   varchar(25)  not null,
    constraint jogador_pkey primary key (id),
    constraint time_fkey foreign key (id_time) references time (id)
);

create table campeonato.torneio
(
    id          serial4 not null,
    data_inicio date    not null,
    data_fim    date    not null,
    id_campeao  int4 null,
    id_craque   int4 null,
    constraint torneio_pkey primary key (id),
    constraint craque_fkey foreign key (id_craque) references jogador (id),
    constraint campeao_fkey foreign key (id_campeao) references time (id)
);

create table campeonato.partida
(
    id                serial4   not null,
    data_realizacao   timestamp not null,
    id_time_mandante  int4      not null,
    id_time_visitante int4      not null,
    id_torneio        int4      not null,
    constraint partida_pkey primary key (id),
    constraint mandante_fkey foreign key (id_time_mandante) references jogador (id),
    constraint visitante_fkey foreign key (id_time_visitante) references jogador (id),
    constraint torneio_fkey foreign key (id_torneio) references torneio (id)
);

create table campeonato.evento
(
    id                    serial4     not null,
    tipo_evento           varchar(25) not null,
    tipo_advertencia      varchar(25) null,
    data                  timestamp null,
    minuto                int null,
    minutos               int null,
    tempo                 int null,
    id_jogador            int4 null,
    id_jogador_substituto int4 null,
    id_partida            int4        not null,
    constraint evento_pkey primary key (id),
    constraint jogador_fkey foreign key (id_jogador) references jogador (id),
    constraint jogador_substituto_fkey foreign key (id_jogador_substituto) references jogador (id),
    constraint partida_fkey foreign key (id_partida) references partida (id)
);
