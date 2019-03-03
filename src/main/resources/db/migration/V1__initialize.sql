SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS clients;

CREATE TABLE clients (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS goods;

CREATE TABLE goods (
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(45) NOT NULL,
  cost decimal(20,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO goods (title, cost)
VALUES
("Good1",1000),
("Good2",2000),
("Good3",3000),
("Good4",4000),
("Good5",5000),
("Good6",6000),
("Good7",7000),
("Good8",8000),
("Good9",9000),
("Good10",10000),
("Good11",11000),
("Good12",12000),
("Good13",13000),
("Good14",14000),
("Good15",15000),
("Good16",16000),
("Good17",17000),
("Good18",18000),
("Good19",19000),
("Good20",20000),
("Good21",21000),
("Good22",22000),
("Good23",23000),
("Good24",24000),
("Good25",25000),
("Good26",26000),
("Good27",27000),
("Good28",28000),
("Good29",29000),
("Good30",30000),
("Good31",31000);


DROP TABLE IF EXISTS trade_table;

CREATE TABLE trade_table (
  id_clients int(11) NOT NULL,
  id_goods int(11) NOT NULL,
  curr_cost decimal(20,0) NOT NULL,
  PRIMARY KEY (id_clients,id_goods),
  KEY id_clients_idx (id_clients),
  KEY id_goods_idx (id_goods),
  CONSTRAINT id_clients FOREIGN KEY (id_clients) REFERENCES clients (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT id_goods FOREIGN KEY (id_goods) REFERENCES goods (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;