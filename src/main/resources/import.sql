-- User
INSERT INTO USERS (USERNAME, EMAIL, PASSWORD) VALUES('admin', 'jakind@naver.com', '$2a$10$6jo.bY8lRZbuZi1Mz8r3led/i3PSH6RHX4.qHI2Q9pf.gXvh31soC'); -- 맨 뒤에 있는 것이 BCrypt 로 인코딩된 Sksk5839!임
INSERT INTO USERS (USERNAME, EMAIL, PASSWORD) VALUES('kpeel5839', 'yshert0605@gmail.com', '$2a$10$6jo.bY8lRZbuZi1Mz8r3led/i3PSH6RHX4.qHI2Q9pf.gXvh31soC');
INSERT INTO USERS (USERNAME, EMAIL, PASSWORD) VALUES('jakind', 'kpeel5839@naver.com', '$2a$10$6jo.bY8lRZbuZi1Mz8r3led/i3PSH6RHX4.qHI2Q9pf.gXvh31soC');

-- Album
INSERT INTO ALBUM (ID, ARTIST_NAME, DATE_OF_ISSUE, INTRODUCTION, NAME, CREATED_DATE, AVERAGE_OF_STAR, NUMBER_OF_REVIEW, HITS) VALUES(1, '박정민', '2022-12-12', '안녕하세요', '믹스테잎', '2022-12-25', 0.0, 0, 0);

-- Song
INSERT INTO SONG (ID, SONGS_IN_ALBUM) VALUES('1', 'intro');
INSERT INTO SONG (ID, SONGS_IN_ALBUM) VALUES('1', 'Classic old car');