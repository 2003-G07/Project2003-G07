INSERT INTO address
VALUES 
	(1, 'Bromma', 'Lorem ipsum 47', '123 10'),
	(2, 'Gustavsberg', 'Lorem ipsum 20', '123 11'),
	(3, 'Hässelby', 'Lorem ipsum 41', '123 12');

INSERT INTO customer
VALUES 
	(1, 'Viktor', 'Hagström', '070-1234567', 'Viktor@mail.se', 1),
	(2, 'Elias', 'Gilani', '071-1234567', 'Elias@mail.se', 2),
	(3, 'Hui', 'Huang', '072-1234567', 'Hui@mail.se', '3');
    
INSERT INTO orders
VALUES 
	(1, 2, '2021-03-14', 394, 0, 2),
    (2, 1, '2021-03-23', 265, 1, 1),
    (3, 3, '2021-03-03', 282, 2, 3);
    
INSERT INTO product
VALUES 
	(1, 'Falun Gong Earl Grey', 11, 'https://picsum.photos/500?random=1', 'Ett svart te smaksatt med bergamott.', 160),
    (2, 'Mörk Choklad', 38, 'https://picsum.photos/500?random=2', 'En Raw-vegansk chokladkaka med 100% kakaohalt.', 260),
    (3, 'Vaniljstång, Madagaskar Bourbon', 33, 'https://picsum.photos/500?random=3', 'Madagaskar Bourbon av högsta kvalitet, 12-14 cm långa.', 900);
    
INSERT INTO order_details
VALUES 
	(1, 1, 3),
    (2, 1, 2),
    (3, 1, 2),
    (4, 1, 1),
    (5, 2, 2),
    (6, 2, 3),
    (7, 2, 1),
    (8, 3, 3),
    (9, 3, 1),
    (10, 3, 2);
    
INSERT INTO category
VALUES 
	(1, 'Te'),
    (2, 'Godis'),
    (3, 'Kaffe'),
    (4, 'Krydda');
        
INSERT INTO product_category
VALUES 
	(1, 1, 1),
    (2, 1, 3),
    (3, 2, 2),
    (4, 3, 4);