CREATE TABLE gift_certificates
(
    gift_certificate_id bigint         NOT NULL AUTO_INCREMENT,
    certificate_name    varchar(256)   NOT NULL,
    description         text           NOT NULL,
    price               decimal(10, 0) NOT NULL,
    duration            int            NOT NULL,
    create_date         datetime       NOT NULL,
    last_update_date    datetime DEFAULT NULL,
    PRIMARY KEY (gift_certificate_id)
);

CREATE TABLE tags
(
    tag_id   bigint       NOT NULL AUTO_INCREMENT,
    tag_name varchar(256) NOT NULL,
    PRIMARY KEY (tag_id)
);

CREATE TABLE gift_certificates_tags
(
    gift_certificate_tag_id bigint NOT NULL AUTO_INCREMENT,
    gift_certificate_id_fk  bigint NOT NULL REFERENCES gift_certificates (gift_certificate_id),
    tag_id_fk               bigint NOT NULL REFERENCES tags (tag_id),
    PRIMARY KEY (gift_certificate_tag_id)

);

CREATE TABLE users
(
    user_id    bigint       NOT NULL AUTO_INCREMENT,
    first_name varchar(256) NOT NULL,
    last_name  varchar(256) NOT NULL,
    email      varchar(320) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE users_gift_certificates
(
    user_gift_certificate_id bigint NOT NULL AUTO_INCREMENT,
    user_id_fk               bigint NOT NULL REFERENCES users (user_id),
    gift_certificate_id_fk   bigint NOT NULL REFERENCES gift_certificates (gift_certificate_id),
    PRIMARY KEY (user_gift_certificate_id)
);