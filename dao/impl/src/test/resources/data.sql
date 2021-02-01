INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('Spa', 'Hot relax to you', 500, 2, '2020-09-12 15:00:00', '2021-01-01 17:00:00');
INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('Visit Spain', 'Chill with family', 10, 11, '2020-01-01 00:00:00', '2021-01-14 00:00:00');
INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('Exhibition', 'Look and smile', 300, 1, '2020-12-12 11:30:00', '2020-12-13 21:00:00');
INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('Market', 'Spend money', 175, 1, '2021-01-03 08:00:00', '2021-01-04 08:00:00');

INSERT INTO tag (name) VALUES ('chill');
INSERT INTO tag (name) VALUES ('relax');
INSERT INTO tag (name) VALUES ('work');
INSERT INTO tag (name) VALUES ('funny');

INSERT INTO tag_has_gift_certificate(gift_certificate_id, tag_id) VALUES (1, 1);
INSERT INTO tag_has_gift_certificate(gift_certificate_id, tag_id) VALUES (2, 2);
INSERT INTO tag_has_gift_certificate(gift_certificate_id, tag_id) VALUES (3, 1);
INSERT INTO tag_has_gift_certificate(gift_certificate_id, tag_id) VALUES (4, 2);

INSERT INTO user(name)
VALUES ('Ivan');
INSERT INTO user(name)
VALUES ('Vladislav');
INSERT INTO user(name)
VALUES ('Gleb');

INSERT INTO orders(purchase_date, cost, gift_certificate_id, user_id)
VALUES ('2020-02-01 17:00:00', 2500, 2, 1);
INSERT INTO orders(purchase_date, cost, gift_certificate_id, user_id)
VALUES ('2017-09-22 22:00:00', 3000, 3, 2);
INSERT INTO orders(purchase_date, cost, gift_certificate_id, user_id)
VALUES ('2016-07-15 13:00:00', 750, 4, 3);