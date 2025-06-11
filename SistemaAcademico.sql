Create database sistemagerenciamento;
use  sistemagerenciamento;

create table telefone(
id_telefone int auto_increment primary key,
ddd varchar(5),
telefone varchar(20),

UNIQUE (ddd,telefone)
);

CREATE TABLE endereco (
    id_endereco INT AUTO_INCREMENT PRIMARY KEY,
    endereco VARCHAR(255) NOT NULL,
    cep VARCHAR(10),
    numero VARCHAR(10),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    pais VARCHAR(50),
    
    UNIQUE (endereco,cep,numero,complemento,bairro,cidade,estado,pais)
);
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nomeUsuario VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(14),
    sexo ENUM('MASCULINO', 'FEMININO', 'OUTRO'),
    dataNascimento DATE,
    email VARCHAR(150),
    telefone_id INT DEFAULT NULL,
    endereco_id INT DEFAULT NULL,
    ativo TINYINT(1),
    permissao ENUM('ADMIN', 'PROFESSOR', 'ALUNO'),
    FOREIGN KEY (telefone_id) REFERENCES telefone(id_telefone) ON DELETE SET NULL,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id_endereco) ON DELETE SET NULL
);

CREATE TABLE professor (
    id_usuario INT PRIMARY KEY,
    especialidade VARCHAR(100),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE aluno (
    id_usuario INT PRIMARY KEY,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) 
);

CREATE TABLE curso (
    id_curso INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    nivel enum('BASICO','INTERMEDIARIO','AVANCADO'),
    modalidade enum('PRESENCIAL','ONLINE','HIBRIDO')
);
CREATE TABLE disciplina (
    id_disciplina INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cargaHoraria INT NOT NULL,
    id_curso INT NOT NULL,
    id_professor INT NOT NULL,
    FOREIGN KEY (id_curso) REFERENCES curso(id_curso) ,
    FOREIGN KEY (id_professor) REFERENCES professor(id_usuario) 
);


CREATE TABLE matricula (
    id_matricula INT AUTO_INCREMENT PRIMARY KEY,
    id_aluno INT,
    id_curso INT,
    data_matricula DATE,
    FOREIGN KEY (id_aluno) REFERENCES aluno(id_usuario),
    FOREIGN KEY (id_curso) REFERENCES curso(id_curso)
);
CREATE TABLE professor_disciplina(
id_professor INT,
id_disciplina INT,
PRIMARY KEY (id_professor,id_disciplina),
FOREIGN KEY	(id_professor) REFERENCES professor(id_usuario),
FOREIGN KEY (id_disciplina) REFERENCES disciplina(id_disciplina)
);
CREATE TABLE curso_disciplina (
    id_curso INT NOT NULL,
    id_disciplina INT NOT NULL,
    PRIMARY KEY (id_curso, id_disciplina),
    FOREIGN KEY (id_curso) REFERENCES curso(id_curso),
    FOREIGN KEY (id_disciplina) REFERENCES disciplina(id_disciplina)
);
CREATE TABLE aluno_curso (
    id_usuario INT,
    id_curso INT,
    PRIMARY KEY (id_usuario, id_curso),
    FOREIGN KEY (id_usuario) REFERENCES aluno(id_usuario),
    FOREIGN KEY (id_curso) REFERENCES curso(id_curso)
);

CREATE TABLE aluno_disciplina (
    id_usuario INT,
    id_disciplina INT,
    PRIMARY KEY (id_usuario, id_disciplina),
    FOREIGN KEY (id_usuario) REFERENCES aluno(id_usuario),
    FOREIGN KEY (id_disciplina) REFERENCES disciplina(id_disciplina)
);

SELECT CONSTRAINT_NAME, TABLE_NAME
FROM information_schema.KEY_COLUMN_USAGE
WHERE CONSTRAINT_NAME = 'fk_telefone';
SHOW CREATE TABLE usuario ;
SELECT * FROM usuario;
DELETE FROM usuario WHERE id_usuario = 13;

Alter table Telefone 
CHANGE COLUMN id id_telefone INT;

SELECT CONSTRAINT_NAME,TABLE_NAME,COLUMN_NAME
FROM information_schema.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'telefone'
  AND REFERENCED_TABLE_NAME = 'usuario';

ALTER TABLE telefone
ALGORITHM=INPLACE,
LOCK=NONE,
CHANGE COLUMN id id_telefone INT;

SELECT u.*, p.especialidade
FROM usuario u
JOIN professor p ON u.id_usuario = p.id_usuario
WHERE u.id_usuario = 1;

