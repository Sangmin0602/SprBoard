-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.0.17-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win32
-- HeidiSQL 버전:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 테이블 demoboard의 구조를 덤프합니다. postings
DROP TABLE IF EXISTS `postings`;
CREATE TABLE IF NOT EXISTS `postings` (
  `seq` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL,
  `content` text NOT NULL,
  `views` int(10) unsigned NOT NULL DEFAULT '0',
  `when_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` enum('Y','N') NOT NULL DEFAULT 'N',
  `fk_writer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `fk_writer_ref_users` (`fk_writer`),
  CONSTRAINT `fk_writer_ref_users` FOREIGN KEY (`fk_writer`) REFERENCES `users` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='각각의 게시판 글';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 demoboard의 구조를 덤프합니다. users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `seq` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid` varchar(24) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` char(40) NOT NULL,
  `when_joined` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `userid` (`userid`),
  UNIQUE KEY `nickname` (`nickname`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 demoboard의 구조를 덤프합니다. user_addrs
DROP TABLE IF EXISTS `user_addrs`;
CREATE TABLE IF NOT EXISTS `user_addrs` (
  `seq` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `addr_main` varchar(200) NOT NULL,
  `addr_sub` varchar(200) NOT NULL,
  `since` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fk_user` int(10) unsigned NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `FK_owner_of_address` (`fk_user`),
  CONSTRAINT `FK_owner_of_address` FOREIGN KEY (`fk_user`) REFERENCES `users` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 주소 정보';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 demoboard의 구조를 덤프합니다. zipcode
DROP TABLE IF EXISTS `zipcode`;
CREATE TABLE IF NOT EXISTS `zipcode` (
  `seq` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'pk 역할',
  `zipcode` char(6) NOT NULL COMMENT '우편번호',
  `serial` char(3) NOT NULL COMMENT '일련번호 3자리',
  `si_do` varchar(30) DEFAULT NULL COMMENT '광역시/도',
  `si_gun_gu` varchar(30) DEFAULT NULL COMMENT '시/군/구',
  `um_myun_dong` varchar(30) DEFAULT NULL COMMENT '읍/면/동',
  `ri` varchar(30) DEFAULT NULL COMMENT '리',
  `doseo` varchar(30) DEFAULT NULL COMMENT '도서지역명(섬이름)',
  `bungi` varchar(30) DEFAULT NULL COMMENT '번지(산)',
  `bungi_from_main` varchar(30) DEFAULT NULL COMMENT '시작번지-주번호',
  `bungi_from_sub` varchar(30) DEFAULT NULL COMMENT '시작번지-부번호',
  `bungi_to_main` varchar(30) DEFAULT NULL COMMENT '끝번지-주번호',
  `bungi_to_sub` varchar(30) DEFAULT NULL COMMENT '끝번지-부번호',
  `building_name` varchar(30) DEFAULT NULL COMMENT '건물명/아파트명',
  `dong_from` varchar(30) DEFAULT NULL COMMENT '(아파트인경우)동 시작 번호',
  `dong_to` varchar(30) DEFAULT NULL COMMENT '(아파트인경우)동 끝번호',
  `since` varchar(8) DEFAULT NULL COMMENT '갱신일',
  `address` varchar(100) DEFAULT NULL COMMENT '전체주소',
  PRIMARY KEY (`seq`),
  UNIQUE KEY `zipcode_serial` (`zipcode`,`serial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
