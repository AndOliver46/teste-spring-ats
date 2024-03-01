CREATE TABLE tb_endereco (
    id UUID PRIMARY KEY,
    logradouro VARCHAR(255) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    pessoa_id UUID NOT NULL,
    CONSTRAINT fk_pessoa_id FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa(id));