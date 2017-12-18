INSERT INTO usuario (TipoUsuario, cpf, matricula, nome, senha) VALUES ("aluno",	12345678910, 213083123, "BERNARDO DE FARIAS", "123456");
INSERT INTO usuario (TipoUsuario, cpf, matricula, nome, senha) VALUES ("aluno",	12345678911, 213083124, "LEONARDO CANTELMO", "123456");
INSERT INTO usuario (TipoUsuario, cpf, matricula, nome, senha) VALUES ("aluno",	12345678912, 213083125, "LUIZ FELIPE", "123456");
INSERT INTO usuario (TipoUsuario, cpf, matricula, nome, senha) VALUES ("aluno",	12345678913, 213083126, "THIAGO OLIVEIRA", "123456");
INSERT INTO usuario (TipoUsuario, cpf, matricula, nome, senha) VALUES ("prof", 12345678111, 654321, "JOSÉ VITERBO FILHO", "123456");
INSERT INTO usuario (TipoUsuario, cpf, matricula, nome, senha) VALUES ("prof", 12345678112, 654322, "VICTOR ALMEIDA", "123456");
INSERT INTO usuario (TipoUsuario, cpf, matricula, nome, senha) VALUES ("admin", 12345678113, 654323, "LEONARDO CRUZ", "123456");

select * from usuario;
select * from projeto;
select * from projeto_usuario;






INSERT INTO USERS (DTYPE,ID,email,matricula,  name, password, user_type) VALUES ("aluno", 10,'bfmello[at]hotmail.com', 213083123, "BERNARDO DE FARIAS", "123456", 'aluno');
INSERT INTO USERS(DTYPE,ID,email, matricula, name, password, user_type) VALUES ("aluno",11,' ', 213083124, "LEONARDO CANTELMO", "123456",'aluno');
INSERT INTO USERS(DTYPE,ID,email, matricula, passwordname, password, user_type) VALUES ("aluno",12,' ', 213083125, "LUIZ FELIPE", "123456",'aluno');
INSERT INTO USERS(DTYPE,ID,email,  matricula, name, password, user_type) VALUES ("aluno",13, ' ', 213083126, "THIAGO OLIVEIRA", "123456",'aluno');
INSERT INTO USERS(DTYPE,ID,email, matricula, name, password, user_type) VALUES ("prof", 14,' ', 654321, "JOSÉ VITERBO FILHO", "123456",'prof');
INSERT INTO USERS(DTYPE,ID,email,  matricula, name, password, user_type) VALUES ("prof", 15,' ', 654322, "VICTOR ALMEIDA", "123456",'prof');
INSERT INTO USERS(DTYPE,ID,email,  matricula, name, password, user_type) VALUES ("admin",16, ' ', 654323, "LEONARDO CRUZ", "123456", 'admin');

select * from usuario;
select * from projeto;
select * from projeto_usuario;