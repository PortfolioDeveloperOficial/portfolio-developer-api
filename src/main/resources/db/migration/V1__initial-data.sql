CREATE TABLE developers
(
    id           UUID        NOT NULL UNIQUE,
    pdi          varchar(12) NOT NULL,
    name         TEXT        NOT NULL,
    email        TEXT        NOT NULL,
    code         TEXT        NOT NULL,
    phone        text NULL,
    birth_date   DATE NULL,
    gender       varchar(1) NULL,
    image_url    TEXT NULL,
    bio          TEXT NULL,
    street       TEXT NULL,
    number       TEXT NULL,
    neighborhood TEXT NULL,
    cep          TEXT NULL,
    city         TEXT NULL,
    state        TEXT NULL,
    country      TEXT NULL,
    github       TEXT NULL,
    linkedin     TEXT NULL,
    PRIMARY KEY (id)
);