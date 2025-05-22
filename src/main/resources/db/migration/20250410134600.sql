--CREATE DATABASE delegacia WITH ENCODING 'UTF8' TEMPLATE template0;

CREATE TABLE endereco (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    logradouro VARCHAR(255) NOT NULL,
    numero INT NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    municipio VARCHAR(100) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    pais VARCHAR(50) NOT NULL
);

CREATE TABLE instituicao (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    secretaria VARCHAR(255) NOT NULL,
    nome_fantasia VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    telefone_fixo VARCHAR(20) UNIQUE,
    telefone_celular VARCHAR(20) UNIQUE,
    endereco_id BIGINT,
    CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE inquerito_policial (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    numero_justica VARCHAR(255),
    ordem_ip BIGINT,
    timestamp TIMESTAMP,
    peca VARCHAR(50),
    situacao VARCHAR(255),
    relator VARCHAR(255),
    origem_forca_policial VARCHAR(255),
    investigado VARCHAR(255),
    vitima VARCHAR(255),
    natureza_do_delito TEXT,
    observacao TEXT,
    instituicao_id BIGINT NOT NULL,
    CONSTRAINT fk_instituicao FOREIGN KEY (instituicao_id) REFERENCES instituicao(id)
);

CREATE TABLE droga (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    tipo_droga VARCHAR(50),
    nome_popular VARCHAR(255),
    unidade_medida VARCHAR(50),
    quantidade NUMERIC(15, 3),
    quantidade_pericia BIGINT,
    quantidade_por_extenso TEXT,
    observacao TEXT,
    numero_lacre BIGINT,
    local_droga VARCHAR(255),
    inquerito_policial_id BIGINT,
    CONSTRAINT fk_droga_inquerito FOREIGN KEY (inquerito_policial_id) REFERENCES inquerito_policial(id)
);

CREATE TABLE arma (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    tipo_arma_fogo VARCHAR(255),
    especie VARCHAR(255),
    marca VARCHAR(255),
    calibre VARCHAR(255),
    numero_porte VARCHAR(255),
    numero_serie VARCHAR(255),
    numero_registro VARCHAR(255),
    capacidade VARCHAR(255),
    quantidade VARCHAR(255),
    numero_lacre VARCHAR(255),
    valor NUMERIC(15,2),
    local_arma VARCHAR(255),
    inquerito_policial_id BIGINT,
    CONSTRAINT fk_droga_inquerito FOREIGN KEY (inquerito_policial_id) REFERENCES inquerito_policial(id)
);
