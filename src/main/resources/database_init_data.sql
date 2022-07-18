insert into user(userName, password, highScore)VALUES ('anne', 'sikkertpassord', 0);
insert into user(userName, password, highScore) VALUES ('bjørn', 'passord', 0);
insert into user(userName, password, highScore) VALUES ('carlos', 'drossap', 0);
insert into user(userName, password, highScore) VALUES ('denise', '123321', 0);

insert into questionnaire(questionnaireName) VALUES ('Matlaging');
insert into questionnaire(questionnaireName) VALUES ('Generisk Gaming');

insert into question(questionnaireId, question, type) VALUES (1, 'Hva er Bechamel?', 'MultipleChoice');
insert into answer(questionId, answer, isCorrect) VALUES (1, 'et kjøttstykke', false);
insert into answer(questionId, answer, isCorrect) VALUES (1, 'en blanding av smør, mel, og melk', true);
insert into answer(questionId, answer, isCorrect) VALUES (1, 'en argentisk matrett', false);
insert into answer(questionId, answer, isCorrect) VALUES (1, 'et fransk pålegg', false);

insert into question(questionnaireId, question, type) VALUES (1, 'Er ost godt?', 'Binary');
insert into answer(questionId, answer, isCorrect) VALUES (2, 'nei', false);
insert into answer(questionId, answer, isCorrect) VALUES (2, 'ja', true);


insert into question(questionnaireId, question, type) VALUES (2, 'Hva heter hovedkarakteren i Witcher 3?', 'MultipleChoice');
insert into answer(questionId, answer, isCorrect) VALUES (3, 'Gjert', false);
insert into answer(questionId, answer, isCorrect) VALUES (3, 'Geir', false);
insert into answer(questionId, answer, isCorrect) VALUES (3, 'Gerald', true);
insert into answer(questionId, answer, isCorrect) VALUES (3, 'Gjermund', false);

insert into question(questionnaireId, question, type) VALUES (2, 'PONG er det første spillet', 'Binary');
insert into answer(questionId, answer, isCorrect) VALUES (4, 'nei', true);
insert into answer(questionId, answer, isCorrect) VALUES (4, 'ja', false);