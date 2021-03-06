DROP TABLE IF EXISTS imagen;

CREATE TABLE if not exists imagen(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY not null,
    nombre VARCHAR(60) NOT NULL,
    file_size INTEGER not null,
    file_type VARCHAR(60) not null,
    img_base_64 clob not null
);

