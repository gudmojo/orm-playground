create table DEPARTMENTS (
    ID bigint primary key,
    NAME varchar(100) not null
);

create table EMPLOYEES (
    ID bigint primary key,
    DEPARTMENT_ID bigint references DEPARTMENTS(ID),
    NAME varchar(100) not null
);
