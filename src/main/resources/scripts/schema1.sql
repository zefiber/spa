
CREATE TABLE IF NOT EXISTS `user_request` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ip_address` int(11) unsigned NOT NULL,
  `req_query` varchar(300) NOT NULL DEFAULT '',
  `req_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `favourite_movie` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ip_address` int(11) unsigned NOT NULL,
  `movie_imdb_id` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


