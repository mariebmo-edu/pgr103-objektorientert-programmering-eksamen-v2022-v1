create DATABASE QuestionnaireDb;

use QuestionnaireDb;

create table users(
                      id int not null auto_increment primary key,
                      userName varchar(25),
                      password varchar(50),
                      highScore int
);

create table questionnaire(
                              id int not null auto_increment primary key,
                              questionnaireName varchar(255)
);

create table question(
                         id int not null auto_increment primary key,
                         questionnaireId int,
                         FOREIGN KEY (questionnaireId) references questionnaire(id),
                         question varchar(255),
                         type varchar(255)
);

create table answer(
                       id int not null auto_increment primary key,
                       questionId int,
                       FOREIGN KEY (questionId) references question(id),
                       answer varchar(255),
                       isCorrect bool
);