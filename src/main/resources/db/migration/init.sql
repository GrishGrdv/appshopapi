CREATE TABLE good_category(
    id             bigint primary key not null,
    name           varchar(255),
    description    varchar
);

CREATE TABLE good(
    id             bigint primary key not null,
    name           varchar(255),
    description    varchar,
    price          NUMERIC,
    category       bigint,
    FOREIGN KEY (category) REFERENCES good (id)
);

CREATE TABLE payment_type(
    id             bigint primary key not null,
    title          varchar(255),
    description    varchar
);

CREATE TABLE delivery_type(
    id             bigint primary key not null,
    title          varchar(255),
    description    varchar
);

CREATE TABLE user_info(
    id             bigint primary key not null,
    name           varchar(255),
    email          varchar,
    password       varchar(255)
);

CREATE TABLE recipient(
    id                  bigint primary key not null,
    firstName           varchar,
    lastName            varchar,
    middleName          varchar,
    address             varchar,
    zipCode             varchar,
    phone               varchar,
    password            varchar(255),
    user_info           bigint,
    FOREIGN KEY (user_info) REFERENCES user_info (id)
);

CREATE TABLE transaction(
    id              bigint primary key not null,
    createdAt       date,
    updatedAt       date,
    status          varchar(255),
    amount          NUMERIC,
    provider_data   jsonb
);

CREATE TABLE checkout(
    id                  bigint primary key not null,
    user_info           bigint,
    transaction         bigint,
    payment_type        bigint,
    delivery_type       bigint,
    basket              jsonb,
    recipient           jsonb,
    FOREIGN KEY (user_info) REFERENCES user_info (id),
    FOREIGN KEY (transaction) REFERENCES transaction (id),
    FOREIGN KEY (payment_type) REFERENCES payment_type (id),
    FOREIGN KEY (delivery_type) REFERENCES delivery_type (id)
);

CREATE TABLE refresh_token(
    id              bigint primary key not null,
    username        varchar(255),
    refreshToken    varchar,
    revoked         boolean,
    dateCreated     date
);