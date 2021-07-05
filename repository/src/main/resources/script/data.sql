INSERT INTO tags (tag_id ,tag_name) VALUES('1','#funny');
INSERT INTO tags (tag_id ,tag_name) VALUES('2','#cool');
INSERT INTO tags (tag_id ,tag_name) VALUES('3','#warm');
INSERT INTO tags (tag_id ,tag_name) VALUES('4','#cold');
INSERT INTO tags (tag_id ,tag_name) VALUES('5','#relax');

INSERT INTO gift_certificates (gift_certificate_id, certificate_name, description, price, duration, create_date)
VALUES(1, 'Car', 'Fast car', '9999.99', 4, '2011-11-19T11:10:11.111');

INSERT INTO gift_certificates (gift_certificate_id, certificate_name, description, price, duration, create_date)
VALUES(2, 'Sand', 'Yellow sand', '2.35', 24, '2020-05-05T23:42:12.112');

INSERT INTO gift_certificates (gift_certificate_id, certificate_name, description, price, duration, create_date)
VALUES(3, 'City', 'Large city', '1000', 2, '2021-05-10T08:42:10.145');

INSERT INTO gift_certificates (gift_certificate_id, certificate_name, description, price, duration, create_date)
VALUES(4, 'Plane', 'Fly plain', '452.12', 6, '2016-01-29T16:30:21.211');

INSERT INTO gift_certificates (gift_certificate_id, certificate_name, description, price, duration, create_date)
VALUES(5, 'Ferry', 'Ferryman', '0.99', 14, '2019-11-19T11:10:11.111');

INSERT INTO gift_certificates_tags (gift_certificate_tag_id, gift_certificate_id_fk, tag_id_fk) VALUES (1, 1, 1);
INSERT INTO gift_certificates_tags (gift_certificate_tag_id, gift_certificate_id_fk, tag_id_fk) VALUES (2, 2, 1);
INSERT INTO gift_certificates_tags (gift_certificate_tag_id, gift_certificate_id_fk, tag_id_fk) VALUES (3, 3, 2);
INSERT INTO gift_certificates_tags (gift_certificate_tag_id, gift_certificate_id_fk, tag_id_fk) VALUES (4, 4, 2);
INSERT INTO gift_certificates_tags (gift_certificate_tag_id, gift_certificate_id_fk, tag_id_fk) VALUES (5, 5, 3);

INSERT INTO users (user_id, first_name, last_name, email) VALUES(1, 'Alice','Green','alice@gmail.com');
INSERT INTO users (user_id, first_name, last_name, email) VALUES(2, 'Marting','Grace','marting@gmail.com');
INSERT INTO users (user_id, first_name, last_name, email) VALUES(3, 'David','Sky','david@gmail.com');
INSERT INTO users (user_id, first_name, last_name, email) VALUES(4, 'Bob','Space','spacebob123@gmail.com');
INSERT INTO users (user_id, first_name, last_name, email) VALUES(5, 'Rick','Sun','always_sun@gmail.com');

INSERT INTO orders (order_id, price, timestamp, gift_certificate_id_fk, user_id_fk)
VALUES(1, '0.99', '2012-10-10T11:10:11.111', 1, 1);

INSERT INTO orders (order_id, price, timestamp, gift_certificate_id_fk, user_id_fk)
VALUES(2, '99.99', '2021-06-26T16:10:11.111', 2, 1);

INSERT INTO orders (order_id, price, timestamp, gift_certificate_id_fk, user_id_fk)
VALUES(3, '99.99', '2019-12-31T23:59:59.111', 3, 1);

INSERT INTO orders (order_id, price, timestamp, gift_certificate_id_fk, user_id_fk)
VALUES(4, '2.35', '2011-11-19T11:10:11.111', 4, 1);

INSERT INTO orders (order_id, price, timestamp, gift_certificate_id_fk, user_id_fk)
VALUES(5, '1000', '2011-11-19T11:10:11.111', 5, 1);