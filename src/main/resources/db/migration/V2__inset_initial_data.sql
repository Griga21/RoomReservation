insert into coworking(name)
values ('Красная поляна'),
    ('Игора'),
    ('Охта-парк');

insert into rooms(count_seats)
values (20),
    (120),
    (2);

insert into orders(start_data, end_data, room_id, coworking_id)
values ('2021-01-02 05:05:00', '2021-01-02 09:00:00', 1, 1),
       ('2021-02-03 10:00:00', '2021-02-05 15:00:00', 2, 3),
       ('2021-05-10 15:00:00', '2021-05-13 18:00:00', 3, 2);