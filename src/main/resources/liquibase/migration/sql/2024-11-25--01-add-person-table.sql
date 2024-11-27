begin;

create table if not exists just.person
(
    id    bigserial              not null,
    slug  character varying(100) not null,
    photo character varying(100) not null,
    constraint person_pkey primary key (id),
    constraint person_uniq unique (slug)
);

create table if not exists just.person_lang
(
    id          bigserial            not null,
    person_id   bigint               not null,
    lang        character varying(2) not null default 'ru',
    first_name  character varying    not null,
    last_name   character varying    not null,
    middle_name character varying,
    org         character varying,
    descr       text,
    constraint person_lang_pk primary key (id),
    constraint person_lang_uniq unique (person_id, lang),
    constraint person_lang_speech_id_fk foreign key (person_id)
        references just.person (id) on delete cascade
);

create index if not exists idx_person_lang_person_id on just.person_lang (person_id);

create or replace view just.person_view as
select p.id as person_id,
       p.slug,
       pl.lang,
       pl.first_name,
       pl.last_name,
       pl.middle_name,
       pl.org,
       pl.descr,
       p.photo
from just.person p
         join just.person_lang pl on p.id = pl.person_id;

-- Добавить колонку person_id в таблицу just.speech
alter table just.speech
    add column person_id bigint;

-- Добавить внешний ключ для связи с таблицей just.person
alter table just.speech
    add constraint speech_person_fk foreign key (person_id)
        references just.person (id) on delete set null;

-- Создать индекс для ускорения запросов
create index if not exists idx_speech_person_id on just.speech (person_id);

drop view just.speech_view;

create or replace view just.speech_view as
select s.id as speech_id,
       s.slug,
       sl.lang,
       sl.title,
       sl.descr,
       s.person_id,
       s.thumb,
       s.presentation,
       s.youtube_url,
       s.create_date,
       s.publish_date,
       s.active
from just.speech s
         left join just.person p on s.person_id = p.id
         join just.speech_lang sl on s.id = sl.speech_id;

commit;