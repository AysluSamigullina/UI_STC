create table if not exists debit_card
(
	id bigserial not null,
	score_id bigint,
	description text
);

alter table debit_card owner to bank;

create table if not exists credit_card
(
	id bigserial not null,
	score_id bigint,
	description text
);

alter table credit_card owner to bank;

create table if not exists score
(
	id bigserial not null,
	amount integer
);

alter table score owner to bank;

create table if not exists logs
(
	id serial not null
		constraint logs_pk
			primary key,
	date timestamp,
	log_level text,
	message text,
	exception text
);

alter table logs owner to bank;

