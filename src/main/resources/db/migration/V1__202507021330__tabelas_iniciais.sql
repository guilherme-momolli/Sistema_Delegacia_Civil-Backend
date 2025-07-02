-- Tabela endereco
CREATE TABLE endereco (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    logradouro VARCHAR(255) NOT NULL,
    numero INT,
    bairro VARCHAR(100),
    cep VARCHAR(8),
    municipio VARCHAR(100) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    pais VARCHAR(50) NOT NULL
);

-- Índice para cep
CREATE INDEX idx_endereco_cep ON endereco(cep);

-- Tabela pessoa
CREATE TABLE pessoa (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    imagem_url VARCHAR(255),
    nome VARCHAR(150),
    nome_social VARCHAR(150),
    data_nascimento TIMESTAMP,
    sexo VARCHAR(10),
    cpf VARCHAR(11),
    rg VARCHAR(20),
    telefone_fixo VARCHAR(20),
    telefone_celular VARCHAR(20),
    email VARCHAR(100),
    estado_civil VARCHAR(50),
    profissao VARCHAR(50),
    nacionalidade VARCHAR(50),
    naturalidade VARCHAR(50),
    endereco_id BIGINT,
    etnia VARCHAR(50),
    situacao_pessoa VARCHAR(50),
    CONSTRAINT fk_pessoa_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

-- Índice para cpf
CREATE INDEX idx_pessoa_cpf ON pessoa(cpf);

-- Tabela instituicao
CREATE TABLE instituicao (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    imagem_url VARCHAR(255),
    tipo VARCHAR(25),
    secretaria VARCHAR(255),
    razao_social VARCHAR(255) NOT NULL,
    nome_fantasia VARCHAR(255),
    cnpj VARCHAR(14),
    especializacao VARCHAR(100),
    email VARCHAR(255),
    telefone_fixo VARCHAR(20),
    telefone_celular VARCHAR(20),
    endereco_id BIGINT,
    CONSTRAINT fk_instituicao_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

-- Índice para cnpj
CREATE INDEX idx_instituicao_cnpj ON instituicao(cnpj);

CREATE TABLE usuario (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nome VARCHAR(150),
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    privilegio VARCHAR(50) NOT NULL,
    instituicao_id BIGINT NOT NULL,
    CONSTRAINT fk_usuario_instituicao FOREIGN KEY (instituicao_id) REFERENCES instituicao(id)
);


-- Tabela objeto
CREATE TABLE objeto (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    imagem_url VARCHAR(255),
    tipo_objeto VARCHAR(25),
    descricao TEXT,
    quantidade INT,
    valor_estimado NUMERIC(15,2),
    numero_serie VARCHAR(50),
    marca VARCHAR(100),
    modelo VARCHAR(100),
    cor VARCHAR(50),
    material VARCHAR(50),
    dimensoes VARCHAR(100),
    estado_conservacao VARCHAR(25),
    data_apreensao DATE,
    local_encontrado VARCHAR(255),
    situacao_objeto VARCHAR(50),
    observacao TEXT,
    proprietario_id BIGINT,
    CONSTRAINT fk_objeto_pessoa FOREIGN KEY (proprietario_id) REFERENCES pessoa(id)
);

-- Tabela veiculo
CREATE TABLE veiculo (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    proprietario_id BIGINT,
    imagem_url VARCHAR(255),
    renavam VARCHAR(11),
    placa VARCHAR(7),
    chassi VARCHAR(17),
    numero_motor VARCHAR(25),
    tipo_veiculo VARCHAR(50),
    categoria VARCHAR(50),
    especie VARCHAR(50),
    marca VARCHAR(50),
    modelo VARCHAR(50),
    ano_modelo VARCHAR(4),
    ano_fabricacao VARCHAR(4),
    combustivel VARCHAR(25),
    cambio VARCHAR(11),
    tipo_tracao VARCHAR(25),
    cor_predominante VARCHAR(50),
    carroceria VARCHAR(50),
    numero_eixos INTEGER,
    capacidade_carga NUMERIC(15,3),
    potencia_motor NUMERIC(15,3),
    cilindrada NUMERIC(15,3),
    peso_bruto NUMERIC(15,3),
    uf_registro VARCHAR(2),
    municipio_registro VARCHAR(28),
    situacao_veiculo VARCHAR(50),
    situacao_licenciamento VARCHAR(50),
    restricao_judicial VARCHAR(50),
    data_primeiro_licenciamento TIMESTAMP,
    numero_crv VARCHAR(50),
    numero_crlv VARCHAR(50),
    tabela_fipe VARCHAR(50),
    CONSTRAINT fk_veiculo_proprietario FOREIGN KEY (proprietario_id) REFERENCES pessoa(id)
);

-- Índice para placa
CREATE INDEX idx_veiculo_placa ON veiculo(placa);

-- Tabela droga
CREATE TABLE droga (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    imagem_url VARCHAR(255),
    tipo_droga VARCHAR(50),
    nome_popular VARCHAR(255),
    unidade_medida VARCHAR(50),
    quantidade NUMERIC(15,3),
    quantidade_pericia NUMERIC(15,3),
    quantidade_por_extenso TEXT,
    observacao TEXT,
    numero_lacre BIGINT,
    local_droga VARCHAR(255)
);

-- Tabela arma
CREATE TABLE arma (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    proprietario_id BIGINT,
    imagem_url VARCHAR(255),
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
    CONSTRAINT fk_arma_proprietario FOREIGN KEY (proprietario_id) REFERENCES pessoa(id)
);

-- Tabela boletim_ocorrencia
CREATE TABLE boletim_ocorrencia (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    origem VARCHAR(255),
    data_ocorrencia TIMESTAMP NOT NULL,
    endereco_id BIGINT,
    boletim VARCHAR(255) NOT NULL,
    natureza VARCHAR(255),
    representacao VARCHAR(255),
    instituicao_id BIGINT,
    CONSTRAINT fk_boletim_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id),
    CONSTRAINT fk_boletim_instituicao FOREIGN KEY (instituicao_id) REFERENCES instituicao(id)
);

-- Tabela apreensao (sem fk_apreensao_inquerito inicialmente)
CREATE TABLE apreensao (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    data_apreensao TIMESTAMP NOT NULL,
    local_apreensao VARCHAR(255),
    tipo_apreensao VARCHAR(50),
    objetos_id BIGINT,
    armas_id BIGINT,
    drogas_id BIGINT,
    veiculos_id BIGINT,
    descricao TEXT,
    numero_lacre VARCHAR(50),
    observacao TEXT,
    responsavel_id BIGINT,
    inquerito_id BIGINT,
    boletim_id BIGINT,
    CONSTRAINT fk_apreensao_responsavel FOREIGN KEY (responsavel_id) REFERENCES pessoa(id),
    CONSTRAINT fk_apreensao_boletim FOREIGN KEY (boletim_id) REFERENCES boletim_ocorrencia(id),
    CONSTRAINT fk_apreensao_objeto FOREIGN KEY (objetos_id) REFERENCES objeto(id),
    CONSTRAINT fk_apreensao_arma FOREIGN KEY (armas_id) REFERENCES arma(id),
    CONSTRAINT fk_apreensao_droga FOREIGN KEY (drogas_id) REFERENCES droga(id),
    CONSTRAINT fk_apreensao_veiculo FOREIGN KEY (veiculos_id) REFERENCES veiculo(id)
);

-- Tabela inquerito_policial (sem vitima_id, investigado_id, relator_id)
CREATE TABLE inquerito_policial (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    numero_justica VARCHAR(255),
    ordem_ip BIGINT,
    data_abertura TIMESTAMP NOT NULL,
    peca VARCHAR(50),
    situacao VARCHAR(25),
    origem_forca_policial VARCHAR(255),
    natureza_do_delito TEXT,
    observacao TEXT,
    instituicao_id BIGINT,
    CONSTRAINT fk_inquerito_instituicao FOREIGN KEY (instituicao_id) REFERENCES instituicao(id)
);

-- Adicionar constraint fk_apreensao_inquerito
ALTER TABLE apreensao
ADD CONSTRAINT fk_apreensao_inquerito FOREIGN KEY (inquerito_id) REFERENCES inquerito_policial(id);

-- Tabela pessoa_boletim (muitos-para-muitos entre pessoa e boletim_ocorrencia)
CREATE TABLE pessoa_boletim (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    pessoa_id BIGINT NOT NULL,
    boletim_id BIGINT NOT NULL,
    tipo_envolvimento VARCHAR(50) NOT NULL,
    observacao TEXT,
    CONSTRAINT fk_pessoa_boletim_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),
    CONSTRAINT fk_pessoa_boletim_boletim FOREIGN KEY (boletim_id) REFERENCES boletim_ocorrencia(id),
    CONSTRAINT uk_pessoa_boletim UNIQUE (pessoa_id, boletim_id)
);

-- Tabela pessoa_inquerito (muitos-para-muitos entre pessoa e inquerito_policial)
CREATE TABLE pessoa_inquerito (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    pessoa_id BIGINT NOT NULL,
    inquerito_id BIGINT NOT NULL,
    tipo_envolvimento VARCHAR(50) NOT NULL,
    observacao TEXT,
    CONSTRAINT fk_pessoa_inquerito_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),
    CONSTRAINT fk_pessoa_inquerito_inquerito FOREIGN KEY (inquerito_id) REFERENCES inquerito_policial(id),
    CONSTRAINT uk_pessoa_inquerito UNIQUE (pessoa_id, inquerito_id)
);