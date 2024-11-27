begin;

alter table just.speech_lang
    drop column speaker;

commit;