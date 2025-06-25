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

CREATE TABLE pessoa (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    imagem_url VARCHAR(255),
    nome VARCHAR(150),
    nome_social VARCHAR(150),
    data_nascimento DATE,
    sexo VARCHAR(10),
    cpf VARCHAR(11),
    rg VARCHAR(20),
    telefone VARCHAR(20),
    celular VARCHAR(20),
    email VARCHAR(255),
    estado_civil VARCHAR(50),
    profissao VARCHAR(50),
    nacionalidade VARCHAR(50),
    naturalidade VARCHAR(50),
    endereco_id BIGINT,
    etnia VARCHAR(50),
    situacao VARCHAR(50),
    CONSTRAINT fk_pessoa_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);


CREATE TABLE instituicao (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    secretaria VARCHAR(255),
    nome_fantasia VARCHAR(255),
    email VARCHAR(255),
    telefone_fixo VARCHAR(20),
    telefone_celular VARCHAR(20),
    endereco_id BIGINT,
    CONSTRAINT fk_instituicao_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE inquerito_policial (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    numero_justica VARCHAR(255),
    ordem_ip BIGINT,
    timestamp TIMESTAMP,
    peca VARCHAR(50),
    situacao VARCHAR(255),
    origem_forca_policial VARCHAR(255),
    natureza_do_delito TEXT,
    observacao TEXT,
    instituicao_id BIGINT NOT NULL,
    CONSTRAINT fk_inquerito_instituicao FOREIGN KEY (instituicao_id) REFERENCES instituicao(id)
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
    imagem_url VARCHAR(255),
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
    imagem_url VARCHAR(255),
    inquerito_policial_id BIGINT,
    CONSTRAINT fk_arma_inquerito FOREIGN KEY (inquerito_policial_id) REFERENCES inquerito_policial(id)
);

CREATE TABLE boletim_ocorrencia (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    origem VARCHAR(255),
    data_ocorrencia TIMESTAMP,
    endereco_id BIGINT,
    boletim VARCHAR(255),
    natureza VARCHAR(255),
    represetacao VARCHAR(255),
    instituicao_id BIGINT,
    CONSTRAINT fk_boletim_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id),
    CONSTRAINT fk_boletim_instituicao FOREIGN KEY (instituicao_id) REFERENCES instituicao(id),
);


CREATE table veiculo (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    imagem_url VARCHAR(255),
    renavam VARCHAR(11),
    placa varchar(7),
    chassi VARCHAR(17),
    numero_motor VARCHAR(50),
    tipo_veiculo VARCHAR(50),
    categoria varchar(50),
    especie varchar(50),
    marca varchar(50),
    modelo varchar(50),
    ano_modelo varchar(4),
    ano_fabricacao varchar(4),
    combustivel varchar(25),
    cambio varchar(11),
    tipo_tracao varchar(25),
    cor_predominante varchar(50),
    carroceria varchar(50),
    numero_eixos INTEGER,
    capacidade_carga NUMERIC(15,3),
    potencia_motor NUMERIC(15,3),
    cilindrada NUMERIC(15,3),
    peso_bruto NUMERIC(15,3),
    uf_registro varchar(2),
    municipio_registro varchar(28),
    situacao_veiculo varchar(50),
    situacao_licenciamento varchar(50),
    restricao_judicial varchar(50),
    data_primeiro_licenciamento DATE,
    numero_crv VARCHAR(50),
    numero_crlv VARCHAR(50),
    tabela_fipe VARCHAR(50),
    inquerito_policial_id BIGINT,
    propietario_id BIGINT,
    boletim_ocorrencia_id BIGINT,
    intistituicao_id BIGINT,
    CONSTRAINT fk_veiculo_boletim FOREIGN KEY (boletim_ocorrencia_id) REFERENCES boletim_ocorrencia(id),
    CONSTRAINT fk_veiculo_pessoa FOREIGN KEY (propietario_id) REFERENCES pessoa(id),
    CONSTRAINT fk_veiculo_inquerito FOREIGN KEY (inquerito_policial_id) REFERENCES inquerito_policial(id)
);

CREATE TABLE objeto (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    imagem_url VARCHAR(255),
    tipo_objeto VARCHAR(25),
    descricao TEXT,
    quantidade INT DEFAULT 1,
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
    inquerito_policial_id BIGINT,
    boletim_ocorrencia_id BIGINT,
    proprietario_id BIGINT,
    CONSTRAINT fk_objeto_inquerito FOREIGN KEY (inquerito_policial_id) REFERENCES inquerito_policial(id),
    CONSTRAINT fk_objeto_boletim FOREIGN KEY (boletim_ocorrencia_id) REFERENCES boletim_ocorrencia(id),
    CONSTRAINT fk_objeto_pessoa FOREIGN KEY (proprietario_id) REFERENCES pessoa(id)
);

CREATE TABLE pessoa_boletim (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    pessoa_id BIGINT,
    boletim_ocorrencia_id BIGINT,
    papel VARCHAR(50),
    CONSTRAINT fk_pessoa_boletim_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),
    CONSTRAINT fk_pessoa_boletim_boletim FOREIGN KEY (boletim_ocorrencia_id) REFERENCES boletim_ocorrencia(id)
);


CREATE TABLE pessoa_inquerito (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    pessoa_id BIGINT,
    inquerito_policial_id BIGINT,
    papel VARCHAR(15),
    CONSTRAINT fk_pessoa_inquerito_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),
    CONSTRAINT fk_pessoa_inquerito_inquerito FOREIGN KEY (inquerito_policial_id) REFERENCES inquerito_policial(id)
);

