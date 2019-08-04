create table if not exists accounts
(
    id serial not null ,
    score_id varchar(20) NOT NULL,
	user_id bigint not null ,
	amount bigint,
	holded bigint,
	open date not null,
	close date

);

insert INTO  accounts values ('00001', '1111', '1000', '0' );

create table if not exists logs
(
    transactionId varchar(20) not null
        constraint logs_pk
            primary key,
    date timestamp,
    log_level text,
    message text,
    exception text
);

-- drop table accounts;

create table if not exists journal
    (
        date date not null ,
        score_id varchar(20) not null ,
        user_id    bigint not null ,
        description varchar(100),
        summa bigint,
        transactionId varchar(20)
);

