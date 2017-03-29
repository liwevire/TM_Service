CREATE TABLE `customer` (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `secondary_name` varchar(50) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `post` varchar(40) DEFAULT NULL,
  `pin` varchar(6) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `loan` (
  `loan_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) DEFAULT NULL,
  `loan_status` varchar(10) DEFAULT 'open',
  `weight` decimal(13,3) DEFAULT '0.000',
  `comments` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`loan_id`),
  KEY `customer_fk` (`customer_id`),
  CONSTRAINT `customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `item` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `loan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `loan_fk` (`loan_id`),
  CONSTRAINT `transaction_loan_item_fk` FOREIGN KEY (`loan_id`) REFERENCES `loan` (`loan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `transaction` (
  `transaction_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `category` varchar(40) DEFAULT NULL,
  `amount` decimal(13,3) DEFAULT NULL,
  `loan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `loan_fk` (`loan_id`),
  CONSTRAINT `transaction_loan_fk` FOREIGN KEY (`loan_id`) REFERENCES `loan` (`loan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;