begin;

alter table just.user
    add column attempts int     default 0    not null,
    add column enabled  boolean default true not null;

commit;