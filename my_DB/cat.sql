/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/12/22 20:51:42                          */
/*==============================================================*/


drop table if exists cat;

drop table if exists category;

drop table if exists color;

drop table if exists feature;

drop table if exists feed;

drop table if exists food_preference;

drop table if exists location;

drop table if exists user;

/*==============================================================*/
/* Table: cat                                                   */
/*==============================================================*/
create table cat
(
   cat_id               int not null,
   color_id             int not null,
   food_id              int not null,
   category_id          int not null,
   feature_id           int not null,
   location_id          int,
   cat_name             char(24) not null,
   primary key (cat_id)
);

/*==============================================================*/
/* Table: category                                              */
/*==============================================================*/
create table category
(
   category_id          int not null,
   category_name        char(12) not null,
   category_description char(32),
   primary key (category_id)
);

alter table category comment '猫咪种类';

/*==============================================================*/
/* Table: color                                                 */
/*==============================================================*/
create table color
(
   color_id             int not null,
   color_name           char(12) not null,
   primary key (color_id)
);

alter table color comment '猫咪颜色';

/*==============================================================*/
/* Table: feature                                               */
/*==============================================================*/
create table feature
(
   feature_id           int not null,
   feature_name         char(8) not null,
   feature_description  char(32),
   primary key (feature_id)
);

/*==============================================================*/
/* Table: feed                                                  */
/*==============================================================*/
create table feed
(
   feed_id              int not null,
   cat_id               int not null,
   user_id              bigint not null,
   feed_time            datetime not null,
   feed_location        char(24) not null,
   feed_food            char(24) not null,
   primary key (feed_id)
);

alter table feed comment '表示喂养关系';

/*==============================================================*/
/* Table: food_preference                                       */
/*==============================================================*/
create table food_preference
(
   food_id              int not null,
   food_name            char(12) not null,
   food_description     char(32),
   primary key (food_id)
);

alter table food_preference comment '猫咪食物偏好';

/*==============================================================*/
/* Table: location                                              */
/*==============================================================*/
create table location
(
   location_id          int not null,
   location_name        char(127) not null,
   primary key (location_id)
);

alter table location comment '常出现的位置';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              bigint not null,
   user_name            char(16) not null,
   user_account         longtext not null,
   user_password        longtext not null,
   primary key (user_id)
);

alter table cat add constraint FK_appear foreign key (location_id)
      references location (location_id) on delete restrict on update restrict;

alter table cat add constraint FK_cat_category foreign key (category_id)
      references category (category_id) on delete restrict on update restrict;

alter table cat add constraint FK_cat_character foreign key (feature_id)
      references feature (feature_id) on delete restrict on update restrict;

alter table cat add constraint FK_cat_color foreign key (color_id)
      references color (color_id) on delete restrict on update restrict;

alter table cat add constraint FK_food_preference foreign key (food_id)
      references food_preference (food_id) on delete restrict on update restrict;

alter table feed add constraint FK_be_feeded foreign key (cat_id)
      references cat (cat_id) on delete restrict on update restrict;

alter table feed add constraint FK_execute_feed foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

