CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_users_user_roles_idx` (`role_id`),
  CONSTRAINT `fk_users_user_roles` FOREIGN KEY (`role_id`) REFERENCES `user_roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `log_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `authorities` longtext,
  `action` longtext,
  `status` longtext,
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

CREATE TABLE `email_notifications` (
  `notification_id` int(11) NOT NULL,
  `notification` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `users_notifications` (
  `user_id` int(11) NOT NULL,
  `notification_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`notification_id`),
  KEY `FK_NOTIFICATION` (`notification_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
