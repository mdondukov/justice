begin;

alter table just.user
    add column firstname character varying(100),
    add column lastname  character varying(100);

commit;