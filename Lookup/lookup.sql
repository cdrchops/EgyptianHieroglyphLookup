-- create view gardiner as select group_concat(distinct gardiner separator ', '), group_concat(distinct transliteration separator ', '), group_concat(distinct translation separator ', ') from dictionary group by gardiner;
-- create view transliteration as select group_concat(distinct transliteration separator ', '), group_concat(distinct gardiner separator ', '), group_concat(distinct translation separator ', ') from dictionary group by transliteration;
-- create view translation as select group_concat(distinct translation separator ', '), group_concat(distinct gardiner separator ', '), group_concat(distinct transliteration separator ', ') from dictionary group by translation;
-- select * from dictionary where translation = 'abomination noun';

-- select * from hiero

select * from dictionary;