-- Initialize database with som bari users
insert into bariuser
(fullname, login, password, userrole, version)
values ('Jan Schrøder Hansen', 'jsh', 'jsh', 'ADMIN', 1);

insert into bariuser
(fullname, login, password, userrole, version)
values ('Kode Kaj', 'kk', 'kk', 'DEVELOPER', 1);

insert into bariuser
(fullname, login, password, userrole, version)
values ('Tanja Kikkenborg', 'tki', 'tki', 'NORMAL', 1);