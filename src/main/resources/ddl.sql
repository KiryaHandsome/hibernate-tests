create table books
(
    id     serial primary key,
    "name" varchar(255) not null,
    isbn   varchar(255) not null
);

create table tags
(
    id     serial primary key,
    "name" varchar(255) unique not null
);

create table books_tags
(
    book_id integer references books (id),
    tag_id  integer references tags (id),
    primary key (book_id, tag_id)
);