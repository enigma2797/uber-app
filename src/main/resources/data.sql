INSERT INTO app_user(email, password, name) VALUES
('sraj@gmail.com', 'dummy', 'Saurav Raj'),
('jdoe@gmail.com', 'password123', 'John Doe'),
('asmith@gmail.com', 'securePass', 'Alice Smith'),
('bjones@gmail.com', 'passw0rd', 'Bob Jones'),
('mjohnson@gmail.com', '12345abc', 'Mary Johnson'),
('kwhite@gmail.com', 'password1', 'Karen White'),
('jwilson@gmail.com', 'qwerty123', 'James Wilson'),
('rclark@gmail.com', 'letmein', 'Robert Clark'),
('jwalker@gmail.com', 'password2', 'Jessica Walker'),
('lking@gmail.com', 'abc123', 'Larry King'),
('nmartin@gmail.com', 'mypassword', 'Nancy Martin'),
('pwright@gmail.com', 'admin123', 'Paul Wright'),
('jsanchez@gmail.com', 'defaultPass', 'Jose Sanchez'),
('lbrown@gmail.com', 'mySecret', 'Linda Brown'),
('ggarcia@gmail.com', 'tempPass', 'George Garcia'),
('mmoore@gmail.com', 'pass1234', 'Michael Moore'),
('jwilliams@gmail.com', 'changeMe', 'Jennifer Williams'),
('rlee@gmail.com', 'password3', 'Richard Lee'),
('tmiller@gmail.com', 'myPass123', 'Thomas Miller'),
('hrodriguez@gmail.com', 'superSecret', 'Helen Rodriguez'),
('dgreen@gmail.com', 'pass0987', 'David Green'),
('jcook@gmail.com', 'myPassword1', 'Julia Cook'),
('awood@gmail.com', 'pass4321', 'Andrew Wood'),
('harris@gmail.com', 'dummyPass', 'Henry Harris'),
('parker@gmail.com', 'pass9999', 'Paula Parker'),
('thompson@gmail.com', 'password4', 'Timothy Thompson');


INSERT INTO user_roles(user_id, roles) VALUES
(1, 'RIDER'),
(2, 'DRIVER'),
(2, 'RIDER'),
(3, 'RIDER'),
(4, 'DRIVER'),
(4, 'RIDER'),
(5, 'RIDER'),
(6, 'DRIVER'),
(6, 'RIDER'),
(7, 'RIDER'),
(8, 'DRIVER'),
(8, 'RIDER'),
(9, 'RIDER'),
(10, 'DRIVER'),
(10, 'RIDER'),
(11, 'RIDER'),
(12, 'DRIVER'),
(12, 'RIDER'),
(13, 'RIDER'),
(14, 'DRIVER'),
(14, 'RIDER'),
(15, 'RIDER'),
(16, 'DRIVER'),
(16, 'RIDER'),
(17, 'RIDER'),
(18, 'DRIVER'),
(18, 'RIDER'),
(19, 'RIDER'),
(20, 'DRIVER'),
(20, 'RIDER'),
(21, 'RIDER'),
(22, 'DRIVER'),
(22, 'RIDER'),
(23, 'RIDER'),
(24, 'DRIVER'),
(24, 'RIDER'),
(25, 'RIDER');

INSERT INTO rider(user_id,rating) VALUES(1,4.9);

INSERT INTO driver(user_id, available, current_location, rating, vehicle_id) VALUES
(2, TRUE, ST_GeomFromText('POINT(77.1025 28.7041)', 4326), 4.5, 'V2'),  -- Delhi
(3, TRUE, ST_GeomFromText('POINT(77.2315 28.6139)', 4326), 4.6, 'V3'),  -- Connaught Place, Delhi
(4, TRUE, ST_GeomFromText('POINT(77.2089 28.6261)', 4326), 4.7, 'V4'),  -- India Gate, Delhi
(5, TRUE, ST_GeomFromText('POINT(77.2566 28.6123)', 4326), 4.8, 'V5'),  -- Akshardham, Delhi
(6, TRUE, ST_GeomFromText('POINT(77.1940 28.5494)', 4326), 4.9, 'V6'),  -- Qutub Minar, Delhi
(7, TRUE, ST_GeomFromText('POINT(77.2244 28.6847)', 4326), 4.2, 'V7'),  -- Red Fort, Delhi
(8, TRUE, ST_GeomFromText('POINT(77.2663 28.5276)', 4326), 4.3, 'V8'),  -- Lotus Temple, Delhi
(9, TRUE, ST_GeomFromText('POINT(77.1116 28.6863)', 4326), 4.4, 'V9'),  -- Pitampura, Delhi
(10, TRUE, ST_GeomFromText('POINT(77.0916 28.7041)', 4326), 4.5, 'V10'), -- Rohini, Delhi
(11, TRUE, ST_GeomFromText('POINT(77.0587 28.6880)', 4326), 4.6, 'V11'), -- Paschim Vihar, Delhi
(12, TRUE, ST_GeomFromText('POINT(77.0754 28.5276)', 4326), 4.7, 'V12'), -- Janakpuri, Delhi
(13, TRUE, ST_GeomFromText('POINT(77.0843 28.5273)', 4326), 4.8, 'V13'), -- Dwarka, Delhi
(14, TRUE, ST_GeomFromText('POINT(77.2303 28.5460)', 4326), 4.9, 'V14'), -- Nehru Place, Delhi
(15, TRUE, ST_GeomFromText('POINT(77.2770 28.6885)', 4326), 4.2, 'V15'), -- Shastri Park, Delhi
(16, TRUE, ST_GeomFromText('POINT(77.2859 28.6143)', 4326), 4.3, 'V16'), -- Laxmi Nagar, Delhi
(17, TRUE, ST_GeomFromText('POINT(77.2089 28.5815)', 4326), 4.4, 'V17'), -- Safdarjung, Delhi
(18, TRUE, ST_GeomFromText('POINT(77.1907 28.6338)', 4326), 4.5, 'V18'), -- Chanakyapuri, Delhi
(19, TRUE, ST_GeomFromText('POINT(77.2145 28.6561)', 4326), 4.6, 'V19'), -- Kashmiri Gate, Delhi
(20, TRUE, ST_GeomFromText('POINT(77.2752 28.6448)', 4326), 4.7, 'V20'), -- Anand Vihar, Delhi
(21, TRUE, ST_GeomFromText('POINT(77.3146 28.5355)', 4326), 4.8, 'V21'), -- Ghaziabad
(22, TRUE, ST_GeomFromText('POINT(77.3056 28.4089)', 4326), 4.9, 'V22'), -- Faridabad
(23, TRUE, ST_GeomFromText('POINT(76.9861 28.4211)', 4326), 4.2, 'V23'), -- Gurgaon
(24, TRUE, ST_GeomFromText('POINT(77.2760 28.4595)', 4326), 4.3, 'V24'), -- Noida
(25, TRUE, ST_GeomFromText('POINT(77.1235 28.6692)', 4326), 4.4, 'V25'); -- North Delhi


INSERT INTO wallet(user_id,balance)
VALUES(1,500),(2,100);

