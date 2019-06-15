CREATE DATABASE  IF NOT EXISTS `sales_service`;
USE `sales_service`;

--
-- Table structure`
--

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `product_in_warehouse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `price` float NOT NULL,
  `discountPrice` float NOT NULL,
  `quantityToDiscount` int(11) NOT NULL,
  FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Insert sample data into tables
--

INSERT INTO `product` VALUES
(1, "Cookie"),
(2, "Cup"),
(3, "Phone");

INSERT INTO `product_in_warehouse` VALUES
(1, 1, 10, 45, 5),
(2, 2, 25, 230, 10),
(3, 3, 300, 1000, 4);

