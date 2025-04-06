create table if not exists coworking(
    id serial primary key,
    name varchar(128) not null
);

create table if not exists rooms(
    room_id serial primary key,
    count_seats serial not null
);

create table if not exists orders(
    order_id serial primary key,
    start_data timestamp not null,
    end_data timestamp not null,
    room_id integer references rooms(room_id),
    coworking_id integer references coworking(id)
);
