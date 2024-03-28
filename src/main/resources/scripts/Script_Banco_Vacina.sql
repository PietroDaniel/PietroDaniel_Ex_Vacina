DROP DATABASE IF EXISTS Banco_Vacina;
CREATE DATABASE Banco_Vacina;
USE Banco_Vacina;

CREATE TABLE pais (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) UNIQUE NOT NULL,
    sigla VARCHAR(2) UNIQUE NOT NULL
    
);

CREATE TABLE pessoa (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    data_Nascimento DATE NOT NULL,
    tipo VARCHAR(255) NOT NULL, 
    sexo VARCHAR(1) NOT NULL,
    id_pais INT NOT NULL,
    CONSTRAINT fk_id_pais FOREIGN KEY(id_pais) REFERENCES pais(id)
);

CREATE TABLE vacina (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    estagio VARCHAR(255) NOT NULL,
    data_inicio DATE NOT NULL,
    id_pesquisador INT NOT NULL,
    id_pais INT NOT NULL,
    CONSTRAINT fk_id_pesquisador FOREIGN KEY(id_pesquisador) REFERENCES pessoa(id),
    CONSTRAINT fk_vacina_id_pais FOREIGN KEY(id_pais) REFERENCES pais(id)
);

CREATE TABLE aplicacao_vacina (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_pessoa INT NOT NULL,
    id_vacina INT NOT NULL,
    data_aplicacao DATE NOT NULL,
    avaliacao INT NOT NULL,
    CONSTRAINT fk_id_pessoa FOREIGN KEY(id_pessoa) REFERENCES pessoa(id),
    CONSTRAINT fk_id_vacina FOREIGN KEY(id_vacina) REFERENCES vacina(id)
);

INSERT INTO pais (nome, sigla) VALUES
('Afeganistão', 'AF'),
('Albânia', 'AL'),
('Argélia', 'DZ'),
('Andorra', 'AD'),
('Angola', 'AO'),
('Antígua e Barbuda', 'AG'),
('Argentina', 'AR'),
('Armênia', 'AM'),
('Austrália', 'AU'),
('Áustria', 'AT'),
('Azerbaijão', 'AZ'),
('Bahamas', 'BS'),
('Bahrein', 'BH'),
('Bangladesh', 'BD'),
('Barbados', 'BB'),
('Bielorrússia', 'BY'),
('Bélgica', 'BE'),
('Belize', 'BZ'),
('Benin', 'BJ'),
('Butão', 'BT'),
('Bolívia', 'BO'),
('Bósnia e Herzegovina', 'BA'),
('Botsuana', 'BW'),
('Brasil', 'BR'),
('Brunei', 'BN'),
('Bulgária', 'BG'),
('Burkina Faso', 'BF'),
('Burundi', 'BI'),
('Cabo Verde', 'CV'),
('Camboja', 'KH'),
('Camarões', 'CM'),
('Canadá', 'CA'),
('República Centro-Africana', 'CF'),
('Chade', 'TD'),
('Chile', 'CL'),
('China', 'CN'),
('Colômbia', 'CO'),
('Comores', 'KM'),
('República Democrática do Congo', 'CD'),
('República do Congo', 'CG'),
('Costa Rica', 'CR'),
('Croácia', 'HR'),
('Cuba', 'CU'),
('Chipre', 'CY'),
('República Tcheca', 'CZ'),
('Dinamarca', 'DK'),
('Djibuti', 'DJ'),
('Dominica', 'DM'),
('República Dominicana', 'DO'),
('Timor-Leste', 'TL'),
('Equador', 'EC'),
('Egito', 'EG'),
('El Salvador', 'SV'),
('Guiné Equatorial', 'GQ'),
('Eritreia', 'ER'),
('Estônia', 'EE'),
('Eswatini', 'SZ'),
('Etiópia', 'ET'),
('Fiji', 'FJ'),
('Finlândia', 'FI'),
('França', 'FR'),
('Gabão', 'GA'),
('Gâmbia', 'GM'),
('Geórgia', 'GE'),
('Alemanha', 'DE'),
('Gana', 'GH'),
('Grécia', 'GR'),
('Granada', 'GD'),
('Guatemala', 'GT'),
('Guiné', 'GN'),
('Guiné-Bissau', 'GW'),
('Guiana', 'GY'),
('Haiti', 'HT'),
('Honduras', 'HN'),
('Hungria', 'HU'),
('Islândia', 'IS'),
('Índia', 'IN'),
('Indonésia', 'ID'),
('Irã', 'IR'),
('Iraque', 'IQ'),
('Irlanda', 'IE'),
('Israel', 'IL'),
('Itália', 'IT'),
('Costa do Marfim', 'CI'),
('Jamaica', 'JM'),
('Japão', 'JP'),
('Jordânia', 'JO'),
('Cazaquistão', 'KZ'),
('Quênia', 'KE'),
('Kiribati', 'KI'),
('Coreia do Norte', 'KP'),
('Coreia do Sul', 'KR'),
('Kuwait', 'KW'),
('Quirguistão', 'KG'),
('Laos', 'LA'),
('Letônia', 'LV'),
('Líbano', 'LB'),
('Lesoto', 'LS'),
('Libéria', 'LR'),
('Líbia', 'LY'),
('Liechtenstein', 'LI'),
('Lituânia', 'LT'),
('Luxemburgo', 'LU'),
('Madagascar', 'MG'),
('Malaui', 'MW'),
('Malásia', 'MY'),
('Maldivas', 'MV'),
('Mali', 'ML'),
('Malta', 'MT'),
('Ilhas Marshall', 'MH'),
('Mauritânia', 'MR'),
('Maurícia', 'MU'),
('México', 'MX'),
('Micronésia', 'FM'),
('Moldávia', 'MD'),
('Mônaco', 'MC'),
('Mongólia', 'MN'),
('Montenegro', 'ME'),
('Marrocos', 'MA'),
('Moçambique', 'MZ'),
('Mianmar', 'MM'),
('Namíbia', 'NA'),
('Nauru', 'NR'),
('Nepal', 'NP'),
('Países Baixos', 'NL'),
('Nova Zelândia', 'NZ'),
('Nicarágua', 'NI'),
('Níger', 'NE'),
('Nigéria', 'NG'),
('Niue', 'NU'),
('Macedônia do Norte', 'MK'),
('Noruega', 'NO'),
('Omã', 'OM'),
('Paquistão', 'PK'),
('Palau', 'PW'),
('Panamá', 'PA'),
('Papua Nova Guiné', 'PG'),
('Paraguai', 'PY'),
('Peru', 'PE'),
('Filipinas', 'PH'),
('Polônia', 'PL'),
('Portugal', 'PT'),
('Catar', 'QA'),
('Romênia', 'RO'),
('Rússia', 'RU'),
('Ruanda', 'RW'),
('São Cristóvão e Nevis', 'KN'),
('Santa Lúcia', 'LC'),
('São Vicente e Granadinas', 'VC'),
('Samoa', 'WS'),
('San Marino', 'SM'),
('São Tomé e Príncipe', 'ST'),
('Arábia Saudita', 'SA'),
('Senegal', 'SN'),
('Sérvia', 'RS'),
('Seychelles', 'SC'),
('Serra Leoa', 'SL'),
('Cingapura', 'SG'),
('Eslováquia', 'SK'),
('Eslovênia', 'SI'),
('Ilhas Salomão', 'SB'),
('Somália', 'SO'),
('África do Sul', 'ZA'),
('Sudão do Sul', 'SS'),
('Espanha', 'ES'),
('Sri Lanka', 'LK'),
('Sudão', 'SD'),
('Suriname', 'SR'),
('Suécia', 'SE'),
('Suíça', 'CH'),
('Síria', 'SY'),
('Tajiquistão', 'TJ'),
('Tanzânia', 'TZ'),
('Tailândia', 'TH'),
('Togo', 'TG'),
('Tonga', 'TO'),
('Trinidad e Tobago', 'TT'),
('Tunísia', 'TN'),
('Turquia', 'TR'),
('Turcomenistão', 'TM'),
('Tuvalu', 'TV'),
('Uganda', 'UG'),
('Ucrânia', 'UA'),
('Emirados Árabes Unidos', 'AE'),
('Reino Unido', 'GB'),
('Estados Unidos', 'US'),
('Uruguai', 'UY'),
('Uzbequistão', 'UZ'),
('Vanuatu', 'VU'),
('Cidade do Vaticano', 'VA'),
('Venezuela', 'VE'),
('Vietnã', 'VN'),
('Iêmen', 'YE'),
('Zâmbia', 'ZM'),
('Zimbábue', 'ZW');

INSERT INTO pessoa (nome, cpf, data_Nascimento, tipo, sexo, id_pais)
VALUES('Marcos Evangelista de Morais', '2220002255', '1970-06-07', 'VOLUNTARIO', 'M', (SELECT id FROM pais WHERE sigla = 'BR'));

INSERT INTO pessoa (nome, cpf, data_Nascimento, tipo, sexo, id_pais)
VALUES('Manoel Francisco dos Santos', '77711122277', '1933-10-28', 'PESQUISADOR', 'M', (SELECT id FROM pais WHERE sigla = 'BR'));

INSERT INTO pessoa (nome, cpf, data_Nascimento, tipo, sexo, id_pais)
VALUES('Edson Arantes do Nascimento', '01012301010', '1940-10-23', 'PESQUISADOR', 'M', (SELECT id FROM pais WHERE sigla = 'BR'));


INSERT INTO vacina (nome, estagio, data_inicio, id_pesquisador, id_pais)
VALUES
('Vacina Alvorada', 'TESTE', '2023-01-10', (SELECT id FROM pessoa WHERE cpf = '77711122277'), (SELECT id FROM pais WHERE sigla = 'BR')),
('Vacina Pioneira', 'TESTE', '2023-02-05', (SELECT id FROM pessoa WHERE cpf = '01012301010'), (SELECT id FROM pais WHERE sigla = 'BR'));

INSERT INTO vacina (nome, estagio, data_inicio, id_pesquisador, id_pais)
VALUES
('Vacina Genesis', 'INICIAL', '2023-03-15', (SELECT id FROM pessoa WHERE cpf = '77711122277'), (SELECT id FROM pais WHERE sigla = 'BR')),
('Vacina Aurora', 'INICIAL', '2023-04-20', (SELECT id FROM pessoa WHERE cpf = '01012301010'), (SELECT id FROM pais WHERE sigla = 'BR'));

INSERT INTO vacina (nome, estagio, data_inicio, id_pesquisador, id_pais)
VALUES
('Vacina CoberturaTotal', 'APLICACAO_EM_MASSA', '2023-07-30', (SELECT id FROM pessoa WHERE cpf = '77711122277'), (SELECT id FROM pais WHERE sigla = 'BR')),
('Vacina Salvaguarda', 'APLICACAO_EM_MASSA', '2023-08-25', (SELECT id FROM pessoa WHERE cpf = '01012301010'), (SELECT id FROM pais WHERE sigla = 'BR'));



select * from pais;
select * from pessoa;
select * from vacina;
select * from aplicacao_vacina;

INSERT INTO aplicacao_vacina (id_pessoa, id_vacina, data_aplicacao, avaliacao)
VALUES (3, (SELECT id FROM vacina WHERE nome = 'Vacina Alvorada'), CURDATE(), 5);

ALTER TABLE vacina ADD COLUMN media_avaliacao DOUBLE DEFAULT 0;