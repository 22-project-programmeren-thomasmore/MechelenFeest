INSERT INTO END_USER
(ID, email_address, username, password, role, get_updates)
VALUES
    (nextval('USER_SEQ'), 'mechelenfeestapp@gmail.com', 'admin', '$2a$10$qkYEzClRvGL03mzmKI1Tw.WGRKzm19S5aqQUeFb7x5/pGw/bhq96K', 'ROLE_ADMIN', FALSE);

INSERT INTO END_USER
(ID, email_address, username, password, role, get_updates)
VALUES
    (nextval('USER_SEQ'), 'mechelenfeestapp@gmail.com', 'testuser', '$2a$10$qkYEzClRvGL03mzmKI1Tw.WGRKzm19S5aqQUeFb7x5/pGw/bhq96K', 'ROLE_USER', FALSE);

insert into SPOT(
    ID,SPOT_TYPE,MAP_CORDINATX,MAP_CORDINATY,SPOT_NAME,SHORT_INFO,ADRESS)
VALUES (
           1,'TOILET',50,50,'cafeToilet','je moet wel iets kopen','mechelenStraat 84');

insert into SPOT(
    ID,SPOT_TYPE,MAP_CORDINATX,MAP_CORDINATY,SPOT_NAME,SHORT_INFO,ADRESS,START_DATE,EXTRA_LINK)
VALUES (
           2,'FESTIVAL',176,200,'de Koer','DE KOER zal dit jaar niet meer op Maanrock staan, maar krijgt zijn eigen festival','Cultuurplein Mechelen','2023-04-04','https://www.dezomerisvanmechelen.be/evenement/de-koer/info');

insert into SPOT(
    ID,SPOT_TYPE,MAP_CORDINATX,MAP_CORDINATY,SPOT_NAME,SHORT_INFO,ADRESS)
VALUES (
           3,'TOILET',75,90,'wc kot','een publiek toilet','NekerpoelPlein');

insert into SPOT(
    ID,SPOT_TYPE,MAP_CORDINATX,MAP_CORDINATY,SPOT_NAME,SHORT_INFO,ADRESS)
VALUES (
           4,'VOEDSELKRAAM',35,180,'HotDog Jho','een klein kraampje','KappersLaan');





INSERT INTO "PUBLIC"."FESTIVAL" VALUES
                                    (1, '#f28629', '23 - 24.06.2023', 'https://www.dezomerisvanmechelen.be/sites/default/files/styles/event_cover_front/public/events/cover/KOER_484x600.png?itok=_jrPk8Ly', 'https://www.dezomerisvanmechelen.be/evenement/de-koer/info','De Koer',10000,false,0),
                                    (2, '#6FB746', '29.06 - 17.08.2023', 'https://www.dezomerisvanmechelen.be/sites/default/files/styles/event_cover_front/public/events/cover/parkpop_2017.jpg?itok=WC0BUTak', 'https://www.dezomerisvanmechelen.be/evenement/parkpop/info','Parkpop',1000,false,0),
                                    (3, '#253679', '25.08 - 27.08.2023', 'https://www.dezomerisvanmechelen.be/sites/default/files/styles/event_cover_front/public/events/cover/190823-201655-Maanrock2019-JD%20%28Groot%29.jpg?itok=QF0Db-NJ', 'https://www.maanrock.be/','Maanrock',1000,false,0),
                                    (4, '#4e8496', '25.08 - 27.08.2023', 'https://www.dezomerisvanmechelen.be/sites/default/files/styles/event_cover_front/public/events/cover/190823-201655-Maanrock2019-JD%20%28Groot%29.jpg?itok=QF0Db-NJ', null,'Momenteele Event',1000,true,756);