   
-----VEHICLE_CALENDAR表添加三个新的字段
   ALTER TABLE "OA"."VEHICLE_CALENDAR" ADD DRIVER_ID varchar2(20);
   ALTER TABLE "OA"."VEHICLE_CALENDAR" ADD DRIVER_NAME varchar2(20);
   ALTER TABLE "OA"."VEHICLE_CALENDAR" ADD DRIVER_MOBILE varchar2(20);
   
-----添加一个新的Menu
Insert into OA.MENUS (MENU_ID,MENU_NAME,MENU_TASKFLOW_URL,MENU_DESC,MENU_CATEGORY,PARENT_MENU_ID,MENU_MASTER_CATEGORY,MENU_ICON_URL,EXPIRED,SEQ) values ('SYS_CAR_DRIVER','我的派车单','/WEB-INF/flows/vehicle/vehicle-app-driver-btf.xml#vehicle-app-driver-btf','司机派车单','CAR',null,'SYS','/images/icons/tasks.png','true','4');

---添加新的Role:司机