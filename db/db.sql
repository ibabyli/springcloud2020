drop DATABASE if exists db2019;
create database db2019;

use db2019;

DROP TABLE IF EXISTS PAYMENT;
CREATE TABLE PAYMENT (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  serial varchar(200) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;