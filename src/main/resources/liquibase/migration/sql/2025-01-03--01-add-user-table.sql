begin;

-- User
create table if not exists just.user
(
    id       bigserial              not null,
    username character varying(100) not null,
    password character varying      not null,
    role     character varying(50)  not null,
    constraint user_pkey primary key (id),
    constraint user_uniq unique (username)
);

commit;