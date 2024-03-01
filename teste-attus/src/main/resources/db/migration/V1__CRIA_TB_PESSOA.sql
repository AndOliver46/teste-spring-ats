CREATE TABLE tb_pessoa (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    endereco_principal UUID);