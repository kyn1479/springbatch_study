CREATE TABLE `boy` (
                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                       `name` varchar(255) DEFAULT NULL,
                       `sex` tinyint(4) DEFAULT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;


CREATE TABLE `customer` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `firstName` varchar(255) DEFAULT NULL,
                            `lastName` varchar(255) DEFAULT NULL,
                            `birthday` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;