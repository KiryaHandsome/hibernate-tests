insert into books(name, isbn)
values ('First book', 'ISBN1a-a-a-a'),
       ('Second book', 'ISBN2b-b-b-b');

insert into tags(name)
values ('rom-com'),
       ('comedy'),
       ('phantasy'),
       ('adventure');

insert into books_tags(book_id, tag_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 2);

insert into teachers(name)
values ('First teacher'),
       ('Second teacher'),
       ('Third teacher'),
       ('Fourth teacher');

insert into students(name)
values ('student1'),
       ('student2'),
       ('student3'),
       ('student4');

insert into teachers_students(teacher_id, student_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 2),
       (2, 4),
       (4, 4),
       (3, 1);