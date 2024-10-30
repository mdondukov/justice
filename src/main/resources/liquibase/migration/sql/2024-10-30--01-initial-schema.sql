begin;

create table if not exists just.event
(
    id           bigserial                   not null,
    slug         character varying(100)      not null,
    title        character varying(200)      not null,
    descr        text                        not null,
    content      text                        not null,
    thumb        character varying(100)      not null,
    agenda       jsonb,
    press        jsonb,
    pictures     jsonb,
    youtube_url  character varying(100),
    create_date  timestamp without time zone not null default now(),
    publish_date timestamp without time zone not null default now(),
    active       boolean                     not null default false,
    constraint events_pkey primary key (id),
    constraint events_uniq unique (slug)
);

create index if not exists idx_event_active on just.event (active);

create table if not exists just.event_lang
(
    id          bigserial not null,
    event_id    bigint    not null,
    title       hstore,
    descr       hstore,
    content     hstore,
    youtube_url hstore,
    constraint event_lang_pkey primary key (id),
    constraint event_lang_uniq unique (event_id),
    constraint event_lang_event_id_fkey foreign key (event_id)
        references just.event (id) match simple
        on update cascade
        on delete cascade
);

create index if not exists idx_event_lang_event_id on just.event_lang (event_id);

create or replace function just.event(_lang character varying default 'ru'::character varying)
    returns table
            (
                id           bigint,
                slug         character varying(100),
                title        character varying(200),
                descr        text,
                content      text,
                thumb        character varying(100),
                agenda       jsonb,
                press        jsonb,
                pictures     jsonb,
                youtube_url  character varying(100),
                create_date  timestamp without time zone,
                publish_date timestamp without time zone,
                active       boolean
            )
    language 'sql'
    cost 100
    volatile parallel unsafe
    rows 1000

as
$body$
select e.id,
       e.slug,
       coalesce(el.title -> _lang, e.title)     as title,
       coalesce(el.descr -> _lang, e.descr)     as descr,
       coalesce(el.content -> _lang, e.content) as content,
       e.thumb,
       e.agenda,
       e.press,
       e.pictures,
       coalesce(el.youtube_url -> _lang, e.youtube_url) as content,
       e.create_date,
       e.publish_date,
       e.active
from just.event e
         left join just.event_lang el on e.id = el.event_id
$body$;

commit;