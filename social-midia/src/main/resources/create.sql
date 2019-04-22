create table tbl_comment (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=MyISAM
create table tbl_configs_comments (id_config bigint not null, id_comment bigint not null) engine=MyISAM
create table tbl_configs_tags (id_config bigint not null, id_tag bigint not null) engine=MyISAM
create table tbl_configuration (id bigint not null auto_increment, description varchar(255), start varchar(255), stop varchar(255), browser varchar(255), sharePercent integer, likePercent integer, executed datetime, id_user bigint, primary key (id)) engine=MyISAM
create table tbl_history (id bigint not null auto_increment, comment tinyblob, tag tinyblob, url varchar(255), primary key (id)) engine=MyISAM
create table tbl_tag (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=MyISAM
create table tbl_user (id bigint not null auto_increment, name varchar(255), pass varchar(255), type varchar(255), user varchar(255), primary key (id)) engine=MyISAM
alter table tbl_configs_comments add constraint FKs9ua9xs6kkovi83s6hmsfce62 foreign key (id_comment) references tbl_comment (id)
alter table tbl_configs_comments add constraint FKm3d98yxcqho8tew04minukahy foreign key (id_config) references tbl_configuration (id)
alter table tbl_configs_tags add constraint FKfjmfgo8fry59jvph1e6eoucyq foreign key (id_tag) references tbl_tag (id)
alter table tbl_configs_tags add constraint FKnv9lu5nl3bhu5lixskexkqgg4 foreign key (id_config) references tbl_configuration (id)
alter table tbl_configuration add constraint FKl0od4h8pt6vyqg925nmjl0m76 foreign key (id_user) references tbl_user (id)







INSERT INTO `socialmidia`.`tbl_user`
(
`name`,
`pass`,
`type`,
`user`)
VALUES
(
'Mamãezona','u79Lodl4C3uCrr5mXxjTeg==',
'INSTAGRAM',
'mamaezona.web');



INSERT INTO `socialmidia`.`tbl_configuration`
(
`description`,
`start`,
`stop`,
`browser`,
`sharePercent`,
`likePercent`,
`executed`,
`id_user`)
VALUES
(
'Mamãezona Insta',
'0700',
'1900',
'CHROME',
0,
100,
null,
1);

