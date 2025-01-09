begin;

drop view just.event_view;

create or replace view just.event_view as
select e.id  as event_id,
       e.slug,
       el.id as event_lang_id,
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

commit;