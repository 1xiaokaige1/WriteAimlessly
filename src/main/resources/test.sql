create table test001
(
    id      int primary key auto_increment,
    `name`  varchar(100),
    age     int,
    address varchar(200)
);

create index id_one on test001
(
   age
);