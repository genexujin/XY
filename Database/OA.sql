ALTER SESSION SET NLS_DATE_LANGUAGE='AMERICAN';

--------------------------------------------------------
--  File created - Friday-February-01-2013   
--------------------------------------------------------
  DROP TABLE "OA"."ANNOUNCEMENTS";
  DROP TABLE "OA"."CLASSROOM_BATCH_RESERVATION";
  DROP TABLE "OA"."CLASSROOM_CALENDAR";
  DROP TABLE "OA"."CONF_ROOM_CALENDAR";
  DROP TABLE "OA"."DEPARTMENTS";
  DROP TABLE "OA"."EMPLOYEES";
  DROP TABLE "OA"."HELPDESK_CALLS";
  DROP TABLE "OA"."LOCATIONS";
  DROP TABLE "OA"."LOVS";
  DROP TABLE "OA"."MENUS";
  DROP TABLE "OA"."NOTIFICATIONS";
  DROP TABLE "OA"."PS_TXN";
  DROP TABLE "OA"."PURCHASE_ORDERS";
  DROP TABLE "OA"."PURCHASE_ORDER_HISTORYS";
  DROP TABLE "OA"."PURCHASE_ORDER_LINES";
  DROP TABLE "OA"."REPAIR_CALLS";
  DROP TABLE "OA"."ROLES";
  DROP TABLE "OA"."ROLE_MENUS";
  DROP TABLE "OA"."ROLE_USERS";
  DROP TABLE "OA"."TASKS";
  DROP TABLE "OA"."VEHICLE_CALENDAR";
  DROP SEQUENCE "OA"."ANNOUNCEMENTS_SEQ";
  DROP SEQUENCE "OA"."CLASSROOM_BATCH_RESERVATI_SEQ";
  DROP SEQUENCE "OA"."CLASSROOM_CALENDAR_SEQ";
  DROP SEQUENCE "OA"."CONF_ROOM_CALENDAR_SEQ";
  DROP SEQUENCE "OA"."DEPARTMENT_SEQ";
  DROP SEQUENCE "OA"."EMPLOYEES_SEQ";
  DROP SEQUENCE "OA"."HELPDESK_CALLS_SEQ";
  DROP SEQUENCE "OA"."LOCATIONS_SEQ";
  DROP SEQUENCE "OA"."LOVS_SEQ";
  DROP SEQUENCE "OA"."NOTIFICATIONS_SEQ";
  DROP SEQUENCE "OA"."PS_TXN_SEQ";
  DROP SEQUENCE "OA"."PURCHASE_ORDERS_SEQ";
  DROP SEQUENCE "OA"."PURCHASE_ORDER_HISTORYS_SEQ";
  DROP SEQUENCE "OA"."PURCHASE_ORDER_LINES_SEQ";
  DROP SEQUENCE "OA"."REPAIR_CALLS_SEQ";
  DROP SEQUENCE "OA"."ROLES_SEQ";
  DROP SEQUENCE "OA"."TASKS_SEQ";
  DROP SEQUENCE "OA"."VEHICLE_ALENDAR_SEQ";
  DROP TYPE "OA"."CHARTABLETYPE";
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
--  DDL for Sequence CLASSROOM_BATCH_RESERVATI_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."CLASSROOM_BATCH_RESERVATI_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence CLASSROOM_CALENDAR_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."CLASSROOM_CALENDAR_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 321 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence CONF_ROOM_CALENDAR_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."CONF_ROOM_CALENDAR_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence DEPARTMENT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."DEPARTMENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence EMPLOYEES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."EMPLOYEES_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 43 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence HELPDESK_CALLS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."HELPDESK_CALLS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence LOCATIONS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."LOCATIONS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence LOVS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."LOVS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence NOTIFICATIONS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."NOTIFICATIONS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PS_TXN_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."PS_TXN_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50 START WITH 6951 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PURCHASE_ORDERS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."PURCHASE_ORDERS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PURCHASE_ORDER_HISTORYS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."PURCHASE_ORDER_HISTORYS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PURCHASE_ORDER_LINES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."PURCHASE_ORDER_LINES_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence REPAIR_CALLS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."REPAIR_CALLS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence ROLES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."ROLES_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 22 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TASKS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."TASKS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence VEHICLE_ALENDAR_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OA"."VEHICLE_ALENDAR_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
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
--  DDL for Table CLASSROOM_BATCH_RESERVATION
--------------------------------------------------------

  CREATE TABLE "OA"."CLASSROOM_BATCH_RESERVATION" 
   (	"ID" VARCHAR2(20 BYTE), 
	"BATCH_NO" VARCHAR2(20 BYTE), 
	"ACT_TITLE" VARCHAR2(200 BYTE), 
	"ACT_START_TIME" TIMESTAMP (6), 
	"ACT_END_TIME" TIMESTAMP (6), 
	"USER_ID" VARCHAR2(20 BYTE), 
	"USER_DISPLAY_NAME" VARCHAR2(20 BYTE), 
	"NUM_OF_PEOPLE" NUMBER(*,0), 
	"CLASSROOM" VARCHAR2(20 BYTE), 
	"COMMENTS" VARCHAR2(200 BYTE), 
	"BATCH_EFFECTIVE_DATE" DATE, 
	"BATCH_EXPIRE_DATE" DATE, 
	"RECURRENCE_TYPE" VARCHAR2(20 BYTE), 
	"FST_WK_MON" VARCHAR2(20 BYTE), 
	"FST_WK_TUE" VARCHAR2(20 BYTE), 
	"FST_WK_WED" VARCHAR2(20 BYTE), 
	"FST_WK_THU" VARCHAR2(20 BYTE), 
	"FST_WK_FRI" VARCHAR2(20 BYTE), 
	"FST_WK_SAT" VARCHAR2(20 BYTE), 
	"FST_WK_SUN" VARCHAR2(20 BYTE), 
	"SND_WK_MON" VARCHAR2(20 BYTE), 
	"SND_WK_TUE" VARCHAR2(20 BYTE), 
	"SND_WK_WED" VARCHAR2(20 BYTE), 
	"SND_WK_THU" VARCHAR2(20 BYTE), 
	"SND_WK_FRI" VARCHAR2(20 BYTE), 
	"SND_WK_SAT" VARCHAR2(20 BYTE), 
	"SND_WK_SUN" VARCHAR2(20 BYTE), 
	"LOCATION_ID" VARCHAR2(20 BYTE), 
	"LOCATION_NAME" VARCHAR2(20 BYTE), 
	"CLASSROOM_ID" VARCHAR2(20 BYTE), 
	"ALL_DAY" VARCHAR2(10 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table CLASSROOM_CALENDAR
--------------------------------------------------------

  CREATE TABLE "OA"."CLASSROOM_CALENDAR" 
   (	"ID" VARCHAR2(20 BYTE), 
	"ACT_TITLE" VARCHAR2(200 BYTE), 
	"ACT_START_TIME" TIMESTAMP (6), 
	"ACT_END_TIME" TIMESTAMP (6), 
	"USER_ID" VARCHAR2(20 BYTE), 
	"USER_DISPLAY_NAME" VARCHAR2(20 BYTE), 
	"NUM_OF_PEOPLE" NUMBER(*,0), 
	"CLASSROOM" VARCHAR2(50 BYTE), 
	"COMMENTS" VARCHAR2(200 BYTE), 
	"CREATED_AT" DATE, 
	"BATCH_ID" VARCHAR2(20 BYTE), 
	"BATCH_NO" VARCHAR2(20 BYTE), 
	"LOCATION_ID" VARCHAR2(20 BYTE), 
	"LOCATION_NAME" VARCHAR2(20 BYTE), 
	"CLASSROOM_ID" VARCHAR2(20 BYTE), 
	"ALL_DAY" VARCHAR2(10 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table CONF_ROOM_CALENDAR
--------------------------------------------------------

  CREATE TABLE "OA"."CONF_ROOM_CALENDAR" 
   (	"ID" VARCHAR2(20 BYTE), 
	"USER_ID" VARCHAR2(20 BYTE), 
	"USER_DISPLAY_NAME" VARCHAR2(20 BYTE), 
	"LOCATION_ID" VARCHAR2(20 BYTE), 
	"LOCATION_NAME" VARCHAR2(20 BYTE), 
	"MEETING_ROOM" VARCHAR2(20 BYTE), 
	"MEETING_ROOM_ID" VARCHAR2(20 BYTE), 
	"TITLE" VARCHAR2(50 BYTE), 
	"PARTICIPANTS" VARCHAR2(100 BYTE), 
	"START_TIME" TIMESTAMP (6), 
	"END_TIME" TIMESTAMP (6), 
	"COMMENTS" VARCHAR2(500 BYTE), 
	"NEED_PROJECTOR" VARCHAR2(5 BYTE), 
	"NEED_LOUDSPEAKER" VARCHAR2(5 BYTE), 
	"NEED_BEVERAGE" VARCHAR2(5 BYTE), 
	"NEED_FRUITS" VARCHAR2(5 BYTE), 
	"NEED_SNACKS" VARCHAR2(5 BYTE), 
	"SNACK_LEVEL" VARCHAR2(20 BYTE), 
	"NUM_OF_PEOPLE" NUMBER, 
	"STATE" VARCHAR2(20 BYTE), 
	"PR_ID" VARCHAR2(20 BYTE), 
	"ALLDAY" VARCHAR2(20 BYTE)
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
	"LAST_UPDATED_BY" VARCHAR2(20 BYTE), 
	"MGR_NAME" VARCHAR2(20 BYTE)
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
	"ID" VARCHAR2(20 BYTE), 
	"MGR_NAME" VARCHAR2(20 BYTE), 
	"DEPT_NAME" VARCHAR2(20 BYTE)
   ) 
 LOB ("PHOTO") STORE AS BASICFILE "SYS_LOB0000020052C00016$$"(ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING ) ;
--------------------------------------------------------
--  DDL for Table HELPDESK_CALLS
--------------------------------------------------------

  CREATE TABLE "OA"."HELPDESK_CALLS" 
   (	"CALL_ID" VARCHAR2(20 BYTE), 
	"CALL_READABLE_ID" VARCHAR2(30 BYTE), 
	"CALLER_ID" VARCHAR2(20 BYTE), 
	"CALLEE_ID" VARCHAR2(20 BYTE), 
	"LOCATION_ID" VARCHAR2(20 BYTE), 
	"LOCATION_DETAIL" VARCHAR2(200 BYTE), 
	"CREATE_BY" VARCHAR2(20 BYTE), 
	"CREATE_AT" DATE, 
	"LAST_UPDATED_BY" VARCHAR2(20 BYTE), 
	"LAST_UPDATED_AT" DATE, 
	"STATE" VARCHAR2(20 BYTE), 
	"CALL_RESULT" VARCHAR2(20 BYTE), 
	"CALL_RESULT_DETAIL" VARCHAR2(500 BYTE), 
	"CALL_EVAL" VARCHAR2(20 BYTE), 
	"CALL_EVAL_DETAIL" VARCHAR2(20 BYTE), 
	"REASON_LEVEL_1" VARCHAR2(40 BYTE), 
	"REASON_LEVEL_2" VARCHAR2(40 BYTE), 
	"REASON_LEVEL_3" VARCHAR2(40 BYTE), 
	"REASON_DETAIL" VARCHAR2(500 BYTE)
   ) ;
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
	"LAST_UPDATED_AT" DATE, 
	"FLEX_COL1" VARCHAR2(200 BYTE), 
	"FLEX_COL2" VARCHAR2(200 BYTE), 
	"FLEX_COL3" VARCHAR2(200 BYTE)
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
	"PRIORITY" NUMBER(*,0), 
	"TITLE" VARCHAR2(200 BYTE), 
	"CONTENT" VARCHAR2(2000 BYTE), 
	"TO_ROLE_ID" VARCHAR2(20 BYTE), 
	"EVENT_DATE" DATE, 
	"IS_SMS_SENT" VARCHAR2(1 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table PS_TXN
--------------------------------------------------------

  CREATE TABLE "OA"."PS_TXN" 
   (	"ID" NUMBER(20,0), 
	"PARENTID" NUMBER(20,0), 
	"COLLID" NUMBER(10,0), 
	"CONTENT" BLOB, 
	"CREATION_DATE" DATE DEFAULT sysdate
   ) ;
--------------------------------------------------------
--  DDL for Table PURCHASE_ORDERS
--------------------------------------------------------

  CREATE TABLE "OA"."PURCHASE_ORDERS" 
   (	"ORDER_ID" VARCHAR2(20 BYTE), 
	"ORDER_READABLE_ID" VARCHAR2(30 BYTE), 
	"SUBMITTER_ID" VARCHAR2(20 BYTE),
  "ITEM_CATEGORY_ID" VARCHAR2(20 BYTE),
	"CREATE_BY" VARCHAR2(20 BYTE), 
	"CREATE_AT" DATE, 
	"LAST_UPDATED_BY" VARCHAR2(20 BYTE), 
	"LAST_UPDATED_AT" DATE, 
	"STATE" VARCHAR2(20 BYTE), 
	"SUBMIT_TOTAL" NUMBER(12,2), 
	"VERIFY_TOTAL" NUMBER(12,2), 
	"ORDER_NOTE" VARCHAR2(500 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table PURCHASE_ORDER_HISTORYS
--------------------------------------------------------

  CREATE TABLE "OA"."PURCHASE_ORDER_HISTORYS" 
   (	"ORDER_HISTORY_ID" VARCHAR2(20 BYTE), 
	"ORDER_HISTORY_DETAIL" VARCHAR2(100 BYTE),
  "ORDER_ID" VARCHAR2(20 BYTE),
  "OPERATOR_ID" VARCHAR2(20 BYTE),
  "OPERATION_DETAIL" VARCHAR2(40 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table PURCHASE_ORDER_LINES
--------------------------------------------------------

  CREATE TABLE "OA"."PURCHASE_ORDER_LINES" 
   (	"ORDER_LINE_ID" VARCHAR2(20 BYTE), 
  "ORDER_ID" VARCHAR2(20 BYTE), 
	"ITEM_ID" VARCHAR2(20 BYTE), 
	"ITEM_DESCRIPTION" VARCHAR2(200 BYTE), 
	"SUBMIT_QUANTITY" NUMBER, 
	"SUBMIT_UNIT" VARCHAR2(20 BYTE), 
	"SUBMIT_PRICE" NUMBER(12,2), 
	"SUBMIT_TOTAL" NUMBER(12,2), 
	"EXPECTED_DATE" DATE, 
	"SUBMIT_NOTE" VARCHAR2(500 BYTE), 
	"STATE" VARCHAR2(20 BYTE), 
	"PURCHASE_QUANTITY" NUMBER, 
	"ACTUAL_PRICE" NUMBER(12,2), 
	"ACTUAL_TOTAL" NUMBER(12,2), 
	"VERIFY_NOTE" VARCHAR2(500 BYTE), 
	"RECEIVE_QUANTITY" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table REPAIR_CALLS
--------------------------------------------------------

  CREATE TABLE "OA"."REPAIR_CALLS" 
   (	"CALL_ID" VARCHAR2(20 BYTE), 
	"CALL_READABLE_ID" VARCHAR2(30 BYTE), 
	"CALLER_ID" VARCHAR2(20 BYTE), 
	"CALLEE_ID" VARCHAR2(20 BYTE), 
	"LOCATION_ID" VARCHAR2(20 BYTE), 
	"LOCATION_DETAIL" VARCHAR2(200 BYTE), 
	"CREATE_BY" VARCHAR2(20 BYTE), 
	"CREATE_AT" DATE, 
	"LAST_UPDATED_BY" VARCHAR2(20 BYTE), 
	"LAST_UPDATED_AT" DATE, 
	"STATE" VARCHAR2(20 BYTE), 
	"CALL_RESULT" VARCHAR2(20 BYTE), 
	"CALL_RESULT_DETAIL" VARCHAR2(500 BYTE), 
	"CALL_EVAL" VARCHAR2(20 BYTE), 
	"CALL_EVAL_DETAIL" VARCHAR2(20 BYTE), 
	"REASON_LEVEL_1" VARCHAR2(40 BYTE), 
	"REASON_LEVEL_2" VARCHAR2(40 BYTE), 
	"REASON_LEVEL_3" VARCHAR2(40 BYTE), 
	"REASON_DETAIL" VARCHAR2(500 BYTE)
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
--------------------------------------------------------
--  DDL for Table TASKS
--------------------------------------------------------

  CREATE TABLE "OA"."TASKS" 
   (	"ID" VARCHAR2(20 BYTE), 
	"TITLE" VARCHAR2(500 BYTE), 
	"ASSIGNED_DATE" DATE, 
	"STATE" VARCHAR2(20 BYTE), 
	"CONTEXT_OBJECT_TYPE" VARCHAR2(20 BYTE), 
	"CONTEXT_OBJECT_ID" VARCHAR2(20 BYTE), 
	"ASSIGNEE_ROLE_ID" VARCHAR2(20 BYTE), 
	"COMPLETION_DATE" DATE, 
	"EXECUTOR_ID" VARCHAR2(20 BYTE), 
	"EXECUTOR_DISPLAY_NAME" VARCHAR2(20 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table VEHICLE_CALENDAR
--------------------------------------------------------

  CREATE TABLE "OA"."VEHICLE_CALENDAR" 
   (	"ID" VARCHAR2(20 BYTE), 
	"USER_ID" VARCHAR2(20 BYTE), 
	"USER_DISPLAY_NAME" VARCHAR2(20 BYTE), 
	"VEHICLE_NAME" VARCHAR2(40 BYTE), 
	"VEHICLE_ID" VARCHAR2(20 BYTE), 
	"TITLE" VARCHAR2(20 BYTE), 
	"CONTACT_ID" VARCHAR2(20 BYTE), 
	"CONTACT_NAME" VARCHAR2(20 BYTE), 
	"CONTACT_PHONE" VARCHAR2(20 BYTE), 
	"STATE" VARCHAR2(20 BYTE), 
	"RETURN_TRIP" VARCHAR2(20 BYTE), 
	"START_TIME" TIMESTAMP (6), 
	"END_TIME" TIMESTAMP (6), 
	"RETURN_START_TIME" TIMESTAMP (6), 
	"RETURN_END_TIME" TIMESTAMP (6), 
	"TRIP_START" VARCHAR2(200 BYTE), 
	"TRIP_DEST" VARCHAR2(200 BYTE), 
	"RETURN_TRIP_START" VARCHAR2(200 BYTE), 
	"RETURN_TRIP_END" VARCHAR2(200 BYTE), 
	"NUM_OF_PEOPLE" NUMBER, 
	"COMMENTS" VARCHAR2(400 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Index VEHICLE_ALENDAR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."VEHICLE_ALENDAR_PK" ON "OA"."VEHICLE_CALENDAR" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index DEPARTMENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."DEPARTMENT_PK" ON "OA"."DEPARTMENTS" ("DEPT_ID") 
  ;
--------------------------------------------------------
--  DDL for Index TASKS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."TASKS_PK" ON "OA"."TASKS" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index CLASSROOM_BATCH_RESERVATI_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."CLASSROOM_BATCH_RESERVATI_PK" ON "OA"."CLASSROOM_BATCH_RESERVATION" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index LOCATIONS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."LOCATIONS_PK" ON "OA"."LOCATIONS" ("LOCATION_ID") 
  ;
--------------------------------------------------------
--  DDL for Index HELPDESK_CALLS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."HELPDESK_CALLS_PK" ON "OA"."HELPDESK_CALLS" ("CALL_ID") 
  ;
--------------------------------------------------------
--  DDL for Index PS_TXN_IDX
--------------------------------------------------------

  CREATE INDEX "OA"."PS_TXN_IDX" ON "OA"."PS_TXN" ("COLLID", "ID") REVERSE 
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
--  DDL for Index EMPLOYEES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."EMPLOYEES_PK" ON "OA"."EMPLOYEES" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index CLASSROOM_BATCH_RESERVATI_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."CLASSROOM_BATCH_RESERVATI_UK1" ON "OA"."CLASSROOM_BATCH_RESERVATION" ("BATCH_NO") 
  ;
--------------------------------------------------------
--  DDL for Index PURCHASE_ORDERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."PURCHASE_ORDERS_PK" ON "OA"."PURCHASE_ORDERS" ("ORDER_ID") 
  ;
--------------------------------------------------------
--  DDL for Index ROLE_USERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."ROLE_USERS_PK" ON "OA"."ROLE_USERS" ("ROLE_ID", "USER_ID") 
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
--  DDL for Index PURCHASE_ORDER_ITEMS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."PURCHASE_ORDER_ITEMS_PK" ON "OA"."PURCHASE_ORDER_LINES" ("ORDER_LINE_ID") 
  ;
--------------------------------------------------------
--  DDL for Index REPAIR_CALLS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."REPAIR_CALLS_PK" ON "OA"."REPAIR_CALLS" ("CALL_ID") 
  ;
--------------------------------------------------------
--  DDL for Index ROLES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."ROLES_PK" ON "OA"."ROLES" ("ROLE_ID") 
  ;
--------------------------------------------------------
--  DDL for Index MENUS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."MENUS_PK" ON "OA"."MENUS" ("MENU_ID") 
  ;
--------------------------------------------------------
--  DDL for Index PURCHASE_ORDER_HISTORYS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."PURCHASE_ORDER_HISTORYS_PK" ON "OA"."PURCHASE_ORDER_HISTORYS" ("ORDER_HISTORY_ID") 
  ;
--------------------------------------------------------
--  DDL for Index CLASSROOM_CALENDAR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OA"."CLASSROOM_CALENDAR_PK" ON "OA"."CLASSROOM_CALENDAR" ("ID") 
  ;
--------------------------------------------------------
--  Constraints for Table DEPARTMENTS
--------------------------------------------------------

  ALTER TABLE "OA"."DEPARTMENTS" MODIFY ("DEPT_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."DEPARTMENTS" ADD CONSTRAINT "DEPARTMENT_PK" PRIMARY KEY ("DEPT_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table ANNOUNCEMENTS
--------------------------------------------------------

  ALTER TABLE "OA"."ANNOUNCEMENTS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."ANNOUNCEMENTS" ADD CONSTRAINT "ANNOUNCEMENTS_PK" PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table NOTIFICATIONS
--------------------------------------------------------

  ALTER TABLE "OA"."NOTIFICATIONS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."NOTIFICATIONS" ADD CONSTRAINT "NOTIFICATIONS_PK" PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table EMPLOYEES
--------------------------------------------------------

  ALTER TABLE "OA"."EMPLOYEES" MODIFY ("USER_NAME" NOT NULL ENABLE);
  ALTER TABLE "OA"."EMPLOYEES" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "OA"."EMPLOYEES" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."EMPLOYEES" ADD CONSTRAINT "EMPLOYEES_PK" PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table LOCATIONS
--------------------------------------------------------

  ALTER TABLE "OA"."LOCATIONS" MODIFY ("LOCATION_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."LOCATIONS" ADD CONSTRAINT "LOCATIONS_PK" PRIMARY KEY ("LOCATION_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table PURCHASE_ORDER_LINES
--------------------------------------------------------

  ALTER TABLE "OA"."PURCHASE_ORDER_LINES" MODIFY ("ORDER_LINE_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."PURCHASE_ORDER_LINES" ADD CONSTRAINT "PURCHASE_ORDER_ITEMS_PK" PRIMARY KEY ("ORDER_LINE_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table PURCHASE_ORDER_HISTORYS
--------------------------------------------------------

  ALTER TABLE "OA"."PURCHASE_ORDER_HISTORYS" MODIFY ("ORDER_HISTORY_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."PURCHASE_ORDER_HISTORYS" ADD CONSTRAINT "PURCHASE_ORDER_HISTORYS_PK" PRIMARY KEY ("ORDER_HISTORY_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table TASKS
--------------------------------------------------------

  ALTER TABLE "OA"."TASKS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."TASKS" ADD CONSTRAINT "TASKS_PK" PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table CLASSROOM_BATCH_RESERVATION
--------------------------------------------------------

  ALTER TABLE "OA"."CLASSROOM_BATCH_RESERVATION" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."CLASSROOM_BATCH_RESERVATION" ADD CONSTRAINT "CLASSROOM_BATCH_RESERVATI_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "OA"."CLASSROOM_BATCH_RESERVATION" ADD CONSTRAINT "CLASSROOM_BATCH_RESERVATI_UK1" UNIQUE ("BATCH_NO") ENABLE;
--------------------------------------------------------
--  Constraints for Table ROLES
--------------------------------------------------------

  ALTER TABLE "OA"."ROLES" MODIFY ("ROLE_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."ROLES" ADD CONSTRAINT "ROLES_PK" PRIMARY KEY ("ROLE_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table LOVS
--------------------------------------------------------

  ALTER TABLE "OA"."LOVS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."LOVS" ADD CONSTRAINT "LOVS_PK" PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table HELPDESK_CALLS
--------------------------------------------------------

  ALTER TABLE "OA"."HELPDESK_CALLS" MODIFY ("CALL_READABLE_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."HELPDESK_CALLS" MODIFY ("CALL_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."HELPDESK_CALLS" ADD CONSTRAINT "HELPDESK_CALLS_PK" PRIMARY KEY ("CALL_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table MENUS
--------------------------------------------------------

  ALTER TABLE "OA"."MENUS" MODIFY ("MENU_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."MENUS" ADD CONSTRAINT "MENUS_PK" PRIMARY KEY ("MENU_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table PURCHASE_ORDERS
--------------------------------------------------------

  ALTER TABLE "OA"."PURCHASE_ORDERS" MODIFY ("ORDER_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."PURCHASE_ORDERS" MODIFY ("ORDER_READABLE_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."PURCHASE_ORDERS" ADD CONSTRAINT "PURCHASE_ORDERS_PK" PRIMARY KEY ("ORDER_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table REPAIR_CALLS
--------------------------------------------------------

  ALTER TABLE "OA"."REPAIR_CALLS" ADD CONSTRAINT "REPAIR_CALLS_PK" PRIMARY KEY ("CALL_ID") ENABLE;
  ALTER TABLE "OA"."REPAIR_CALLS" MODIFY ("CALL_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."REPAIR_CALLS" MODIFY ("CALL_READABLE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ROLE_USERS
--------------------------------------------------------

  ALTER TABLE "OA"."ROLE_USERS" MODIFY ("ROLE_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."ROLE_USERS" MODIFY ("USER_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."ROLE_USERS" ADD CONSTRAINT "ROLE_USERS_PK" PRIMARY KEY ("ROLE_ID", "USER_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table ROLE_MENUS
--------------------------------------------------------

  ALTER TABLE "OA"."ROLE_MENUS" MODIFY ("ROLE_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."ROLE_MENUS" MODIFY ("MENU_ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."ROLE_MENUS" ADD CONSTRAINT "ROLE_MENUS_PK" PRIMARY KEY ("ROLE_ID", "MENU_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table CLASSROOM_CALENDAR
--------------------------------------------------------

  ALTER TABLE "OA"."CLASSROOM_CALENDAR" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "OA"."CLASSROOM_CALENDAR" ADD CONSTRAINT "CLASSROOM_CALENDAR_PK" PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table VEHICLE_CALENDAR
--------------------------------------------------------

  ALTER TABLE "OA"."VEHICLE_CALENDAR" ADD CONSTRAINT "VEHICLE_ALENDAR_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "OA"."VEHICLE_CALENDAR" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PS_TXN
--------------------------------------------------------

  ALTER TABLE "OA"."PS_TXN" ADD CONSTRAINT "PS_TXN_PK" PRIMARY KEY ("COLLID", "ID") ENABLE;
--------------------------------------------------------
--  DDL for Trigger ANNOUNCEMENTS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."ANNOUNCEMENTS_TRG" BEFORE INSERT ON "OA"."ANNOUNCEMENTS"
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
--  DDL for Trigger CLASSROOM_BATCH_RESERVATI_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."CLASSROOM_BATCH_RESERVATI_TRG" BEFORE INSERT ON "OA"."CLASSROOM_BATCH_RESERVATION" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.ID IS NULL THEN
      SELECT CLASSROOM_BATCH_RESERVATI_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."CLASSROOM_BATCH_RESERVATI_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CLASSROOM_CALENDAR_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."CLASSROOM_CALENDAR_TRG" BEFORE INSERT ON "OA"."CLASSROOM_CALENDAR" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.ID IS NULL THEN
      SELECT CLASSROOM_CALENDAR_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."CLASSROOM_CALENDAR_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CONF_ROOM_CALENDAR_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."CONF_ROOM_CALENDAR_TRG" BEFORE INSERT ON "OA"."CONF_ROOM_CALENDAR" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.ID IS NULL THEN
      SELECT CONF_ROOM_CALENDAR_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."CONF_ROOM_CALENDAR_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger DEPARTMENT_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."DEPARTMENT_TRG" BEFORE INSERT ON "OA"."DEPARTMENTS" 
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

  CREATE OR REPLACE TRIGGER "OA"."EMPLOYEES_TRG" BEFORE INSERT ON "OA"."EMPLOYEES" 
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
--  DDL for Trigger HELPDESK_CALLS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."HELPDESK_CALLS_TRG" BEFORE INSERT ON "OA"."HELPDESK_CALLS"
FOR EACH ROW
declare
  seqid varchar2(20);
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.CALL_ID IS NULL THEN
      SELECT HELPDESK_CALLS_SEQ.NEXTVAL INTO seqid FROM DUAL;
      SELECT seqid INTO :NEW.CALL_ID FROM DUAL;
      SELECT 'BX-' || seqid INTO :NEW.CALL_READABLE_ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."HELPDESK_CALLS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger LOCATIONS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."LOCATIONS_TRG" BEFORE INSERT ON "OA"."LOCATIONS" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.LOCATION_ID IS NULL THEN
      SELECT 'LOC-'||LOCATIONS_SEQ.NEXTVAL INTO :NEW.LOCATION_ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."LOCATIONS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger LOVS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."LOVS_TRG" BEFORE INSERT ON "OA"."LOVS" 
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

  CREATE OR REPLACE TRIGGER "OA"."NOTIFICATIONS_TRG" BEFORE INSERT ON "OA"."NOTIFICATIONS" 
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
--  DDL for Trigger PURCHASE_ORDERS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."PURCHASE_ORDERS_TRG" BEFORE INSERT ON "OA"."PURCHASE_ORDERS"
FOR EACH ROW
declare
  seqid varchar2(20);
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.ORDER_ID IS NULL THEN
      SELECT PURCHASE_ORDERS_SEQ.NEXTVAL INTO seqid FROM DUAL;
      SELECT seqid INTO :NEW.ORDER_ID FROM DUAL;
      SELECT 'CG-' || seqid INTO :NEW.ORDER_READABLE_ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."PURCHASE_ORDERS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PURCHASE_ORDER_HISTORYS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."PURCHASE_ORDER_HISTORYS_TRG" BEFORE INSERT ON "OA"."PURCHASE_ORDER_HISTORYS"
FOR EACH ROW
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.ORDER_HISTORY_ID IS NULL THEN
      SELECT PURCHASE_ORDER_HISTORYS_SEQ.NEXTVAL INTO :NEW.ORDER_HISTORY_ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."PURCHASE_ORDER_HISTORYS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PURCHASE_ORDER_LINES_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."PURCHASE_ORDER_LINES_TRG" BEFORE INSERT ON "OA"."PURCHASE_ORDER_LINES"
FOR EACH ROW
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.ORDER_LINE_ID IS NULL THEN
      SELECT PURCHASE_ORDER_LINES_SEQ.NEXTVAL INTO :NEW.ORDER_LINE_ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."PURCHASE_ORDER_LINES_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger REPAIR_CALLS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."REPAIR_CALLS_TRG" BEFORE INSERT ON "OA"."REPAIR_CALLS"
FOR EACH ROW
declare
  seqid varchar2(20);
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.CALL_ID IS NULL THEN
      SELECT REPAIR_CALLS_SEQ.NEXTVAL INTO seqid FROM DUAL;
      SELECT seqid INTO :NEW.CALL_ID FROM DUAL;
      SELECT 'BX-' || seqid INTO :NEW.CALL_READABLE_ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."REPAIR_CALLS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger ROLES_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."ROLES_TRG" BEFORE INSERT ON "OA"."ROLES" 
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
--------------------------------------------------------
--  DDL for Trigger TASKS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."TASKS_TRG" BEFORE INSERT ON "OA"."TASKS" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.ID IS NULL THEN
      SELECT TASKS_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."TASKS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger VEHICLE_ALENDAR_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OA"."VEHICLE_ALENDAR_TRG" BEFORE INSERT ON "OA"."VEHICLE_CALENDAR" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF :NEW.ID IS NULL THEN
      SELECT VEHICLE_ALENDAR_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "OA"."VEHICLE_ALENDAR_TRG" ENABLE;

REM INSERTING into OA.ANNOUNCEMENTS
REM INSERTING into OA.CLASSROOM_BATCH_RESERVATION
Insert into OA.CLASSROOM_BATCH_RESERVATION (ID,BATCH_NO,ACT_TITLE,ACT_START_TIME,ACT_END_TIME,USER_ID,USER_DISPLAY_NAME,NUM_OF_PEOPLE,CLASSROOM,COMMENTS,BATCH_EFFECTIVE_DATE,BATCH_EXPIRE_DATE,RECURRENCE_TYPE,FST_WK_MON,FST_WK_TUE,FST_WK_WED,FST_WK_THU,FST_WK_FRI,FST_WK_SAT,FST_WK_SUN,SND_WK_MON,SND_WK_TUE,SND_WK_WED,SND_WK_THU,SND_WK_FRI,SND_WK_SAT,SND_WK_SUN,LOCATION_ID,LOCATION_NAME,CLASSROOM_ID,ALL_DAY) values ('21','CLSRM-BATCH-00008','asdfsadf',to_timestamp('01-JAN-70 10.35.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('01-JAN-70 10.35.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'1','徐晋',12,'教室101','asdf',to_date('30-DEC-12','DD-MON-RR'),to_date('02-FEB-13','DD-MON-RR'),'WEEKLY','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','LOC-1','丽园','1','ALLDAY');
REM INSERTING into OA.CLASSROOM_CALENDAR
Insert into OA.CLASSROOM_CALENDAR (ID,ACT_TITLE,ACT_START_TIME,ACT_END_TIME,USER_ID,USER_DISPLAY_NAME,NUM_OF_PEOPLE,CLASSROOM,COMMENTS,CREATED_AT,BATCH_ID,BATCH_NO,LOCATION_ID,LOCATION_NAME,CLASSROOM_ID,ALL_DAY) values ('301','fasdfasdf',to_timestamp('29-JAN-13 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('29-JAN-13 11.59.59.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'gene','徐晋',12,'教室102',null,to_date('27-JAN-13','DD-MON-RR'),null,null,'LOC-2','自忠','2','ALLDAY');
Insert into OA.CLASSROOM_CALENDAR (ID,ACT_TITLE,ACT_START_TIME,ACT_END_TIME,USER_ID,USER_DISPLAY_NAME,NUM_OF_PEOPLE,CLASSROOM,COMMENTS,CREATED_AT,BATCH_ID,BATCH_NO,LOCATION_ID,LOCATION_NAME,CLASSROOM_ID,ALL_DAY) values ('302','asdfsadf',to_timestamp('31-DEC-12 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('31-DEC-12 11.59.59.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'gene','徐晋',12,'教室101','asdf',to_date('27-JAN-13','DD-MON-RR'),null,'CLSRM-BATCH-00008','LOC-1','丽园','1','ALLDAY');
Insert into OA.CLASSROOM_CALENDAR (ID,ACT_TITLE,ACT_START_TIME,ACT_END_TIME,USER_ID,USER_DISPLAY_NAME,NUM_OF_PEOPLE,CLASSROOM,COMMENTS,CREATED_AT,BATCH_ID,BATCH_NO,LOCATION_ID,LOCATION_NAME,CLASSROOM_ID,ALL_DAY) values ('303','asdfsadf',to_timestamp('07-JAN-13 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('07-JAN-13 11.59.59.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'gene','徐晋',12,'教室101','asdf',to_date('27-JAN-13','DD-MON-RR'),null,'CLSRM-BATCH-00008','LOC-1','丽园','1','ALLDAY');
Insert into OA.CLASSROOM_CALENDAR (ID,ACT_TITLE,ACT_START_TIME,ACT_END_TIME,USER_ID,USER_DISPLAY_NAME,NUM_OF_PEOPLE,CLASSROOM,COMMENTS,CREATED_AT,BATCH_ID,BATCH_NO,LOCATION_ID,LOCATION_NAME,CLASSROOM_ID,ALL_DAY) values ('304','asdfsadf',to_timestamp('14-JAN-13 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('14-JAN-13 11.59.59.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'gene','徐晋',12,'教室101','asdf',to_date('27-JAN-13','DD-MON-RR'),null,'CLSRM-BATCH-00008','LOC-1','丽园','1','ALLDAY');
Insert into OA.CLASSROOM_CALENDAR (ID,ACT_TITLE,ACT_START_TIME,ACT_END_TIME,USER_ID,USER_DISPLAY_NAME,NUM_OF_PEOPLE,CLASSROOM,COMMENTS,CREATED_AT,BATCH_ID,BATCH_NO,LOCATION_ID,LOCATION_NAME,CLASSROOM_ID,ALL_DAY) values ('305','asdfsadf',to_timestamp('21-JAN-13 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('21-JAN-13 11.59.59.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'gene','徐晋',12,'教室101','asdf',to_date('27-JAN-13','DD-MON-RR'),null,'CLSRM-BATCH-00008','LOC-1','丽园','1','ALLDAY');
Insert into OA.CLASSROOM_CALENDAR (ID,ACT_TITLE,ACT_START_TIME,ACT_END_TIME,USER_ID,USER_DISPLAY_NAME,NUM_OF_PEOPLE,CLASSROOM,COMMENTS,CREATED_AT,BATCH_ID,BATCH_NO,LOCATION_ID,LOCATION_NAME,CLASSROOM_ID,ALL_DAY) values ('306','asdfsadf',to_timestamp('29-JAN-13 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('29-JAN-13 11.59.59.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'gene','徐晋',12,'教室101','asdf',to_date('27-JAN-13','DD-MON-RR'),null,'CLSRM-BATCH-00008','LOC-1','丽园','1','ALLDAY');
Insert into OA.CLASSROOM_CALENDAR (ID,ACT_TITLE,ACT_START_TIME,ACT_END_TIME,USER_ID,USER_DISPLAY_NAME,NUM_OF_PEOPLE,CLASSROOM,COMMENTS,CREATED_AT,BATCH_ID,BATCH_NO,LOCATION_ID,LOCATION_NAME,CLASSROOM_ID,ALL_DAY) values ('307','asdf',to_timestamp('30-JAN-13 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('30-JAN-13 11.59.59.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'gene','徐晋',12,'教室102',null,to_date('27-JAN-13','DD-MON-RR'),null,null,'LOC-2','自忠','2','ALLDAY');
Insert into OA.CLASSROOM_CALENDAR (ID,ACT_TITLE,ACT_START_TIME,ACT_END_TIME,USER_ID,USER_DISPLAY_NAME,NUM_OF_PEOPLE,CLASSROOM,COMMENTS,CREATED_AT,BATCH_ID,BATCH_NO,LOCATION_ID,LOCATION_NAME,CLASSROOM_ID,ALL_DAY) values ('308','uuuuuuuuuuu',to_timestamp('30-JAN-13 09.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('30-JAN-13 02.00.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'gene','徐晋',22,'教室101',null,to_date('29-JAN-13','DD-MON-RR'),null,null,'LOC-1','丽园','1','TIME');
Insert into OA.CLASSROOM_CALENDAR (ID,ACT_TITLE,ACT_START_TIME,ACT_END_TIME,USER_ID,USER_DISPLAY_NAME,NUM_OF_PEOPLE,CLASSROOM,COMMENTS,CREATED_AT,BATCH_ID,BATCH_NO,LOCATION_ID,LOCATION_NAME,CLASSROOM_ID,ALL_DAY) values ('309','pppppppppppp',to_timestamp('01-FEB-13 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('01-FEB-13 11.59.59.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'gene','徐晋',2,'教室101',null,to_date('29-JAN-13','DD-MON-RR'),null,null,'LOC-1','丽园','1','ALLDAY');
REM INSERTING into OA.CONF_ROOM_CALENDAR
Insert into OA.CONF_ROOM_CALENDAR (ID,USER_ID,USER_DISPLAY_NAME,LOCATION_ID,LOCATION_NAME,MEETING_ROOM,MEETING_ROOM_ID,TITLE,PARTICIPANTS,START_TIME,END_TIME,COMMENTS,NEED_PROJECTOR,NEED_LOUDSPEAKER,NEED_BEVERAGE,NEED_FRUITS,NEED_SNACKS,SNACK_LEVEL,NUM_OF_PEOPLE,STATE,PR_ID,ALLDAY) values ('4','1','徐晋','LOC-1','丽园','2001','22','RRRRRRRRRRRRR','XXXXXXXXXXXXXXX',to_timestamp('27-JAN-13 12.30.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('28-JAN-13 01.00.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'DSF','N','N','N','N','N',null,10,'已拒绝',null,'TIME');
Insert into OA.CONF_ROOM_CALENDAR (ID,USER_ID,USER_DISPLAY_NAME,LOCATION_ID,LOCATION_NAME,MEETING_ROOM,MEETING_ROOM_ID,TITLE,PARTICIPANTS,START_TIME,END_TIME,COMMENTS,NEED_PROJECTOR,NEED_LOUDSPEAKER,NEED_BEVERAGE,NEED_FRUITS,NEED_SNACKS,SNACK_LEVEL,NUM_OF_PEOPLE,STATE,PR_ID,ALLDAY) values ('3','1','徐晋','LOC-1','丽园','1001','21','yyyyyyy','iiiiiiiiiiiii',to_timestamp('27-JAN-13 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-JAN-13 11.59.59.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'fasdfasdf','N','N','Y','N','N','10',10,'已审核',null,'ALLDAY');
Insert into OA.CONF_ROOM_CALENDAR (ID,USER_ID,USER_DISPLAY_NAME,LOCATION_ID,LOCATION_NAME,MEETING_ROOM,MEETING_ROOM_ID,TITLE,PARTICIPANTS,START_TIME,END_TIME,COMMENTS,NEED_PROJECTOR,NEED_LOUDSPEAKER,NEED_BEVERAGE,NEED_FRUITS,NEED_SNACKS,SNACK_LEVEL,NUM_OF_PEOPLE,STATE,PR_ID,ALLDAY) values ('5','1','徐晋','LOC-1','丽园','2001','22','yyyyyyy','wwwwwwwwwww',to_timestamp('28-JAN-13 09.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('28-JAN-13 01.30.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),null,'N','N','N','Y','N',null,12,'已审核',null,'TIME');
Insert into OA.CONF_ROOM_CALENDAR (ID,USER_ID,USER_DISPLAY_NAME,LOCATION_ID,LOCATION_NAME,MEETING_ROOM,MEETING_ROOM_ID,TITLE,PARTICIPANTS,START_TIME,END_TIME,COMMENTS,NEED_PROJECTOR,NEED_LOUDSPEAKER,NEED_BEVERAGE,NEED_FRUITS,NEED_SNACKS,SNACK_LEVEL,NUM_OF_PEOPLE,STATE,PR_ID,ALLDAY) values ('6','3','普通用户','LOC-2','自忠','3001','20','人大代表会议','人大代表',to_timestamp('28-JAN-13 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('28-JAN-13 11.59.59.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),null,'Y','N','N','N','Y',null,2,'已取消',null,'ALLDAY');
Insert into OA.CONF_ROOM_CALENDAR (ID,USER_ID,USER_DISPLAY_NAME,LOCATION_ID,LOCATION_NAME,MEETING_ROOM,MEETING_ROOM_ID,TITLE,PARTICIPANTS,START_TIME,END_TIME,COMMENTS,NEED_PROJECTOR,NEED_LOUDSPEAKER,NEED_BEVERAGE,NEED_FRUITS,NEED_SNACKS,SNACK_LEVEL,NUM_OF_PEOPLE,STATE,PR_ID,ALLDAY) values ('7','3','普通用户','LOC-2','自忠','3001','20','政协会议','政协委员',to_timestamp('30-JAN-13 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('30-JAN-13 11.59.59.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'阿士大夫','N','N','N','Y','Y','20',20,'已审核',null,'ALLDAY');
REM INSERTING into OA.DEPARTMENTS
Insert into OA.DEPARTMENTS (DEPT_ID,DEPT_NAME,LOCATION_ID,MGR_ID,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY,MGR_NAME) values ('3','总务处',null,'1',to_date('01-FEB-13','DD-MON-RR'),'徐晋',to_date('01-FEB-13','DD-MON-RR'),'徐晋','徐晋');
Insert into OA.DEPARTMENTS (DEPT_ID,DEPT_NAME,LOCATION_ID,MGR_ID,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY,MGR_NAME) values ('4','财务部',null,'3',to_date('01-FEB-13','DD-MON-RR'),'徐晋',to_date('01-FEB-13','DD-MON-RR'),'徐晋','普通用户');
REM INSERTING into OA.EMPLOYEES
Insert into OA.EMPLOYEES (USER_NAME,PASSWORD,FIRST_NAME,LAST_NAME,GENDER,MGR_ID,DEPT_ID,EMAIL,MOBILE,OFFICE_PHONE,HOME_PHONE,FAX,LOCATION_ID,HOME_ADDRESS,OFFICE_ADDRESS,LAST_UPDATED_BY,LAST_UPDATED_AT,CREATED_BY,CREATED_AT,TITLE,ID,MGR_NAME,DEPT_NAME) values ('gene','welcome1','晋','徐','男','2','3','gene.xujin@gmail.com','18621910893','50172665','50172665','50172665',null,'浦电路305弄','丽园路11101',null,to_date('01-FEB-13','DD-MON-RR'),null,null,'管理员','1','总务主任','总务处');
Insert into OA.EMPLOYEES (USER_NAME,PASSWORD,FIRST_NAME,LAST_NAME,GENDER,MGR_ID,DEPT_ID,EMAIL,MOBILE,OFFICE_PHONE,HOME_PHONE,FAX,LOCATION_ID,HOME_ADDRESS,OFFICE_ADDRESS,LAST_UPDATED_BY,LAST_UPDATED_AT,CREATED_BY,CREATED_AT,TITLE,ID,MGR_NAME,DEPT_NAME) values ('pairliu','welcome1','主任','总务',null,'23','4',null,'122222',null,null,null,null,null,null,null,to_date('01-FEB-13','DD-MON-RR'),null,null,'总务主任','2','办公室主任','财务部');
Insert into OA.EMPLOYEES (USER_NAME,PASSWORD,FIRST_NAME,LAST_NAME,GENDER,MGR_ID,DEPT_ID,EMAIL,MOBILE,OFFICE_PHONE,HOME_PHONE,FAX,LOCATION_ID,HOME_ADDRESS,OFFICE_ADDRESS,LAST_UPDATED_BY,LAST_UPDATED_AT,CREATED_BY,CREATED_AT,TITLE,ID,MGR_NAME,DEPT_NAME) values ('normal','welcome1','用户','普通',null,'3',null,null,'18621910893',null,null,null,null,null,null,null,to_date('01-FEB-13','DD-MON-RR'),null,null,'普通用户','3','普通用户',null);
Insert into OA.EMPLOYEES (USER_NAME,PASSWORD,FIRST_NAME,LAST_NAME,GENDER,MGR_ID,DEPT_ID,EMAIL,MOBILE,OFFICE_PHONE,HOME_PHONE,FAX,LOCATION_ID,HOME_ADDRESS,OFFICE_ADDRESS,LAST_UPDATED_BY,LAST_UPDATED_AT,CREATED_BY,CREATED_AT,TITLE,ID,MGR_NAME,DEPT_NAME) values ('engineer','welcome1','维修员','李',null,'2',null,null,'111111111',null,null,null,null,null,null,null,to_date('01-FEB-13','DD-MON-RR'),null,null,'普通用户','4','总务主任',null);
Insert into OA.EMPLOYEES (USER_NAME,PASSWORD,FIRST_NAME,LAST_NAME,GENDER,MGR_ID,DEPT_ID,EMAIL,MOBILE,OFFICE_PHONE,HOME_PHONE,FAX,LOCATION_ID,HOME_ADDRESS,OFFICE_ADDRESS,LAST_UPDATED_BY,LAST_UPDATED_AT,CREATED_BY,CREATED_AT,TITLE,ID,MGR_NAME,DEPT_NAME) values ('panjie','welcome1','主任','办公室',null,null,null,null,'1232323',null,null,null,null,null,null,null,to_date('01-FEB-13','DD-MON-RR'),null,null,'办公室主任','23',null,null);
Insert into OA.EMPLOYEES (USER_NAME,PASSWORD,FIRST_NAME,LAST_NAME,GENDER,MGR_ID,DEPT_ID,EMAIL,MOBILE,OFFICE_PHONE,HOME_PHONE,FAX,LOCATION_ID,HOME_ADDRESS,OFFICE_ADDRESS,LAST_UPDATED_BY,LAST_UPDATED_AT,CREATED_BY,CREATED_AT,TITLE,ID,MGR_NAME,DEPT_NAME) values ('bumen','welcome1','主任','部门',null,null,null,null,'123123123',null,null,null,null,null,null,null,to_date('01-FEB-13','DD-MON-RR'),null,null,'部门主任','24',null,null);
REM INSERTING into OA.HELPDESK_CALLS
Insert into OA.HELPDESK_CALLS (CALL_ID,CALL_READABLE_ID,CALLER_ID,CALLEE_ID,LOCATION_ID,LOCATION_DETAIL,CREATE_BY,CREATE_AT,LAST_UPDATED_BY,LAST_UPDATED_AT,STATE,CALL_RESULT,CALL_RESULT_DETAIL,CALL_EVAL,CALL_EVAL_DETAIL,REASON_LEVEL_1,REASON_LEVEL_2,REASON_LEVEL_3,REASON_DETAIL) values ('2','BX-2','1','4','1',null,null,null,null,null,null,null,null,null,null,'6','14',null,null);
Insert into OA.HELPDESK_CALLS (CALL_ID,CALL_READABLE_ID,CALLER_ID,CALLEE_ID,LOCATION_ID,LOCATION_DETAIL,CREATE_BY,CREATE_AT,LAST_UPDATED_BY,LAST_UPDATED_AT,STATE,CALL_RESULT,CALL_RESULT_DETAIL,CALL_EVAL,CALL_EVAL_DETAIL,REASON_LEVEL_1,REASON_LEVEL_2,REASON_LEVEL_3,REASON_DETAIL) values ('3','BX-3','2','4','2',null,null,null,null,null,null,null,null,null,null,'7','9',null,null);
Insert into OA.HELPDESK_CALLS (CALL_ID,CALL_READABLE_ID,CALLER_ID,CALLEE_ID,LOCATION_ID,LOCATION_DETAIL,CREATE_BY,CREATE_AT,LAST_UPDATED_BY,LAST_UPDATED_AT,STATE,CALL_RESULT,CALL_RESULT_DETAIL,CALL_EVAL,CALL_EVAL_DETAIL,REASON_LEVEL_1,REASON_LEVEL_2,REASON_LEVEL_3,REASON_DETAIL) values ('4','BX-4','1','4','1',null,null,null,null,null,null,null,null,null,null,null,null,null,null);
Insert into OA.HELPDESK_CALLS (CALL_ID,CALL_READABLE_ID,CALLER_ID,CALLEE_ID,LOCATION_ID,LOCATION_DETAIL,CREATE_BY,CREATE_AT,LAST_UPDATED_BY,LAST_UPDATED_AT,STATE,CALL_RESULT,CALL_RESULT_DETAIL,CALL_EVAL,CALL_EVAL_DETAIL,REASON_LEVEL_1,REASON_LEVEL_2,REASON_LEVEL_3,REASON_DETAIL) values ('5','BX-5',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
REM INSERTING into OA.LOCATIONS
Insert into OA.LOCATIONS (LOCATION_ID,FAX,STREET_ADDRESS,POSTAL_CODE,LOCATION_NAME,FRONT_DESK_PHONE,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('LOC-1','12',null,null,'丽园',null,null,null,null,null);
Insert into OA.LOCATIONS (LOCATION_ID,FAX,STREET_ADDRESS,POSTAL_CODE,LOCATION_NAME,FRONT_DESK_PHONE,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('LOC-2',null,null,null,'自忠',null,null,null,null,null);
REM INSERTING into OA.LOVS
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('1','ClsRm','class room',null,'教室101',null,null,null,null,'LOC-1','12',null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('2','ClsRm',null,null,'教室102',null,null,null,null,'LOC-2','22',null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('3','ClsRm',null,null,'教室203',null,null,null,null,'LOC-1','33',null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('4','ClsRm',null,null,'教室401',null,null,null,null,'LOC-2','15',null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('5','ClsRmBatchSeq','Class Room Batch Sequence',null,'8',null,null,'徐晋',null,null,null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('20','ConfRm',null,null,'3001',null,null,null,null,null,'（50座）','LOC-2');
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('21','ConfRm',null,null,'1001',null,null,null,null,null,'（20座）','LOC-1');
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('22','ConfRm',null,null,'2001',null,null,null,null,null,'（20座）','LOC-1');
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('23','ConfRm',null,null,'4001',null,null,null,null,null,'（100座）','LOC-2');
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('6','HdRsnLv1',null,null,'网络问题',null,null,null,null,null,null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('7','HdRsnLv1',null,null,'硬件问题',null,null,null,null,null,null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('8','HdRsnLv1',null,null,'软件问题',null,null,null,null,null,null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('9','HdRsnLv2',null,'7','显示器问题',null,null,null,null,null,null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('10','HdRsnLv2',null,'7','鼠标问题',null,null,null,null,null,null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('11','HdRsnLv2',null,'8','IE问题',null,null,null,null,null,null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('12','HdRsnLv2',null,'8','Word问题',null,null,null,null,null,null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('13','HdRsnLv2',null,'6','路由器问题',null,null,null,null,null,null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('14','HdRsnLv2',null,'6','网线问题',null,null,null,null,null,null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('15','HdState',null,null,'未提交',null,null,null,null,'1',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('16','HdState',null,null,'已受理',null,null,null,null,'2',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('17','HdState',null,null,'已处理',null,null,null,null,'3',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('18','HdState',null,null,'已评价',null,null,null,null,'4',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('19','HdState',null,null,'已撤消',null,null,null,null,'5',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('24','SnackLevel',null,null,'10',null,null,null,null,'10元',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('25','SnackLevel',null,null,'20',null,null,null,null,'20元',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('26','SnackLevel',null,null,'30',null,null,null,null,'30元',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('27','SnackLevel',null,null,'40',null,null,null,null,'40元',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('28','Vehicle',null,null,'沪A88888',null,null,null,null,'帕萨特',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('29','Vehicle',null,null,'沪A66666',null,null,null,null,'别克商务',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('30','Vehicle',null,null,'沪A99999',null,null,null,null,'大巴',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('31','Vehicle',null,null,'沪A18188',null,null,null,null,'奥迪A8',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('32','PoLineState',null,null,'未提交',null,null,null,null,'1',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('33','PoLineState',null,null,'待审核',null,null,null,null,'2',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('34','PoLineState',null,null,'待审批',null,null,null,null,'3',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('35','PoLineState',null,null,'执行中',null,null,null,null,'4',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('36','PoLineState',null,null,'已完成',null,null,null,null,'5',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('37','PoLineState',null,null,'已撤消',null,null,null,null,'6',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('38','PoState',null,null,'未提交',null,null,null,null,'1',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('39','PoState',null,null,'待审核',null,null,null,null,'2',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('40','PoState',null,null,'待审批',null,null,null,null,'3',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('41','PoState',null,null,'被拒绝',null,null,null,null,'4',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('42','PoState',null,null,'执行中',null,null,null,null,'5',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('43','PoState',null,null,'已完成',null,null,null,null,'6',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('44','PoState',null,null,'已撤消',null,null,null,null,'7',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('45','ItemCatg',null,null,'计算机设备',null,null,null,null,'IC_1',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('46','ItemCatg',null,null,'办公设备',null,null,null,null,'IC_2',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('47','Item',null,'IC_1','笔记本电脑',null,null,null,null,'I_1',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('48','Item',null,'IC_1','鼠标',null,null,null,null,'I_2',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('49','Item',null,'IC_1','移动硬盘',null,null,null,null,'I_3',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('50','Item',null,'IC_2','钢笔',null,null,null,null,'I_4',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('51','Item',null,'IC_2','圆珠笔',null,null,null,null,'I_5',null,null);
Insert into OA.LOVS (ID,CODE,LOV_DESC,PARENT_CODE,VALUE,CREATED_AT,CREATED_BY,LAST_UPDATED_BY,LAST_UPDATED_AT,FLEX_COL1,FLEX_COL2,FLEX_COL3) values ('52','Item',null,'IC_2','铅笔',null,null,null,null,'I_6',null,null);

REM INSERTING into OA.MENUS
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_PUR_MY_PR','我的采购申请','/WEB-INF/flows/po/MyPo.xml#MyPo','我的采购申请','PUR',null,'SYS','/images/icons/user.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_PUR_APPROVE','审批','/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf','审批','PUR',null,'SYS','/images/icons/check.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_PUR_REVIEW','审核','/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf','审核','PUR',null,'SYS','/images/icons/tasks.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_PUR_RECEIVE','收货','/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf','收货','PUR',null,'SYS','/images/icons/package.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_PUR_PR','采购订单','/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf','采购订单','PUR',null,'SYS','/images/icons/log.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_HD_MY_REQ','我的报修','/WEB-INF/flows/helpdesk/MyHdCall.xml#MyHdCall','我的报修','HD',null,'SYS','/images/icons/communicate.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_HD_REQ','报修单','/WEB-INF/flows/helpdesk/CreateHdCall.xml#CreateHdCall','报修单','HD',null,'SYS','/images/icons/constructor.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_HD_PROC','报修处理','/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf','报修处理','HD',null,'SYS','/images/icons/show_failures.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CLSRM_CAL','教室日历','/WEB-INF/flows/clsrm/ClsRmCalendar.xml#ClsRmCalendar','教室日历','CLSRM',null,'SYS','/images/icons/chooseDate.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CLSRM_BATCH','批量预定','/WEB-INF/flows/clsrm/batch-reservation-btf.xml#batch-reservation-btf','批量预定','CLSRM',null,'SYS','/images/icons/group.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CONFRM_CAL','会议室日历','/WEB-INF/flows/confRm/confRm-calendar-btf.xml#confRm-calendar-btf','会议室日历','CONFRM',null,'SYS','/images/icons/chooseDate.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CONFRM_REQ','会议室预订','/WEB-INF/flows/confRm/confRm-application-btf.xml#confRm-application-btf','会议室预定','CONFRM',null,'SYS','/images/icons/team.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CONFRM_APPROVE','审核','/WEB-INF/flows/confRm/confRm-approval-btf.xml#confRm-approval-btf','审核','CONFRM',null,'SYS','/images/icons/tasks.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CAR_CAL','车辆日历','/WEB-INF/flows/vehicle/vehicle-cal-btf.xml#vehicle-cal-btf','车辆日历','CAR',null,'SYS','/images/icons/chooseDate.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CAR_REQ','用车申请','/WEB-INF/flows/vehicle/vehicle-application-btf.xml#vehicle-application-btf','用车申请','CAR',null,'SYS','/images/icons/car_arrow.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CAR_APPROVE','审核','/WEB-INF/flows/vehicle/vehicle-app-approver-btf.xml#vehicle-app-approver-btf','审核','CAR',null,'SYS','/images/icons/tasks.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_MEAL_REQ','订餐','/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf','订餐','MEAL',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_ANCMT_REQ','发通知','/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf','发通知','ANCMT',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_ANCMT_LIST','通知列表','/WEB-INF/flows/welcome/welcome-btf.xml#welcome-btf','通知列表','ANCMT',null,'SYS',null);
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('SYS_CAR_TRIP_PLAN','调度','/WEB-INF/flows/vehicle/vehicle-app-planner-btf.xml#vehicle-app-planner-btf','调度','CAR',null,'SYS','/images/icons/unbounded_task_flow.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('ADMIN_DEPT','部门','/WEB-INF/flows/admin/departments-btf.xml#departments-btf','部门','DEPT',null,'ADMIN','/images/icons/node_role.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('ADMIN_EMP','员工','/WEB-INF/flows/admin/employees-btf.xml#employees-btf','员工','EMP',null,'ADMIN','/images/icons/team.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('ADMIN_MENUS','菜单','/WEB-INF/flows/admin/menus-btf.xml#menus-btf','菜单','MENU',null,'ADMIN','/images/icons/tree.png');
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL) values ('ADMIN_ROLE','角色','/WEB-INF/flows/admin/roles-btf.xml#roles-btf','角色','ROLE',null,'ADMIN','/images/icons/actor.png');
REM INSERTING into OA.NOTIFICATIONS
REM INSERTING into OA.PS_TXN
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,1,to_date('13-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,101,to_date('15-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,1151,to_date('19-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,1152,to_date('19-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,1251,to_date('20-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,1252,to_date('20-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,1351,to_date('20-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,1352,to_date('20-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,1451,to_date('20-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,2501,to_date('25-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,3551,to_date('26-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,3651,to_date('26-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,3751,to_date('26-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,3752,to_date('26-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,3753,to_date('26-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,3754,to_date('26-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,3755,to_date('26-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,3756,to_date('26-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,4801,to_date('27-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,4802,to_date('27-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,5851,to_date('31-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,5852,to_date('31-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,5853,to_date('31-JAN-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,5951,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.PS_TXN (ID,PARENTID,COLLID,CREATION_DATE) values (1,-1,5952,to_date('01-FEB-13','DD-MON-RR'));
REM INSERTING into OA.PURCHASE_ORDERS
REM INSERTING into OA.PURCHASE_ORDER_HISTORYS
REM INSERTING into OA.PURCHASE_ORDER_LINES
REM INSERTING into OA.REPAIR_CALLS
REM INSERTING into OA.ROLES
Insert into OA.ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('1','系统管理员','系统管理员',null,null,null,null);
Insert into OA.ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('2','采购员',null,null,null,null,null);
Insert into OA.ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('3','副院长',null,null,null,null,null);
Insert into OA.ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('4','采购部主任',null,null,null,null,null);
Insert into OA.ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('5','普通用户','普通用户',null,null,null,null);
Insert into OA.ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('6','会议室管理员','会议室管理员',null,null,null,null);
Insert into OA.ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('7','部门主任','部门主任',null,null,null,null);
Insert into OA.ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('8','办公室主任','办公室主任',null,null,null,null);
Insert into OA.ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,CREATED_AT,CREATED_BY,LAST_UPDATED_AT,LAST_UPDATED_BY) values ('9','总务处主任','总务处主任',null,null,null,null);
REM INSERTING into OA.ROLE_MENUS
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_ANCMT_LIST',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_ANCMT_REQ',null,null);
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
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('2','SYS_PUR_MY_PR',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('4','SYS_PUR_REVIEW',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('5','SYS_CLSRM_CAL',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('5','SYS_CONFRM_CAL',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('5','SYS_CONFRM_REQ',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('2','SYS_PUR_RECEIVE',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('4','SYS_CAR_CAL',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('5','SYS_CAR_CAL',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('6','SYS_CAR_CAL',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('7','SYS_CAR_CAL',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('8','SYS_CAR_CAL',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('7','SYS_CAR_REQ',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('8','SYS_CAR_APPROVE',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','ADMIN_DEPT',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','ADMIN_EMP',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','ADMIN_MENUS',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','ADMIN_ROLE',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('1','SYS_CAR_TRIP_PLAN',null,null);
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('3','SYS_PUR_APPROVE',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('3','SYS_CAR_CAL',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('3','SYS_PUR_RECEIVE',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('2','SYS_CAR_CAL',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('2','SYS_CAR_REQ',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('2','SYS_MEAL_REQ',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_CAR_CAL',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_CAR_TRIP_PLAN',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_PUR_MY_PR',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_PUR_APPROVE',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_PUR_REVIEW',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_PUR_RECEIVE',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_PUR_PR',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_HD_MY_REQ',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_HD_REQ',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_HD_PROC',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_CLSRM_CAL',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_CLSRM_BATCH',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_CONFRM_CAL',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_CONFRM_REQ',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_CONFRM_APPROVE',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_CAR_REQ',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_CAR_APPROVE',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_MEAL_REQ',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_ANCMT_REQ',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','SYS_ANCMT_LIST',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','ADMIN_DEPT',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','ADMIN_EMP',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','ADMIN_MENUS',null,to_date('01-FEB-13','DD-MON-RR'));
Insert into OA.ROLE_MENUS (ROLE_ID,MENU_ID,CREATED_BY,CREATED_AT) values ('9','ADMIN_ROLE',null,to_date('01-FEB-13','DD-MON-RR'));
REM INSERTING into OA.ROLE_USERS
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('1','1',null,null);
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('2','2',null,null);
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('3','2',null,null);
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('4','2',null,null);
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('1','2',null,null);
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('5','3',null,null);
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('9','2',null,null);
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('8','23',null,null);
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('7','24',null,null);
Insert into OA.ROLE_USERS (ROLE_ID,USER_ID,CREATED_AT,CREATED_BY) values ('8','24',null,null);
REM INSERTING into OA.TASKS
REM INSERTING into OA.VEHICLE_CALENDAR
Insert into OA.VEHICLE_CALENDAR (ID,USER_ID,USER_DISPLAY_NAME,VEHICLE_NAME,VEHICLE_ID,TITLE,CONTACT_ID,CONTACT_NAME,CONTACT_PHONE,STATE,RETURN_TRIP,START_TIME,END_TIME,RETURN_START_TIME,RETURN_END_TIME,TRIP_START,TRIP_DEST,RETURN_TRIP_START,RETURN_TRIP_END,NUM_OF_PEOPLE,COMMENTS) values ('6','24','部门主任',null,null,'eeaaaaaaaaaaaaaaaaaa','1','徐晋','18621910893','已取消','true',to_timestamp('28-FEB-13 05.42.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('28-FEB-13 10.42.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),null,null,'ggggggggggg','eeeeeeeeeeeeee',null,null,12,'uuuuuuuuuuu');
Insert into OA.VEHICLE_CALENDAR (ID,USER_ID,USER_DISPLAY_NAME,VEHICLE_NAME,VEHICLE_ID,TITLE,CONTACT_ID,CONTACT_NAME,CONTACT_PHONE,STATE,RETURN_TRIP,START_TIME,END_TIME,RETURN_START_TIME,RETURN_END_TIME,TRIP_START,TRIP_DEST,RETURN_TRIP_START,RETURN_TRIP_END,NUM_OF_PEOPLE,COMMENTS) values ('2','1','徐晋','沪A99999','30','fffffffffffff','4','李维修员','111111111','已调度','N',to_timestamp('31-JAN-13 02.27.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('31-JAN-13 03.28.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),null,null,'asdfasdf','asdfasdfs',null,null,12,null);
Insert into OA.VEHICLE_CALENDAR (ID,USER_ID,USER_DISPLAY_NAME,VEHICLE_NAME,VEHICLE_ID,TITLE,CONTACT_ID,CONTACT_NAME,CONTACT_PHONE,STATE,RETURN_TRIP,START_TIME,END_TIME,RETURN_START_TIME,RETURN_END_TIME,TRIP_START,TRIP_DEST,RETURN_TRIP_START,RETURN_TRIP_END,NUM_OF_PEOPLE,COMMENTS) values ('7','24','部门主任',null,null,'tttttttt','2','总务主任','1233','已拒绝','true',to_timestamp('01-FEB-13 05.44.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('01-FEB-13 09.44.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),null,null,'tttttttttt','tttttttttt',null,null,12,null);
Insert into OA.VEHICLE_CALENDAR (ID,USER_ID,USER_DISPLAY_NAME,VEHICLE_NAME,VEHICLE_ID,TITLE,CONTACT_ID,CONTACT_NAME,CONTACT_PHONE,STATE,RETURN_TRIP,START_TIME,END_TIME,RETURN_START_TIME,RETURN_END_TIME,TRIP_START,TRIP_DEST,RETURN_TRIP_START,RETURN_TRIP_END,NUM_OF_PEOPLE,COMMENTS) values ('8','24','部门主任',null,null,'pppppppppppp','4','李维修员','111111111','已调度','false',to_timestamp('31-JAN-13 10.45.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('31-JAN-13 11.45.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),null,null,'ppppppppppp','pppppppppppp',null,null,112,null);
Insert into OA.VEHICLE_CALENDAR (ID,USER_ID,USER_DISPLAY_NAME,VEHICLE_NAME,VEHICLE_ID,TITLE,CONTACT_ID,CONTACT_NAME,CONTACT_PHONE,STATE,RETURN_TRIP,START_TIME,END_TIME,RETURN_START_TIME,RETURN_END_TIME,TRIP_START,TRIP_DEST,RETURN_TRIP_START,RETURN_TRIP_END,NUM_OF_PEOPLE,COMMENTS) values ('5','1','徐晋','沪A88888','28','eeeeeeeeeeeeeeeee','2','总务主任','123333','已调度','Y',to_timestamp('28-JAN-13 01.26.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('28-JAN-13 05.26.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),null,null,'sdfa','asdfasdf',null,null,12,null);
Insert into OA.VEHICLE_CALENDAR (ID,USER_ID,USER_DISPLAY_NAME,VEHICLE_NAME,VEHICLE_ID,TITLE,CONTACT_ID,CONTACT_NAME,CONTACT_PHONE,STATE,RETURN_TRIP,START_TIME,END_TIME,RETURN_START_TIME,RETURN_END_TIME,TRIP_START,TRIP_DEST,RETURN_TRIP_START,RETURN_TRIP_END,NUM_OF_PEOPLE,COMMENTS) values ('9','24','部门主任','沪A88888','28','aaaaaaaaaa','24','部门主任','123','已调度','true',to_timestamp('31-JAN-13 05.46.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('31-JAN-13 06.46.00.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),null,null,'iiiiiiiiii','qqqqqqqqqqq',null,null,12,'123');


commit;
