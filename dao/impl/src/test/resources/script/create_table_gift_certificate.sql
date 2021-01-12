CREATE TABLE gift_certificate (
    id   BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45)   NOT NULL,
    description           VARCHAR(200)  NOT NULL,
    price                 DECIMAL(10, 2) NOT NULL,
    duration              INT            NOT NULL,
    create_date           DATETIME       NOT NULL,
    last_update_date      DATETIME       NOT NULL
);