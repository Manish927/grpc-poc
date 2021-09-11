drop table orders if exists;
drop table user if exists;

CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) DEFAULT NULL,
  password varchar(50) DEFAULT NULL,
  name varchar(50) DEFAULT NULL,
  gender varchar(50) DEFAULT NULL,
  age int(11) DEFAULT NULL,
  PRIMARY KEY (id)
);


INSERT INTO user
VALUES
(1,'Manish927','admin','Manish Srivastava1','MALE', 28),
(2,'Manish','christian','Manish Srivastava 2','MALE', 35),
(3,'abc','abc','Manish Srivastava 3','MALE', 45),
(4,'ross','ross','Ross 4','MALE', 35),
(5,'abc123','abc123','Manish Srivastava 5','MALE',35),
(6,'xyz','xyz','Manish Srivastava 6','FEMALE',34),
(7,'rachel','rachel','Manish Srivastava 7','FEMALE',40),
(8,'clemen','clemen','Manish Srivastava 8','FEMALE',40);


CREATE TABLE orders (
  order_id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) DEFAULT NULL,
  no_of_items int(11) DEFAULT NULL,
  total_amount double DEFAULT NULL,
  order_date date DEFAULT NULL,
  PRIMARY KEY (order_id),
  foreign key (user_id) references user(id)
 );


INSERT INTO orders VALUES (1,5,3,635,'2021-09-09'),(2,8,1,1045,'2021-09-09'), (3,5,5,700,'2021-09-09') ;
