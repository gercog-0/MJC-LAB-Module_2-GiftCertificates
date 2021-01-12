CREATE TABLE gift_certificate_has_tag
(
    tag_id              BIGINT NOT NULL,
    gift_certificate_id BIGINT NOT NULL,
    PRIMARY KEY (tag_id, gift_certificate_id)
);