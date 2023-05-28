INSERT INTO END_USER
(ID, email_address, username, password, role, get_updates, enabled)
VALUES
    (nextval('USER_SEQ'), 'mechelenfeestapp@gmail.com', 'admin', '$2a$10$qkYEzClRvGL03mzmKI1Tw.WGRKzm19S5aqQUeFb7x5/pGw/bhq96K', 'ROLE_ADMIN', FALSE, TRUE);

INSERT INTO END_USER
(ID, email_address, username, password, role, get_updates, enabled)
VALUES
    (1, 'mechelenfeestapp@gmail.com', 'administratie', '$2a$10$qkYEzClRvGL03mzmKI1Tw.WGRKzm19S5aqQUeFb7x5/pGw/bhq96K', 'ROLE_ADMIN', FALSE, TRUE);

INSERT INTO END_USER
(ID, email_address, username, password, role, get_updates, enabled)
VALUES
    (nextval('USER_SEQ'), 'mechelenfeestapp@gmail.com', 'testuser', '$2a$10$qkYEzClRvGL03mzmKI1Tw.WGRKzm19S5aqQUeFb7x5/pGw/bhq96K', 'ROLE_USER', FALSE, TRUE);

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





INSERT INTO "PUBLIC"."FESTIVAL" (ID,BACKGROUND_COLOR, START_DATE ,END_DATE ,FESTIVAL_IMAGE, FESTIVAL_LINK, FESTIVAL_NAME,FESTIVAL_TYPE, MAX_CAPACITY, POPULATION)
                                VALUES
                                    (1, '#f28629', '2023-06-23', '2023-06-24', 'https://dezomerisvanmechelen.be/wp-content/uploads/2023/04/Maanrock_AIKON_20220826_18_42_33_Zonder-logo_low-scaled.jpg', 'https://dezomerisvanmechelen.be/event/de-koer-festival/','De Koer','Music',10000,0),
                                    (2, '#6FB746', '2023-06-29', '2023-08-17', 'https://dezomerisvanmechelen.be/wp-content/uploads/2023/04/Maanrock_AIKON_20220826_18_42_33_Zonder-logo_low-scaled.jpg', 'https://dezomerisvanmechelen.be/event/parkpop/','Parkpop','Culture',1000,0),
                                    (3, '#253679', '2023-08-25', '2023-08-27', 'https://dezomerisvanmechelen.be/wp-content/uploads/2023/04/Maanrock-Aikon-Producties-3-scaled.jpg', 'https://dezomerisvanmechelen.be/event/maanrock/','Maanrock','Music',1000,0),
                                    (4, '#d0422a', '2023-07-20', '2023-08-06', 'https://dezomerisvanmechelen.be/wp-content/uploads/2023/04/Maanrock_AIKON_20220828_13_35_16_Zonder-logo_low-scaled.jpg', 'https://dezomerisvanmechelen.be/event/mechelen-beach/','Mechelen Beach','Sport',1000,0),
                                    (5, '#9b51e0', '2023-07-01', '2023-09-30', 'https://dezomerisvanmechelen.be/wp-content/uploads/2023/04/Maanrock_AIKON_20220826_19_36_27_Zonder-logo_low-scaled.jpg', 'https://dezomerisvanmechelen.be/event/hap-food-festival/','Hap Food Festival','Culture',1000,0);

INSERT INTO TICKET
(ID, name, price, festival_Id)
VALUES
    (1, 'Dagticket vrijdag', 22.50 , 1);

INSERT INTO TICKET
(ID, name, price, festival_Id)
VALUES
    (2, 'Dagticket zaterdag', 22.50 , 1);

INSERT INTO TICKET
(ID, name, price, festival_Id)
VALUES
    (3, 'Combi ticket', 37.50 , 1);