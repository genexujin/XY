--------------------------------------------------------
--  File created - Sunday-January-13-2013   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type CHARTABLETYPE
--------------------------------------------------------

  CREATE OR REPLACE TYPE "OA"."CHARTABLETYPE" as table of varchar2(4000);

/

--------------------------------------------------------
--  DDL for Sequence ANNOUNCEMENTS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."ANNOUNCEMENTS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence DEPARTMENT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."DEPARTMENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence EMPLOYEES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."EMPLOYEES_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence LOCATIONS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."LOCATIONS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence LOVS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."LOVS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence NOTIFICATIONS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."NOTIFICATIONS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence ROLES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."ROLES_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table ANNOUNCEMENTS
--------------------------------------------------------

  CREATE TABLE "OA"."ANNOUNCEMENTS" 
   (	"ID" VARCHAR2(20 BYTE), 
	"SENDER_ID" VARCHAR2(20 BYTE), 
	"SENDER_NAME" VARCHAR2(20 BYTE), 
	"TITLE" VARCHAR2(200 BYTE), 
	"CONTENT" VARCHAR2(2000 BYTE), 
	"ATTACHMENT_PATH" VARCHAR2(100 BYTE), 
	"SENT_DATE" DATE, 
	"EXPIRE_DATE" DATE, 
	"CATEGORY" VARCHAR2(20 BYTE), 
	"IMPORTANCE" VARCHAR2(20 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table DEPARTMENTS
--------------------------------------------------------

  CREATE TABLE "OA"."DEPARTMENTS" 
   (	"DEPT_ID" VARCHAR2(20 BYTE), 
	"DEPT_NAME" VARCHAR2(50 BYTE), 
	"LOCATION_ID" VARCHAR2(20 BYTE), 
	"MGR_ID" VARCHAR2(20 BYTE), 
	"CREATED_AT" DATE, 
	"CREATED_BY" VARCHAR2(20 BYTE), 
	"LAST_UPDATED_AT" DATE, 
	"LAST_UPDATED_BY" VARCHAR2(20 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table EMPLOYEES
--------------------------------------------------------

  CREATE TABLE "OA"."EMPLOYEES" 
   (	"USER_NAME" VARCHAR2(20 BYTE), 
	"PASSWORD" VARCHAR2(20 BYTE), 
	"FIRST_NAME" VARCHAR2(20 BYTE), 
	"LAST_NAME" VARCHAR2(20 BYTE), 
	"GENDER" VARCHAR2(20 BYTE), 
	"MGR_ID" VARCHAR2(20 BYTE), 
	"DEPT_ID" VARCHAR2(20 BYTE), 
	"EMAIL" VARCHAR2(100 BYTE), 
	"MOBILE" VARCHAR2(20 BYTE), 
	"OFFICE_PHONE" VARCHAR2(20 BYTE), 
	"HOME_PHONE" VARCHAR2(20 BYTE), 
	"FAX" VARCHAR2(20 BYTE), 
	"LOCATION_ID" VARCHAR2(20 BYTE), 
	"HOME_ADDRESS" VARCHAR2(20 BYTE), 
	"OFFICE_ADDRESS" VARCHAR2(20 BYTE), 
	"PHOTO" BLOB, 
	"LAST_UPDATED_BY" VARCHAR2(20 BYTE), 
	"LAST_UPDATED_AT" DATE, 
	"CREATED_BY" VARCHAR2(20 BYTE), 
	"CREATED_AT" DATE, 
	"TITLE" VARCHAR2(20 BYTE), 
	"ID" VARCHAR2(20 BYTE)
   ) 
 LOB ("PHOTO") STORE AS BASICFILE "SYS_LOB0000020052C00016$$"(ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING ) ;
--------------------------------------------------------
--  DDL for Table LOCATIONS
--------------------------------------------------------

  CREATE TABLE "OA"."LOCATIONS" 
   (	"LOCATION_ID" VARCHAR2(20 BYTE), 
	"FAX" VARCHAR2(20 BYTE), 
	"STREET_ADDRESS" VARCHAR2(200 BYTE), 
	"POSTAL_CODE" VARCHAR2(20 BYTE), 
	"LOCATION_NAME" VARCHAR2(20 BYTE), 
	"FRONT_DESK_PHONE" VARCHAR2(20 BYTE), 
	"CREATED_AT" DATE, 
	"CREATED_BY" VARCHAR2(20 BYTE), 
	"LAST_UPDATED_AT" DATE, 
	"LAST_UPDATED_BY" VARCHAR2(20 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table LOVS
--------------------------------------------------------

  CREATE TABLE "OA"."LOVS" 
   (	"ID" VARCHAR2(20 BYTE), 
	"CODE" VARCHAR2(20 BYTE), 
	"LOV_DESC" VARCHAR2(200 BYTE), 
	"PARENT_CODE" VARCHAR2(20 BYTE), 
	"VALUE" VARCHAR2(20 BYTE), 
	"CREATED_AT" DATE, 
	"CREATED_BY" VARCHAR2(20 BYTE), 
	"LAST_UPDATED_BY" VARCHAR2(20 BYTE), 
	"LAST_UPDATED_AT" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table MENUS
--------------------------------------------------------

  CREATE TABLE "OA"."MENUS" 
   (	"MENU_ID" VARCHAR2(20 BYTE), 
	"MENU_NAME" VARCHAR2(20 BYTE), 
	"MENU_TASKFLOW_URL" VARCHAR2(500 BYTE), 
	"MENU_DESC" VARCHAR2(20 BYTE), 
	"MENU_CATEGORY" VARCHAR2(20 BYTE), 
	"PARENT_MENU_ID" VARCHAR2(20 BYTE), 
	"MENU_MASTER_CATEGORY" VARCHAR2(20 BYTE), 
	"MENU_ICON_URL" VARCHAR2(100 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table NOTIFICATIONS
--------------------------------------------------------

  CREATE TABLE "OA"."NOTIFICATIONS" 
   (	"ID" VARCHAR2(20 BYTE), 
	"CATEGORY" VARCHAR2(20 BYTE), 
	"PRIORITY" VARCHAR2(20 BYTE), 
	"TITLE" VARCHAR2(200 BYTE), 
	"CONTENT" VARCHAR2(2000 BYTE), 
	"TO_USER_ID" VARCHAR2(20 BYTE), 
	"EVENT_DATE" DATE, 
	"IS_SMS_SENT" VARCHAR2(20 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table ROLES
--------------------------------------------------------

  CREATE TABLE "OA"."ROLES" 
   (	"ROLE_ID" VARCHAR2(20 BYTE), 
	"ROLE_NAME" VARCHAR2(50 BYTE), 
	"ROLE_DESC" VARCHAR2(200 BYTE), 
	"CREATED_AT" DATE, 
	"CREATED_BY" VARCHAR2(20 BYTE), 
	"LAST_UPDATED_AT" DATE, 
	"LAST_UPDATED_BY" VARCHAR2(20 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table ROLE_MENUS
--------------------------------------------------------

  CREATE TABLE "OA"."ROLE_MENUS" 
   (	"ROLE_ID" VARCHAR2(20 BYTE), 
	"MENU_ID" VARCHAR2(20 BYTE), 
	"CREATED_BY" VARCHAR2(20 BYTE), 
	"CREATED_AT" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table ROLE_USERS
--------------------------------------------------------

  CREATE TABLE "OA"."ROLE_USERS" 
   (	"ROLE_ID" VARCHAR2(20 BYTE), 
	"USER_ID" VARCHAR2(20 BYTE), 
	"CREATED_AT" DATE, 
	"CREATED_BY" VARCHAR2(20 BYTE)
   ) ;
REM INSERTING into OA.ANNOUNCEMENTS
REM INSERTING into OA.DEPARTMENTS
REM INSERTING into OA.EMPLOYEES
Insert into OA.EMPLOYEES (USER_NAME,PASSWORD,FIRST_NAME,LAST_NAME,GENDER,MGR_ID,DEPT_ID,EMAIL,MOBILE,OFFICE_PHONE,HOME_PHONE,FAX,LOCATION_ID,HOME_ADDRESS,OFFICE_ADDRESS,LAST_UPDATED_BY,LAST_UPDATED_AT,CREATED_BY,CREATED_AT,TITLE,ID) values ('gene','welcome1','晋','徐','男',null,null,'gene.xujin@gmail.com','18621910893','50172665','50172665','50172665',null,'浦电路305弄','丽园路11101',null,null,null,null,'管理员','1');
Insert into OA.EMPLOYEES (USER_NAME,PASSWORD,FIRST_NAME,LAST_NAME,GENDER,MGR_ID,DEPT_ID,EMAIL,MOBILE,OFFICE_PHONE,HOME_PHONE,FAX,LOCATION_ID,HOME_ADDRESS,OFFICE_ADDRESS,LAST_UPDATED_BY,LAST_UPDATED_AT,CREATED_BY,CREATED_AT,TITLE,ID) values ('pairliu','welcome1',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,'2');
REM INSERTING into OA.LOCATIONS
REM INSERTING into OA.LOVS
REM INSERTING into OA.MENUS
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_PUR_MY_PR','我的采购申请',null,'我的采购申请','PUR',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_PUR_APPROVE','审批',null,'审批','PUR',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_PUR_REVIEW','审核',null,'审核','PUR',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_PUR_RECEIVE','收货',null,'收货','PUR',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_PUR_PR','采购订单',null,'采购订单','PUR',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_HD_MY_REQ','我的报修',null,'我的报修','HD',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_HD_REQ','报修单',null,'报修单','HD',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_HD_PROC','报修处理',null,'报修处理','HD',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CLSRM_CAL','教室日历',null,'教室日历','CLSRM',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CLSRM_BATCH','批量预定',null,'批量预定','CLSRM',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CONFRM_CAL','会议室日历',null,'会议室日历','CONFRM',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CONFRM_REQ','会议室预定',null,'会议室预定','CONFRM',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CONFRM_APPROVE','审核',null,'审核','CONFRM',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CAR_CAL','车辆日历',null,'车辆日历','CAR',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CAR_REQ','用车申请',null,'用车申请','CAR',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CAR_APPROVE','审核',null,'审核','CAR',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_MEAL_REQ','订餐',null,'订餐','MEAL',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_MEAL_FORECAST','订餐量预测',null,'订餐量预测','MEAL',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_ANCMT_REQ','发通知',null,'发通知','ANCMT',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_ANCMT_LIST','通知列表',null,'通知列表','ANCMT',null,'SYS',null);
REM INSERTING into OA.NOTIFICATIONS
REM INSERTING into OA.ROLES
Insert into OA.ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('1','系统管理员','系统管理员',null,null,null,null);
Insert into OA.ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('2','采购员',null,null,null,null,null);
Insert into OA.ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('3','副院长',null,null,null,null,null);
Insert into OA.ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('4','采购部主任',null,null,null,null,null);
REM INSERTING into OA.ROLE_MENUS
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_ANCMT_LIST',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_ANCMT_REQ',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_MEAL_FORECAST',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_MEAL_REQ',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_CAR_APPROVE',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_CAR_REQ',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_CAR_CAL',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_CONFRM_APPROVE',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_CONFRM_REQ',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_CONFRM_CAL',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_CLSRM_BATCH',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_CLSRM_CAL',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_HD_PROC',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_HD_REQ',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_HD_MY_REQ',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_PUR_PR',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_PUR_RECEIVE',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_PUR_REVIEW',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_PUR_APPROVE',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_PUR_MY_PR',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('2','SYS_PUR_MY_PR',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('2','SYS_PUR_RECEIVE',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('3','SYS_PUR_APPROVE',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('4','SYS_PUR_REVIEW',null,null);
REM INSERTING into OA.ROLE_USERS
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('1','1',null,null);
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('2','2',null,null);
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('3','2',null,null);
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('4','2',null,null);
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('1','2',null,null);
--------------------------------------------------------
--  DDL for Index ROLE_USERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."ROLE_USERS_PK" ON "OA"."ROLE_USERS" ("ROLE_ID", "USER_ID") 
  ;
--------------------------------------------------------
--  DDL for Index DEPARTMENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."DEPARTMENT_PK" ON "OA"."DEPARTMENTS" ("DEPT_ID") 
  ;
--------------------------------------------------------
--  DDL for Index ANNOUNCEMENTS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."ANNOUNCEMENTS_PK" ON "OA"."ANNOUNCEMENTS" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index LOVS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."LOVS_PK" ON "OA"."LOVS" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index LOCATIONS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."LOCATIONS_PK" ON "OA"."LOCATIONS" ("LOCATION_ID") 
  ;
--------------------------------------------------------
--  DDL for Index ROLE_MENUS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."ROLE_MENUS_PK" ON "OA"."ROLE_MENUS" ("ROLE_ID", "MENU_ID") 
  ;
--------------------------------------------------------
--  DDL for Index NOTIFICATIONS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."NOTIFICATIONS_PK" ON "OA"."NOTIFICATIONS" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index ROLES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."ROLES_PK" ON "OA"."ROLES" ("ROLE_ID") 
  ;
--------------------------------------------------------
--  DDL for Index EMPLOYEES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."EMPLOYEES_PK" ON "OA"."EMPLOYEES" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index MENUS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."MENUS_PK" ON "OA"."MENUS" ("MENU_ID") 
  ;
--------------------------------------------------------
--  Constraints for Table DEPARTMENTS
--------------------------------------------------------

  ALTER TABLE "OA"."DEPARTMENTS" ADD CONSTRAINT "DEPARTMENT_PK" PRIMARY KEY ("DEPT_ID") ENABLE;
  ALTER TABLE "OA"."DEPARTMENTS" MODIFY ("DEPT_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ANNOUNCEMENTS
--------------------------------------------------------

  ALTER TABLE "OA"."ANNOUNCEMENTS" ADD CONSTRAINT "ANNOUNCEMENTS_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "OA"."ANNOUNCEMENTS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table NOTIFICATIONS
--------------------------------------------------------

  ALTER TABLE "OA"."NOTIFICATIONS" ADD CONSTRAINT "NOTIFICATIONS_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "OA"."NOTIFICATIONS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ROLES
--------------------------------------------------------

  ALTER TABLE "OA"."ROLES" ADD CONSTRAINT "ROLES_PK" PRIMARY KEY ("ROLE_ID") ENABLE;
  ALTER TABLE "OA"."ROLES" MODIFY ("ROLE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table LOVS
--------------------------------------------------------

  ALTER TABLE "OA"."LOVS" ADD CONSTRAINT "LOVS_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "OA"."LOVS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EMPLOYEES
--------------------------------------------------------

  ALTER TABLE "OA"."EMPLOYEES" ADD CONSTRAINT "EMPLOYEES_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "OA"."EMPLOYEES" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."EMPLOYEES" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "OA"."EMPLOYEES" MODIFY ("USER_NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MENUS
--------------------------------------------------------

  ALTER TABLE "OA"."MENUS" ADD CONSTRAINT "MENUS_PK" PRIMARY KEY ("MENU_ID") ENABLE;
  ALTER TABLE "OA"."MENUS" MODIFY ("MENU_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ROLE_USERS
--------------------------------------------------------

  ALTER TABLE "OA"."ROLE_USERS" ADD CONSTRAINT "ROLE_USERS_PK" PRIMARY KEY ("ROLE_ID", "USER_ID") ENABLE;
  ALTER TABLE "OA"."ROLE_USERS" MODIFY ("USER_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."ROLE_USERS" MODIFY ("ROLE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table LOCATIONS
--------------------------------------------------------

  ALTER TABLE "OA"."LOCATIONS" ADD CONSTRAINT "LOCATIONS_PK" PRIMARY KEY ("LOCATION_ID") ENABLE;
  ALTER TABLE "OA"."LOCATIONS" MODIFY ("LOCATION_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ROLE_MENUS
--------------------------------------------------------

  ALTER TABLE "OA"."ROLE_MENUS" ADD CONSTRAINT "ROLE_MENUS_PK" PRIMARY KEY ("ROLE_ID", "MENU_ID") ENABLE;
  ALTER TABLE "OA"."ROLE_MENUS" MODIFY ("MENU_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."ROLE_MENUS" MODIFY ("ROLE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  DDL for Trigger ANNOUNCEMENTS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."ANNOUNCEMENTS_TRG" BEFORE INSERT ON ANNOUNCEMENTS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.ID IS NULL THEN
      SELECT ANNOUNCEMENTS_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."ANNOUNCEMENTS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger DEPARTMENT_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."DEPARTMENT_TRG" BEFORE INSERT ON DEPARTMENTS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.DEPT_ID IS NULL THEN
      SELECT DEPARTMENT_SEQ.NEXTVAL INTO :NEW.DEPT_ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."DEPARTMENT_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger EMPLOYEES_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."EMPLOYEES_TRG" BEFORE INSERT ON EMPLOYEES 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.ID IS NULL THEN
      SELECT EMPLOYEES_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."EMPLOYEES_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger LOCATIONS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."LOCATIONS_TRG" BEFORE INSERT ON LOCATIONS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.LOCATION_ID IS NULL THEN
      SELECT LOCATIONS_SEQ.NEXTVAL INTO :NEW.LOCATION_ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."LOCATIONS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger LOVS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."LOVS_TRG" BEFORE INSERT ON LOVS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.ID IS NULL THEN
      SELECT LOVS_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."LOVS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger NOTIFICATIONS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."NOTIFICATIONS_TRG" BEFORE INSERT ON NOTIFICATIONS 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.ID IS NULL THEN
      SELECT NOTIFICATIONS_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."NOTIFICATIONS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger ROLES_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."ROLES_TRG" BEFORE INSERT ON ROLES 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.ROLE_ID IS NULL THEN
      SELECT ROLES_SEQ.NEXTVAL INTO :NEW.ROLE_ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."ROLES_TRG" ENABLE;
