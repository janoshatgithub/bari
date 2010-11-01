
    alter table BariCase 
        drop constraint fk_from_baricase_to_bariuser;

    alter table DiscussionMessage 
        drop constraint fk_from_discussionmessage_to_baricase;

    alter table DiscussionMessage 
        drop constraint fk_from_discussiommessage_to_bariuser;

    drop table BariCase;

    drop table BariUser;

    drop table DiscussionMessage;

    create table BariCase (
        id bigint not null generated always as identity,
        caseStatus varchar(15) not null,
        conclusion varchar(400),
        created timestamp not null,
        description varchar(400) not null,
        devStatus varchar(15) not null,
        finished timestamp,
        title varchar(50) not null,
        type varchar(10) not null,
        version integer not null,
        bariUser_id bigint not null,
        primary key (id)
    );

    create table BariUser (
        id bigint not null generated always as identity,
        fullname varchar(50) not null,
        login varchar(20) not null,
        password varchar(20) not null,
        userRole varchar(10) not null,
        version integer not null,
        primary key (id)
    );

    create table DiscussionMessage (
        id bigint not null generated always as identity,
        created timestamp not null,
        message varchar(400) not null,
        version integer not null,
        bariCase_id bigint not null,
        bariUser_id bigint not null,
        primary key (id)
    );

    alter table BariCase 
        add constraint fk_from_baricase_to_bariuser 
        foreign key (bariUser_id) 
        references BariUser;

    alter table DiscussionMessage 
        add constraint fk_from_discussionmessage_to_baricase 
        foreign key (bariCase_id) 
        references BariCase;

    alter table DiscussionMessage 
        add constraint fk_from_discussiommessage_to_bariuser 
        foreign key (bariUser_id) 
        references BariUser;
