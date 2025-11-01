CREATE TABLE endereco (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    logradouro VARCHAR(255) NOT NULL,
    numero INT,
    bairro VARCHAR(100),
    cep VARCHAR(8),
    municipio VARCHAR(100) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    pais VARCHAR(50) NOT NULL,
    regiao_administrativa VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE delegacia (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    imagem_url VARCHAR(255),
    tipo_delegacia VARCHAR(50),
    nome VARCHAR(255),
    especializacao VARCHAR(100),
    email VARCHAR(255),
    telefone_fixo VARCHAR(20),
    telefone_celular VARCHAR(20),
    endereco_id BIGINT NOT NULL REFERENCES endereco(id),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE pessoa (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    imagem_url VARCHAR(255),
    nome VARCHAR(150),
    nome_social VARCHAR(150),
    data_nascimento TIMESTAMP WITH TIME ZONE,
    sexo VARCHAR(10),
    cpf VARCHAR(11) UNIQUE,
    rg VARCHAR(20),
    telefone_fixo VARCHAR(20),
    telefone_celular VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    estado_civil VARCHAR(50),
    profissao VARCHAR(50),
    cargo VARCHAR(50),
    nacionalidade VARCHAR(50),
    naturalidade VARCHAR(50),
    certificado_registro VARCHAR(50),
    endereco_id BIGINT REFERENCES endereco(id),
    etnia VARCHAR(50),
    situacao_pessoa VARCHAR(50),
    ativo BOOLEAN DEFAULT true,
    faccao varchar(50),
   	cargo_faccao varchar(50),
   	situacao_faccao varchar(50),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE usuario (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(150),
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(255),
    privilegio VARCHAR(50),
    delegacia_id BIGINT REFERENCES delegacia(id),
    ativo BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE boletim_ocorrencia (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    origem VARCHAR(255),
    data_ocorrencia TIMESTAMP WITH TIME ZONE NOT NULL,
    endereco_id BIGINT,
    boletim VARCHAR(255) NOT NULL,
    natureza VARCHAR(255),
    representacao VARCHAR(255),
    delegacia_id BIGINT NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_boletim_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id),
    CONSTRAINT fk_boletim_delegacia FOREIGN KEY (delegacia_id) REFERENCES delegacia(id)
);

CREATE TABLE inquerito_policial (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    numero_processo VARCHAR(255),
    ordem_ip BIGINT,
    data_abertura TIMESTAMP WITH TIME ZONE NOT NULL,
    peca VARCHAR(50),
    situacao VARCHAR(25),
    origem_forca_policial VARCHAR(255),
    natureza_do_delito TEXT,
    observacao TEXT,
    delegacia_id BIGINT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_inquerito_delegacia FOREIGN KEY (delegacia_id) REFERENCES delegacia(id)
);

CREATE TABLE bem (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    tipo_bem VARCHAR(20) NOT NULL,
    imagem_url VARCHAR(255),
    marca VARCHAR(255),
    modelo VARCHAR(255),
    valor_estimado NUMERIC(15,2),
    pessoa_id BIGINT,
    delegacia_id BIGINT,
    situacao_bem VARCHAR(50),
    origem VARCHAR(100),
    numero_lacre VARCHAR(50),
    local_bem varchar(50),
    observacao TEXT,
    descricao TEXT,
    ativo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bem_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),
    CONSTRAINT fk_bem_delegacia FOREIGN KEY (delegacia_id) REFERENCES delegacia(id)
);

CREATE TABLE objeto (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bem_id BIGINT UNIQUE NOT NULL,
    tipo_objeto VARCHAR(25),
    numero_serie VARCHAR(50),
    cor VARCHAR(50),
    material VARCHAR(50),
    dimensoes VARCHAR(100),
    estado_conservacao VARCHAR(25),
    situacao_objeto VARCHAR(50),
    CONSTRAINT fk_objeto_bem FOREIGN KEY (bem_id) REFERENCES bem(id)
);

CREATE TABLE veiculo (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bem_id BIGINT UNIQUE NOT NULL,
    renavam VARCHAR(11),
    placa VARCHAR(7),
    chassi VARCHAR(17),
    numero_motor VARCHAR(25),
    tipo_veiculo VARCHAR(50),
    categoria VARCHAR(50),
    especie_veiculo VARCHAR(50),
    ano_modelo TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    ano_fabricacao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
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
    data_primeiro_licenciamento TIMESTAMP WITH TIME ZONE,
    numero_crv VARCHAR(50),
    numero_crlv VARCHAR(50),
    tabela_fipe VARCHAR(50),
    CONSTRAINT fk_veiculo_bem FOREIGN KEY (bem_id) REFERENCES bem(id)
);

CREATE TABLE droga (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bem_id BIGINT UNIQUE NOT NULL,
    tipo_droga VARCHAR(50),
    nome_popular VARCHAR(255),
    unidade_medida VARCHAR(50),
    peso_bruto NUMERIC(10, 3),
    quantidade INT,
    quantidade_por_extenso TEXT,
    CONSTRAINT fk_droga_bem FOREIGN KEY (bem_id) REFERENCES bem(id)
);

CREATE TABLE arma (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bem_id BIGINT UNIQUE NOT NULL,
    tipo_arma_fogo VARCHAR(50),
    especie_arma VARCHAR(50),
    calibre VARCHAR(50),
    numero_porte VARCHAR(100),
    numero_serie VARCHAR(100),
    numero_registro VARCHAR(100),
    capacidade VARCHAR(50),
    CONSTRAINT fk_arma_bem FOREIGN KEY (bem_id) REFERENCES bem(id)
);

CREATE TABLE bem_envolvimento (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bem_id BIGINT NOT NULL,
    boletim_id BIGINT,
    inquerito_id BIGINT,
    tipo_envolvimento VARCHAR(50),
    data_envolvimento TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bem_envolvimento_bem FOREIGN KEY (bem_id) REFERENCES bem(id),
    CONSTRAINT fk_bem_envolvimento_boletim FOREIGN KEY (boletim_id) REFERENCES boletim_ocorrencia(id),
    CONSTRAINT fk_bem_envolvimento_inquerito FOREIGN KEY (inquerito_id) REFERENCES inquerito_policial(id)
);

CREATE TABLE pessoa_envolvimento (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pessoa_id BIGINT NOT NULL,
    boletim_id BIGINT,
    inquerito_id BIGINT,
    tipo_envolvimento VARCHAR(50),
    observacao TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pessoa_envolvimento_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),
    CONSTRAINT fk_pessoa_envolvimento_boletim_ocorrencia FOREIGN KEY (boletim_id) REFERENCES boletim_ocorrencia(id),
    CONSTRAINT fk_pessoa_envolvimento_inquerito_policial FOREIGN KEY (inquerito_id) REFERENCES inquerito_policial(id)
);

--CREATE INDEX idx_veiculo_placa ON veiculo(placa);
--CREATE INDEX idx_inquerito_delegacia ON inquerito_policial(del	egacia_id);
--CREATE INDEX idx_endereco_cep ON endereco(cep);
--CREATE INDEX idx_endereco_regiao ON endereco(regiao_administrativa);
--CREATE INDEX idx_boletim_ocorrencia_boletim ON boletim_ocorrencia(boletim);
--CREATE INDEX idx_boletim_delegacia ON boletim_ocorrencia(delegacia_id);
--CREATE INDEX idx_usuario_delegacia ON usuario(delegacia_id);