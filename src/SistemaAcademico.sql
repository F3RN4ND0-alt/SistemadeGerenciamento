Create database sistemagerenciamento;
use  sistemagerenciamento;

create table telefone(
id int auto_increment primary key,
ddd varchar(5),
telefone varchar(20)
);

CREATE TABLE endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    endereco VARCHAR(255) NOT NULL,
    cep VARCHAR(10),
    numero VARCHAR(10),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    pais VARCHAR(50)
);
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nomeUsuario VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(14),
    sexo ENUM('MASCULINO', 'FEMININO', 'OUTRO'),
    dataNascimento DATE,
    email VARCHAR(150),
    telefone_id INT,
    endereco_id INT,
    ativo TINYINT(1),
    permissao ENUM('ADMIN', 'PROFESSOR', 'ALUNO'),
    FOREIGN KEY (telefone_id) REFERENCES telefone(id),
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
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
    id_disciplina INT,
    data_matricula DATE,
    FOREIGN KEY (id_aluno) REFERENCES aluno(id_usuario),
    FOREIGN KEY (id_disciplina) REFERENCES disciplina(id_disciplina)
);
SELECT * FROM telefone;
DELETE FROM telefone WHERE id = 1;

ALTER TABLE usuario 
ADD  CONSTRAINT fk_endereco
FOREIGN KEY (telefone_id) REFERENCES telefone(id)
ON DELETE CASCADE;

ALTER TABLE usuario 
ADD  CONSTRAINT fk_telefone
FOREIGN KEY (endereco_id) REFERENCES endereco(id)
ON DELETE CASCADE;