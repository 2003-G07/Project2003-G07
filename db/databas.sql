CREATE TABLE address(
	address_id INT,
	address_city VARCHAR(255),
	address_address VARCHAR(255),
	address_zip VARCHAR(255),

	PRIMARY KEY(address_id)
);

CREATE TABLE customer(
	customer_id INT,
	customer_fname VARCHAR(255),
	customer_lname VARCHAR(255),
	customer_tel VARCHAR(255),
	customer_email VARCHAR(255),
    customer_address_id INT,

	PRIMARY KEY(customer_id),
	FOREIGN KEY(customer_address_id) REFERENCES address(address_id)
);

CREATE TABLE orders(
	orders_id INT,
	customer_id INT,
	orders_date DATE,
	orders_cost DECIMAL(7,2),
    orders_status INT,
    orders_address_id INT,

	PRIMARY KEY(orders_id),
	FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY(orders_address_id) REFERENCES address(address_id)
);

CREATE TABLE product(
	product_id INT,
    product_name VARCHAR(255),
    product_quantity INT,
    product_img_link VARCHAR(255),
    product_description VARCHAR(255),
    product_price DECIMAL(6.2),
    
	PRIMARY KEY(product_id)
);

CREATE TABLE order_details(
	order_details_id INT,
    orders_id INT,
    product_id INT,

    PRIMARY KEY(order_details_id),
	FOREIGN KEY(orders_id) REFERENCES orders(orders_id),
	FOREIGN KEY(product_id) REFERENCES product(product_id)
);


CREATE TABLE category(
	category_id INT,
    category_name VARCHAR (255),
    
	PRIMARY KEY(category_id)
);


CREATE TABLE product_category(
	product_category_id INT,
    product_id INT,
    category_id INT,
    
	PRIMARY KEY(product_category_id),
	FOREIGN KEY(product_id) REFERENCES product(product_id),
	FOREIGN KEY(category_id) REFERENCES category(category_id)
);



CREATE TABLE admins(
	admins_id INT,
	admins_fname VARCHAR(255),
	admins_lname VARCHAR(255),
	admins_tel VARCHAR(255),
	admins_email VARCHAR(255),

	PRIMARY KEY(admins_id) 
);


   
    
    
    
    
	
    

