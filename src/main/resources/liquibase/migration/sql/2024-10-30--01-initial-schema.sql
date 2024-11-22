begin;

-- Activities

create table if not exists just.activity
(
    id   bigserial              not null,
    slug character varying(100) not null,
    ord  integer                not null default 0,
    constraint activity_pkey primary key (id),
    constraint activity_uniq unique (slug)
);

create table if not exists just.activity_lang
(
    id          bigserial              not null,
    activity_id bigint                 not null,
    lang        character varying(2)   not null default 'ru',
    name        character varying      not null,
    short_name  character varying(100) not null,
    descr       text                   not null,
    constraint activity_lang_pk primary key (id),
    constraint activity_lang_uniq unique (activity_id, lang),
    constraint activity_lang_activity_id_fk foreign key (activity_id)
        references just.activity (id) on delete cascade
);

create index if not exists idx_activity_lang_activity_id on just.activity_lang (activity_id);

create or replace view just.activity_view as
select d.id as activity_id,
       d.slug,
       dl.lang,
       dl.name,
       dl.short_name,
       dl.descr,
       d.ord
from just.activity d
         join
     just.activity_lang dl on d.id = dl.activity_id;


-- Documents

create table if not exists just.document
(
    id          bigserial                   not null,
    dtype       character varying(50)       not null,
    files       jsonb,
    create_date timestamp without time zone not null default now(),
    active      boolean                     not null default true,
    ord         integer                     not null default 0,
    constraint document_pkey primary key (id)
);

create table if not exists just.document_lang
(
    id          bigserial            not null,
    document_id bigint               not null,
    lang        character varying(2) not null default 'ru',
    title       character varying    not null,
    descr       text                 not null,
    constraint document_lang_pk primary key (id),
    constraint document_lang_uniq unique (document_id, lang),
    constraint document_lang_document_id_fk foreign key (document_id)
        references just.document (id) on delete cascade
);

create index if not exists idx_document_lang_document_id on just.document_lang (document_id);

create or replace view just.document_view as
select d.id as document_id,
       dl.lang,
       dl.title,
       dl.descr,
       d.dtype,
       d.files,
       d.create_date,
       d.active,
       d.ord
from just.document d
         join just.document_lang dl on d.id = dl.document_id;

create table if not exists just.document_activity
(
    document_id bigint not null,
    activity_id bigint not null,
    constraint document_activity_pkey primary key (document_id, activity_id),
    constraint document_activity_document_id_fk foreign key (document_id) references just.document (id) on delete cascade,
    constraint document_activity_activity_id_fk foreign key (activity_id) references just.activity (id) on delete cascade
);


-- Events

create table if not exists just.event
(
    id           bigserial                   not null,
    slug         character varying(100)      not null,
    thumb        character varying(100)      not null,
    agenda       jsonb,
    press        jsonb,
    pictures     jsonb,
    create_date  timestamp without time zone not null default now(),
    publish_date timestamp without time zone not null default now(),
    active       boolean                     not null default true,
    constraint events_pkey primary key (id),
    constraint events_uniq unique (slug)
);

create table if not exists just.event_lang
(
    id          bigserial            not null,
    event_id    bigint               not null,
    lang        character varying(2) not null default 'ru',
    title       character varying    not null,
    descr       text                 not null,
    content     text                 not null,
    youtube_url character varying(100),
    constraint event_lang_pk primary key (id),
    constraint event_lang_uniq unique (event_id, lang),
    constraint event_lang_event_id_fk foreign key (event_id)
        references just.event (id) on delete cascade
);

create index if not exists idx_event_lang_event_id on just.event_lang (event_id);

create or replace view just.event_view as
select e.id as event_id,
       e.slug,
       el.lang,
       el.title,
       el.descr,
       el.content,
       e.thumb,
       e.agenda,
       e.press,
       e.pictures,
       el.youtube_url,
       e.create_date,
       e.publish_date,
       e.active
from just.event e
         join just.event_lang el on e.id = el.event_id;

-- Posters

create table if not exists just.poster
(
    id          bigserial                   not null,
    slug        character varying(100)      not null,
    thumb       character varying(100)      not null,
    files       jsonb,
    create_date timestamp without time zone not null default now(),
    active      boolean                     not null default true,
    ord         integer                     not null default 0,
    constraint poster_pkey primary key (id),
    constraint poster_uniq unique (slug)
);

create table if not exists just.poster_lang
(
    id        bigserial            not null,
    poster_id bigint               not null,
    lang      character varying(2) not null default 'ru',
    title     character varying    not null,
    descr     text                 not null,
    constraint poster_lang_pk primary key (id),
    constraint poster_lang_uniq unique (poster_id, lang),
    constraint poster_lang_poster_id_fk foreign key (poster_id)
        references just.poster (id) on delete cascade
);

create index if not exists idx_poster_lang_poster_id on just.poster_lang (poster_id);

create or replace view just.poster_view as
select p.id as poster_id,
       p.slug,
       pl.lang,
       pl.title,
       pl.descr,
       p.thumb,
       p.files,
       p.create_date,
       p.active,
       p.ord
from just.poster p
         join just.poster_lang pl on p.id = pl.poster_id;

create table if not exists just.poster_activity
(
    poster_id   bigint not null,
    activity_id bigint not null,
    constraint poster_activity_pkey primary key (poster_id, activity_id),
    constraint poster_activity_poster_id_fk foreign key (poster_id) references just.poster (id) on delete cascade,
    constraint poster_activity_activity_id_fk foreign key (activity_id) references just.activity (id) on delete cascade
);


-- Speeches

create table if not exists just.speech
(
    id           bigserial                   not null,
    slug         character varying(100)      not null,
    thumb        character varying(100)      not null,
    presentation jsonb,
    youtube_url  character varying(100),
    create_date  timestamp without time zone not null default now(),
    publish_date timestamp without time zone not null default now(),
    active       boolean                     not null default true,
    constraint speech_pkey primary key (id),
    constraint speech_uniq unique (slug)
);

create table if not exists just.speech_lang
(
    id        bigserial              not null,
    speech_id bigint                 not null,
    lang      character varying(2)   not null default 'ru',
    title     character varying      not null,
    descr     text                   not null,
    speaker   character varying(200) not null,
    constraint speech_lang_pk primary key (id),
    constraint speech_lang_uniq unique (speech_id, lang),
    constraint speech_lang_speech_id_fk foreign key (speech_id)
        references just.speech (id) on delete cascade
);

create index if not exists idx_speech_lang_speech_id on just.speech_lang (speech_id);

create or replace view just.speech_view as
select s.id as speech_id,
       s.slug,
       sl.lang,
       sl.title,
       sl.descr,
       sl.speaker,
       s.thumb,
       s.presentation,
       s.youtube_url,
       s.create_date,
       s.publish_date,
       s.active
from just.speech s
         join just.speech_lang sl on s.id = sl.speech_id;

create table if not exists just.speech_activity
(
    speech_id   bigint not null,
    activity_id bigint not null,
    constraint speech_activity_pkey primary key (speech_id, activity_id),
    constraint speech_activity_speech_id_fk foreign key (speech_id) references just.speech (id) on delete cascade,
    constraint speech_activity_activity_id_fk foreign key (activity_id) references just.activity (id) on delete cascade
);

commit;