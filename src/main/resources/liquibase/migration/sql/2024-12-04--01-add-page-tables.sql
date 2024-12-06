begin;

-- Pages

create table if not exists just.page
(
    id   bigserial              not null,
    slug character varying(100) not null,
    constraint page_pkey primary key (id),
    constraint page_uniq unique (slug)
);

create table if not exists just.page_lang
(
    id      bigserial            not null,
    page_id bigint               not null,
    lang    character varying(2) not null default 'ru',
    title   character varying    not null,
    content jsonb                not null,
    constraint page_lang_pk primary key (id),
    constraint page_lang_uniq unique (page_id, lang),
    constraint page_lang_page_id_fk foreign key (page_id)
        references just.page (id) on delete cascade
);

create index if not exists idx_page_lang_page_id on just.page_lang (page_id);

create or replace view just.page_view as
select p.id as page_id,
       p.slug,
       pl.lang,
       pl.title,
       pl.content
from just.page p
         join
     just.page_lang pl on p.id = pl.page_id;

commit;