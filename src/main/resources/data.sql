insert into user_details(id,name,birth_date)
values(10001,'Onkar',current_date());

insert into user_details(id,name,birth_date)
values(10002,'Rahul',current_date());

insert into user_details(id,name,birth_date)
values(10003,'Het',current_date());

insert into post(id,description,user_id)
values(20001,'learn Aws',10001);

insert into post(id,description,user_id)
values(20002,'Learn Spring',10001);

insert into post(id,description,user_id)
values(20003,'Learn SpringBoot',10002);

insert into post(id,description,user_id)
values(20004,'Java',10003);