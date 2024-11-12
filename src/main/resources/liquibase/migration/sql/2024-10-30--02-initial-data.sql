begin;

-- Activities

INSERT INTO just.activity (slug, ord)
VALUES ('ecology', 1),
       ('gender', 2),
       ('privacy', 3);

INSERT INTO just.activity_lang (activity_id, lang, name, short_name, descr)
VALUES ((SELECT id FROM just.activity WHERE slug = 'ecology'), 'ru', 'Право на безопасную окружающую среду',
        'Экологическое право', '');

INSERT INTO just.activity_lang (activity_id, lang, name, short_name, descr)
VALUES ((SELECT id FROM just.activity WHERE slug = 'gender'), 'ru', 'Недискриминация и гендерное право',
        'Гендерное право', '');

INSERT INTO just.activity_lang (activity_id, lang, name, short_name, descr)
VALUES ((SELECT id FROM just.activity WHERE slug = 'privacy'), 'ru', 'Право на защиту персональных данных',
        'Персональные данные', '');


-- Events

INSERT INTO just.event (slug, thumb, publish_date, active)
VALUES ('center-opening',
        '/tmb/30c677b6-78dd-4522-9121-6218b55cf85c.jpg',
        '2024-07-25 00:00:00', true);

INSERT INTO just.event_lang (event_id, lang, title, descr, content)
VALUES ((SELECT id FROM just.event WHERE slug = 'center-opening'), 'ru',
        'Открытие Центра социально-экологической поддержки и юридического консультирования граждан',
        '', '');

INSERT INTO just.event (slug, thumb, publish_date, active)
VALUES ('spec-expertize',
        '/tmb/7e3bb7d0-5a48-48c8-90c8-fb31caf0c700.jpg',
        '2024-08-09 00:00:00', true);

INSERT INTO just.event_lang (event_id, lang, title, descr, content)
VALUES ((SELECT id FROM just.event WHERE slug = 'spec-expertize'), 'ru',
        'Специализированные виды экспертиз проектов нормативных правовых актов',
        '', '');

insert into just.event (slug, thumb, publish_date, active)
values ('law-creation-foundation',
        '/tmb/995f8cf3-a094-48c1-b617-41a8c7d1884f.jpg',
        '2024-06-06 00:00:00', true);

INSERT INTO just.event_lang (event_id, lang, title, descr, content)
VALUES ((SELECT id FROM just.event WHERE slug = 'law-creation-foundation'), 'ru',
        'Обучающий семинар "Основы нормотворческой деятельности"',
        '', '');


-- Documents

DO
$$
    DECLARE
        doc_id BIGINT;
    BEGIN
        -- Документ 1
        INSERT INTO just.document (dtype, files, active, ord)
        VALUES ('MANUAL', '[
          {
            "type": "PDF",
            "path": "/files/pub_monitoring_manual_01.pdf",
            "lang": "ru"
          }
        ]', true, 1)
        RETURNING id INTO doc_id;

        INSERT INTO just.document_lang (document_id, lang, title, descr)
        VALUES (doc_id, 'ru', 'Руководство по общественному мониторингу',
                'Публикация “Руководство по общественному мониторингу Целей Устойчивого Развития и социально-экологической поддержке населения” посвящена описанию процесса проведения мониторинга с участием граждан в сборе данных, а также механизмов взаимодействия по проведению мониторинга между гражданами, органами государственной власти и местного самоуправления.');

        -- Документ 2
        INSERT INTO just.document (dtype, files, active, ord)
        VALUES ('ANALYTICS', '[
          {
            "type": "PDF",
            "path": "/files/analytic_report_privacy.pdf",
            "lang": "ru"
          }
        ]', true, 1)
        RETURNING id INTO doc_id;

        INSERT INTO just.document_lang (document_id, lang, title, descr)
        VALUES (doc_id, 'ru',
                'Аналитический отчет о механизмах защиты персональных данных и неприкосновенности частной жизни в КР',
                '');

        INSERT INTO just.document_activity (document_id, activity_id)
        VALUES (doc_id, (SELECT id FROM just.activity WHERE slug = 'privacy'));

        -- Документ 3
        INSERT INTO just.document (dtype, files, active, ord)
        VALUES ('ANALYTICS', '[
          {
            "type": "PDF",
            "path": "/files/analytic_report_eco.pdf",
            "lang": "ru"
          }
        ]', true, 2)
        RETURNING id INTO doc_id;

        INSERT INTO just.document_lang (document_id, lang, title, descr)
        VALUES (doc_id, 'ru', 'Аналитический отчет о механизмах обеспечения экологических прав в КР', '');

        INSERT INTO just.document_activity (document_id, activity_id)
        VALUES (doc_id, (SELECT id FROM just.activity WHERE slug = 'ecology'));

        -- Документ 4
        INSERT INTO just.document (dtype, files, active, ord)
        VALUES ('ANALYTICS', '[
          {
            "type": "PDF",
            "path": "/files/analytic_report_gender.pdf",
            "lang": "ru"
          }
        ]', true, 3)
        RETURNING id INTO doc_id;

        INSERT INTO just.document_lang (document_id, lang, title, descr)
        VALUES (doc_id, 'ru', 'Аналитический отчет о механизмах защиты и продвижения гендерного равенства в КР', '');

        INSERT INTO just.document_activity (document_id, activity_id)
        VALUES (doc_id, (SELECT id FROM just.activity WHERE slug = 'gender'));

        -- Документ 5
        INSERT INTO just.document (dtype, files, active, ord)
        VALUES ('LEGAL_DOCUMENT', '[
          {
            "type": "PDF",
            "path": "/files/law_chemic.pdf",
            "lang": "ru"
          }
        ]', true, 1)
        RETURNING id INTO doc_id;

        INSERT INTO just.document_lang (document_id, lang, title, descr)
        VALUES (doc_id, 'ru',
                'Анализ законодательства Кыргызской Республики в области обращения химических веществ и отходов', '');

        INSERT INTO just.document_activity (document_id, activity_id)
        VALUES (doc_id, (SELECT id FROM just.activity WHERE slug = 'ecology'));

        -- Документ 6
        INSERT INTO just.document (dtype, files, active, ord)
        VALUES ('LEGAL_DOCUMENT', '[
          {
            "type": "PDF",
            "path": "/files/bio_workgroup_2.pdf",
            "lang": "ru"
          }
        ]', true, 2)
        RETURNING id INTO doc_id;

        INSERT INTO just.document_lang (document_id, lang, title, descr)
        VALUES (doc_id, 'ru',
                'Биоразнообразие и изменение климата. Проект решения, представленный Председателем Рабочей группы II',
                '');

        INSERT INTO just.document_activity (document_id, activity_id)
        VALUES (doc_id, (SELECT id FROM just.activity WHERE slug = 'ecology'));

        -- Документ 7
        INSERT INTO just.document (dtype, files, active, ord)
        VALUES ('LEGAL_DOCUMENT', '[
          {
            "type": "WORD",
            "path": "/files/bio_conv.docx",
            "lang": "ru"
          }
        ]', true, 3)
        RETURNING id INTO doc_id;

        INSERT INTO just.document_lang (document_id, lang, title, descr)
        VALUES (doc_id, 'ru', 'Выполнение Кыргызстаном обязательств, вытекающих из Конвенции по биоразнообразию', '');

        INSERT INTO just.document_activity (document_id, activity_id)
        VALUES (doc_id, (SELECT id FROM just.activity WHERE slug = 'ecology'));

        -- Документ 8
        INSERT INTO just.document (dtype, files, active, ord)
        VALUES ('LEGAL_DOCUMENT', '[
          {
            "type": "PDF",
            "path": "/files/green_economic_ideas.pdf",
            "lang": "ru"
          }
        ]', true, 4)
        RETURNING id INTO doc_id;

        INSERT INTO just.document_lang (document_id, lang, title, descr)
        VALUES (doc_id, 'ru', 'Каталог проектных идей по “Зеленой экономике” Кыргызстана', '');

        INSERT INTO just.document_activity (document_id, activity_id)
        VALUES (doc_id, (SELECT id FROM just.activity WHERE slug = 'ecology'));

        -- Документ 9
        INSERT INTO just.document (dtype, files, active, ord)
        VALUES ('LEGAL_DOCUMENT', '[
          {
            "type": "WORD",
            "path": "/files/iter_eco_conventions.docx",
            "lang": "ru"
          }
        ]', true, 5)
        RETURNING id INTO doc_id;

        INSERT INTO just.document_lang (document_id, lang, title, descr)
        VALUES (doc_id, 'ru', 'Международные экологические конвенции, стороной которых является Кыргызская Республика',
                '');

        INSERT INTO just.document_activity (document_id, activity_id)
        VALUES (doc_id, (SELECT id FROM just.activity WHERE slug = 'ecology'));

        -- Документ 10
        INSERT INTO just.document (dtype, files, active, ord)
        VALUES ('LEGAL_DOCUMENT', '[
          {
            "type": "PDF",
            "path": "/files/sd_goals_report.pdf",
            "lang": "ru"
          }
        ]', true, 6)
        RETURNING id INTO doc_id;

        INSERT INTO just.document_lang (document_id, lang, title, descr)
        VALUES (doc_id, 'ru', 'Отчет о ходе достижения Целей Устойчивого Развития в Кыргызской Республике', '');

        INSERT INTO just.document_activity (document_id, activity_id)
        VALUES (doc_id, (SELECT id FROM just.activity WHERE slug = 'ecology'));

        -- Документ 11
        INSERT INTO just.document (dtype, files, active, ord)
        VALUES ('LEGAL_DOCUMENT', '[
          {
            "type": "WORD",
            "path": "/files/climate_chane_ca_request.docx",
            "lang": "ru"
          }
        ]', true, 7)
        RETURNING id INTO doc_id;

        INSERT INTO just.document_lang (document_id, lang, title, descr)
        VALUES (doc_id, 'ru', 'Обращение НПО стран Центральной Азии по вопросам изменения климата', '');

        INSERT INTO just.document_activity (document_id, activity_id)
        VALUES (doc_id, (SELECT id FROM just.activity WHERE slug = 'ecology'));

        -- Документ 12
        INSERT INTO just.document (dtype, files, active, ord)
        VALUES ('LEGAL_DOCUMENT', '[
          {
            "type": "PDF",
            "path": "/files/eco_inter_kr.pdf",
            "lang": "ru"
          }
        ]', true, 8)
        RETURNING id INTO doc_id;

        INSERT INTO just.document_lang (document_id, lang, title, descr)
        VALUES (doc_id, 'ru', 'Международные обязательства Кыргызской Республики в области охраны окружающей среды',
                '');

        INSERT INTO just.document_activity (document_id, activity_id)
        VALUES (doc_id, (SELECT id FROM just.activity WHERE slug = 'ecology'));

    END
$$;


-- Posters

INSERT INTO just.poster (slug, thumb, files, ord)
VALUES ('private-data-safety',
        '/tmb/e955a40d-e878-4b0c-b5ae-14ae8cfac2b6.jpg',
        '[
          {
            "type": "PDF",
            "path": "/files/private-data-safety.pdf",
            "lang": "ru"
          }
        ]',
        1);

INSERT INTO just.poster_lang (poster_id, title, descr)
VALUES ((SELECT id FROM just.poster WHERE slug = 'private-data-safety'),
        'Карта защиты персональных данных',
        '');

INSERT INTO just.poster_activity (poster_id, activity_id)
VALUES ((SELECT id FROM just.poster WHERE slug = 'private-data-safety'),
        (SELECT id FROM just.activity WHERE slug = 'privacy'));

INSERT INTO just.poster (slug, thumb, files, ord)
VALUES ('public-law-process',
        '/tmb/b2d29749-9d36-44cc-909b-baa6de1a6c9f.jpg',
        '[
          {
            "type": "PDF",
            "path": "/files/public-law-process.pdf",
            "lang": "ru"
          }
        ]',
        2);

INSERT INTO just.poster_lang (poster_id, title, descr)
VALUES ((SELECT id FROM just.poster WHERE slug = 'public-law-process'),
        'Участие гражданского общества в законотворческом процессе, а также в оценке системы защиты прав человека',
        '');

INSERT INTO just.poster (slug, thumb, files, ord)
VALUES ('eco-public-participation',
        '/tmb/5201076d-9f0d-4016-847f-05d2ef046a90.jpg',
        '[
          {
            "type": "PDF",
            "path": "/files/eco-public-participation.pdf",
            "lang": "ru"
          }
        ]',
        3);

INSERT INTO just.poster_lang (poster_id, title, descr)
VALUES ((SELECT id FROM just.poster WHERE slug = 'eco-public-participation'),
        'Как я могу вовлекаться в принятие значимых экологических решений?',
        '');

INSERT INTO just.poster_activity (poster_id, activity_id)
VALUES ((SELECT id FROM just.poster WHERE slug = 'eco-public-participation'),
        (SELECT id FROM just.activity WHERE slug = 'ecology'));


-- Speeches

INSERT INTO just.speech (slug, thumb)
VALUES ('gender-mechanisms',
        '/tmb/1892ec51-6c32-4aca-a182-527f338afc83.jpg');

INSERT INTO just.speech_lang (speech_id, title, descr, speaker)
VALUES ((SELECT id FROM just.speech WHERE slug = 'gender-mechanisms'),
        'Механизмы обеспечения гендерного равенства в мире и Кыргызстане',
        '',
        'Зульфия Кочорбаева');

INSERT INTO just.speech_activity (speech_id, activity_id)
VALUES ((SELECT id FROM just.speech WHERE slug = 'gender-mechanisms'),
        (SELECT id FROM just.activity WHERE slug = 'gender'));

INSERT INTO just.speech (slug, thumb)
VALUES ('international-law-and-human-rights',
        '/tmb/d131b4da-fd49-4cc0-b592-b61b10e809e8.jpg');

INSERT INTO just.speech_lang (speech_id, title, descr, speaker)
VALUES ((SELECT id FROM just.speech WHERE slug = 'international-law-and-human-rights'),
        'Международное право в сфере прав человека и его реализации в Кыргызстане',
        '',
        'Аннелиз Бостон');

INSERT INTO just.speech (slug, thumb)
VALUES ('eco-safety',
        '/tmb/aa99d532-7de0-45e4-8b68-f7b5f578b9e3.jpg');

INSERT INTO just.speech_lang (speech_id, title, descr, speaker)
VALUES ((SELECT id FROM just.speech WHERE slug = 'eco-safety'),
        'Защита экологической безопасности граждан в мире и Кыргызстане: нормы и практики',
        '',
        'Анна Кириленко');

INSERT INTO just.speech_activity (speech_id, activity_id)
VALUES ((SELECT id FROM just.speech WHERE slug = 'eco-safety'),
        (SELECT id FROM just.activity WHERE slug = 'ecology'));

commit;