CREATE TABLE `points` (
  `place` int(2) NOT NULL,
  `points` int(2) NOT NULL,
  PRIMARY KEY (`points`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `drivers` (
  `id` int(2) NOT NULL AUTO_INCREMENT,	
  `name` varchar(100) NOT NULL,
  `points` int(4) NOT NULL,
  `wins` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
