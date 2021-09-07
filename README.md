# task1NeoSoft

**Scenario - Case #1**
Write a Java program which would randomly generate a Housie Ticket. Here are the rules of generating the ticket:
a. There are 3 rows and 9 columns in the ticket. Columns from 1.. 9 has values from 1 to 90. In the first column the values of the number are from 1..10, second column 11..20 and so on.
b. Each column has max 2 cells filled in.
c. The numbers cannot be repeated in a column.
d. Each row has max 5 cells filled in, hence only 15 cells in all.

Each matrix represents one single ticket..

The program should showcase the following:
a. No hard-coding of values, create functions which take parameters - for example 3 rows and 9 columns should not be hard-coded, should be taken as input.
b. Exception Handling
c. Best practices of Java
d. Java 8 features
e. OOPS and design patterns
f. Logging details

Note: Please send us all the required project files for execution.
------

**Scenario - Case #2**
Write a query given the following data model:
Query: Get list of Posts with latest 10 comments of each post authored by 'James Bond'


create table author (
id int primary key,
name varchar2(100)
)

create table post(
id int primary key,
name varchar2(100),
authorid int foreign key references id of table author
createdts datetime
)

create table comment (
id int primary key,
content varchar2(1000),
postid int foreign key references id of table post
createdts datetime
userid int foreign key references id of table user
)

create table user (
id int primary key,
name varchar2(100)
)
