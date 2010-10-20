
    alter table DiscussionMessage 
        drop constraint fk_baricase;

    drop table BariCase;

    drop table DiscussionMessage;

    create table BariCase (
        id bigint not null generated always as identity,
        bariUser varchar(255),
        caseStatus varchar(255),
        conclusion varchar(255),
        created timestamp,
        description varchar(255),
        devStatus varchar(255),
        finished timestamp,
        title varchar(255),
        type varchar(255),
        version integer,
        primary key (id)
    );

    create table DiscussionMessage (
        id bigint not null generated always as identity,
        bariUser varchar(255),
        created timestamp,
        message varchar(255),
        version integer,
        bariCase_id bigint,
        primary key (id)
    );

    alter table DiscussionMessage 
        add constraint fk_baricase 
        foreign key (bariCase_id) 
        references BariCase;
