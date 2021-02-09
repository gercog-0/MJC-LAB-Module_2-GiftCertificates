CREATE TABLE gift_certificate (
    id   BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45)   NOT NULL,
    description           VARCHAR(200)  NOT NULL,
    price                 DECIMAL(10, 2) NOT NULL,
    duration              INT            NOT NULL,
    create_date           DATETIME       NOT NULL,
    last_update_date      DATETIME       NOT NULL
) ENGINE = InnoDB;

CREATE TABLE tag (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) UNIQUE
) ENGINE = InnoDB;

CREATE TABLE tag_has_gift_certificate (
    tag_id              BIGINT NOT NULL,
    gift_certificate_id BIGINT NOT NULL,
    PRIMARY KEY (tag_id, gift_certificate_id)
) ENGINE = InnoDB;

CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE orders (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cost DECIMAL(19,2) NOT NULL,
    purchase_date DATETIME NOT NULL,
    gift_certificate_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
) ENGINE = InnoDB;