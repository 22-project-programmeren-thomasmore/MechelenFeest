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
                                    (1, '#f28629', '23 - 24.06.2023', 'https://dezomerisvanmechelen.be/wp-content/uploads/2023/04/Maanrock_AIKON_20220826_18_42_33_Zonder-logo_low-scaled.jpg', 'https://dezomerisvanmechelen.be/event/de-koer-festival/','De Koer','Music',10000,false,0),
                                    (2, '#6FB746', '29.06 - 17.08.2023', 'https://dezomerisvanmechelen.be/wp-content/uploads/2023/04/Maanrock_AIKON_20220826_18_42_33_Zonder-logo_low-scaled.jpg', 'https://dezomerisvanmechelen.be/event/parkpop/','Parkpop','Culture',1000,false,0),
                                    (3, '#253679', '25.08 - 27.08.2023', 'https://dezomerisvanmechelen.be/wp-content/uploads/2023/04/Maanrock-Aikon-Producties-3-scaled.jpg', 'https://dezomerisvanmechelen.be/event/maanrock/','Maanrock','Music',1000,false,0),
                                    (4, '#d0422a', '20.07 - 06.08.2023', 'https://dezomerisvanmechelen.be/wp-content/uploads/2023/04/Maanrock_AIKON_20220828_13_35_16_Zonder-logo_low-scaled.jpg', 'https://dezomerisvanmechelen.be/event/mechelen-beach/','Mechelen Beach','Sport',1000,false,0),
                                    (5, '#9b51e0', '01.07 - 30.09.2023', 'https://dezomerisvanmechelen.be/wp-content/uploads/2023/04/Maanrock_AIKON_20220826_19_36_27_Zonder-logo_low-scaled.jpg', 'https://dezomerisvanmechelen.be/event/hap-food-festival/','Hap Food Festival','Culture',1000,false,0),
                                    (6, '#4e8496', '25.08 - 27.08.2023', 'https://dezomerisvanmechelen.be/wp-content/uploads/2023/04/Maanrock_AIKON_20220826_19_36_27_Zonder-logo_low-scaled.jpg', null,'Momenteele Event','Technology',1000,true,756);