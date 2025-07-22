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

CREATE TABLE instituicao (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    imagem_url VARCHAR(255),
    tipo_empresa VARCHAR(50),
    razao_social VARCHAR(255),
    nome_fantasia VARCHAR(255),
    cnpj VARCHAR(14) UNIQUE,
    email VARCHAR(255),
    telefone_fixo VARCHAR(20),
    telefone_celular VARCHAR(20),
    endereco_id BIGINT NOT NULL REFERENCES endereco(id),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE delegacia (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    imagem_url VARCHAR(255),
    tipo_delegacia VARCHAR(50) NOT NULL,
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
    instituicao_id BIGINT REFERENCES instituicao(id),
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
    pessoa_id BIGINT REFERENCES pessoa(id),
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
    status_aprovacao VARCHAR(25),
    ativo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_inquerito_delegacia FOREIGN KEY (delegacia_id) REFERENCES delegacia(id)
);


CREATE TABLE bem (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    tipo_bem VARCHAR(20) NOT NULL,                          -- Ex: 'ARMA', 'DROGA', 'VEICULO', 'OBJETO'
    imagem_url VARCHAR(255),
    marca VARCHAR(255),
    modelo VARCHAR(255),
    valor_estimado NUMERIC(15,2),
    pessoa_id BIGINT,
    delegacia_id BIGINT,
    instituicao_id BIGINT,
    situacao_bem VARCHAR(50),                               -- Ex: 'APREENDIDO', 'ROUBADO', 'FURTADO'
    origem VARCHAR(100),                                    -- Ex: 'PATRIMONIAL', 'APREENSÃO'
    numero_lacre VARCHAR(50),
    local_bem varchar(50),
    observacao TEXT,
    descricao TEXT,
    ativo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bem_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),
    CONSTRAINT fk_bem_delegacia FOREIGN KEY (delegacia_id) REFERENCES delegacia(id),
    CONSTRAINT fk_bem_instituicao FOREIGN KEY (instituicao_id) REFERENCES instituicao(id)
);

CREATE TABLE bem_envolvimento (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bem_id BIGINT NOT NULL,
    boletim_id BIGINT,
    inquerito_id BIGINT,
    tipo_envolvimento VARCHAR(50),                          -- Ex: 'APREENDIDO', 'FURTADO', 'RECUPERADO'
    data_envolvimento TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bem_envolvimento_bem FOREIGN KEY (bem_id) REFERENCES bem(id),
    CONSTRAINT fk_bem_envolvimento_boletim FOREIGN KEY (boletim_id) REFERENCES boletim_ocorrencia(id),
    CONSTRAINT fk_bem_envolvimento_inquerito FOREIGN KEY (inquerito_id) REFERENCES inquerito_policial(id)
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
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
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
    especie VARCHAR(50),
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
    data_primeiro_licenciamento TIMESTAMP WITH TIME ZONE,
    numero_crv VARCHAR(50),
    numero_crlv VARCHAR(50),
    tabela_fipe VARCHAR(50),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_veiculo_bem FOREIGN KEY (bem_id) REFERENCES bem(id)
);

CREATE TABLE droga (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bem_id BIGINT UNIQUE NOT NULL,
    tipo_droga VARCHAR(50),
    nome_popular VARCHAR(255),
    unidade_medida VARCHAR(50),
    quantidade_por_extenso TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_droga_bem FOREIGN KEY (bem_id) REFERENCES bem(id)
);

CREATE TABLE arma (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bem_id BIGINT UNIQUE NOT NULL,
    tipo_arma_fogo VARCHAR(255),
    especie VARCHAR(255),
    calibre VARCHAR(255),
    numero_porte VARCHAR(255),
    numero_serie VARCHAR(255),
    numero_registro VARCHAR(255),
    capacidade VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_arma_bem FOREIGN KEY (bem_id) REFERENCES bem(id)
);

CREATE TABLE bem_movimentacao (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bem_id BIGINT NOT NULL,
    origem VARCHAR(50),
    destino_delegacia_id BIGINT,
    data_movimentacao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    observacao TEXT,
    usuario_responsavel_id BIGINT,
    CONSTRAINT fk_mov_bem FOREIGN KEY (bem_id) REFERENCES bem(id),
    CONSTRAINT fk_mov_destino FOREIGN KEY (destino_delegacia_id) REFERENCES delegacia(id),
    CONSTRAINT fk_mov_usuario FOREIGN KEY (usuario_responsavel_id) REFERENCES usuario(id)
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
--    CONSTRAINT uk_pessoa_envolvimento UNIQUE (pessoa_id, boletim_id, inquerito_id)
);

CREATE INDEX idx_veiculo_placa ON veiculo(placa);
CREATE INDEX idx_inquerito_delegacia ON inquerito_policial(delegacia_id);
CREATE INDEX idx_endereco_cep ON endereco(cep);
CREATE INDEX idx_endereco_regiao ON endereco(regiao_administrativa);
CREATE INDEX idx_boletim_ocorrencia_boletim ON boletim_ocorrencia(boletim);
CREATE INDEX idx_boletim_delegacia ON boletim_ocorrencia(delegacia_id);
CREATE INDEX idx_usuario_delegacia ON usuario(delegacia_id);