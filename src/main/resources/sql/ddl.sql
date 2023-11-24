create table if not exists books
(
    id     serial primary key,
    "name" varchar(255) not null,
    isbn   varchar(255) not null
);

create table if not exists tags
(
    id     serial primary key,
    "name" varchar(255) unique not null
);

create table if not exists books_tags
(
    book_id integer references books (id),
    tag_id  integer references tags (id),
    primary key (book_id, tag_id)
);

create table if not exists teachers
(
    id   serial primary key,
    name varchar(255)
);

create table if not exists students
(
    id   serial primary key,
    name varchar(255)
);

create table if not exists teachers_students
(
    teacher_id integer references teachers (id),
    student_id integer references students (id),
    primary key (teacher_id, student_id)
);