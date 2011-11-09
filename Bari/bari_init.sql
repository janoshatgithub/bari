-- Extra constrains
alter table bariuser
add constraint valid_user_roles
check (userrole in ('ADMIN', 'DEVELOPER', 'NORMAL'));

alter table bariuser
add constraint password_length_ge_3
check (length(password) >= 3);

alter table baricase
add constraint valid_type
check (type in ('ERROR', 'REQUEST'));

alter table baricase
add constraint valid_dev_stauts
check (devstatus in ('NOTSTARTED', 'STARTED', 'READYTOTEST', 'TESTED',
'INPRODUCTION'));

alter table baricase
add constraint valid_case_stauts
check (casestatus in ('NEW', 'CONSIDERING', 'APPROVED', 'REJECTED',
'DONE'));

-- Initialize database with some bari users.
insert into bariuser
(fullname, login, password, userrole, version)
values ('Jens Sne Hansen', 'jsh', 'jsh', 'ADMIN', 1);

insert into bariuser
(fullname, login, password, userrole, version)
values ('Kaj Kode Nørd', 'kkn', 'kkn', 'DEVELOPER', 1);

insert into bariuser
(fullname, login, password, userrole, version)
values ('Tanja Kageborg', 'tki', 'tki', 'NORMAL', 1);

-- Add some products and user groups
insert into product
(name, version)
values ('BaRI', 1);

insert into product
(name, version)
values ('Hibernate', 1);

insert into product
(name, version)
values ('Wicket', 1);

insert into usergroup
(bariuser_id, product_id, version)
values (1, 1, 1);

insert into usergroup
(bariuser_id, product_id, version)
values (1, 2, 1);

insert into usergroup
(bariuser_id, product_id, version)
values (1, 3, 1);

insert into usergroup
(bariuser_id, product_id, version)
values (2, 1, 1);

insert into usergroup
(bariuser_id, product_id, version)
values (2, 2, 1);

insert into usergroup
(bariuser_id, product_id, version)
values (2, 3, 1);

insert into usergroup
(bariuser_id, product_id, version)
values (3, 1, 1);

insert into usergroup
(bariuser_id, product_id, version)
values (3, 2, 1);

insert into usergroup
(bariuser_id, product_id, version)
values (3, 3, 1);
