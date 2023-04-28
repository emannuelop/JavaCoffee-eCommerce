-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');

INSERT INTO marca (nome, cnpj) VALUES ('três corações', '12.738.020/0001-02');
INSERT INTO marca (nome, cnpj) VALUES ('Nestle', '93.031.562/0001-93');

INSERT INTO produto (nome, preco, estoque, id_marca) VALUES ('Cafe Black Tucano', 34.95, 50, 2);
INSERT INTO produto (nome, preco, estoque, id_marca) VALUES ('Cafe Fazenda Floresta', 32.30, 35, 1);
INSERT INTO produto (nome, preco, estoque, id_marca) VALUES ('Cafe Orfeu', 29.00, 60, 2);

INSERT INTO cafe (metodopreparo, tipo, intensidade, id) VALUES ('sdfsgsdf', 'sfdgxdgxd', 1, 1);
INSERT INTO cafe (metodopreparo, tipo, intensidade, id) VALUES ('sdfsgsdf', 'sfdgxdgxd', 1, 2);
INSERT INTO cafe (metodopreparo, tipo, intensidade, id) VALUES ('sdfsgsdf', 'sfdgxdgxd', 2, 3);

INSERT INTO estado (nome, sigla) VALUES ('Acre', 'AC');
INSERT INTO estado (nome, sigla) VALUES ('Amazonas', 'AM');
INSERT INTO estado (nome, sigla) VALUES ('Pará', 'PA');
INSERT INTO estado (nome, sigla) VALUES ('Goiás', 'GO');
INSERT INTO estado (nome, sigla) VALUES ('Tocantins', 'TO');

INSERT INTO municipio (nome, id_estado) VALUES ('Manaus', 2);
INSERT INTO municipio (nome, id_estado) VALUES ('Palmas', 5);
INSERT INTO municipio (nome, id_estado) VALUES ('Guaraí', 5);
INSERT INTO municipio (nome, id_estado) VALUES ('Belém', 3);

INSERT INTO endereco (logradouro, bairro, numero, cep, id_municipio) VALUES ('alameda 12', 'Quadra 708 Sul', 'lote 10', '77082-012', 2);
INSERT INTO endereco (logradouro, bairro, numero, cep, id_municipio) VALUES ('avenida Bernado Sayão', 'Setor Aeroporto', 'número 3564', '77700-001', 3);
INSERT INTO endereco (logradouro, bairro, numero, cep, id_municipio) VALUES ('rua Piauí', 'Quadra 301 Norte', 'numero 102', '77030-030', 1);

INSERT INTO telefone (codigoarea, numero) VALUES ('011', '98456-7812');
INSERT INTO telefone (codigoarea, numero) VALUES ('061', '99901-5842');
INSERT INTO telefone (codigoarea, numero) VALUES ('061', '99933-0572');
INSERT INTO telefone (codigoarea, numero) VALUES ('063', '99933-0572');
INSERT INTO telefone (codigoarea, numero) VALUES ('078', '98203-3301');

INSERT INTO usuario (nome, email, senha, cpf, id_endereco, id_telefone_principal, id_telefone_opcional)
            VALUES ('João Aguiar', 'joao_aguia@gmail.com', 'joao1234', '09112332145',
                    1, 2, 1);

INSERT INTO usuario (nome, email, senha, cpf, id_endereco, id_telefone_principal)
            VALUES ('Maria Fernanda', 'mariaF@gmail.com', 'senha1234', '89114182345',
                    3, 3);

INSERT INTO usuario (nome, email, senha, cpf, id_endereco, id_telefone_principal, id_telefone_opcional)
            VALUES ('Paulo Vitor', 'paulo_gaymer@gmail.com', 'pa1000ulo', '19429301284',
                    2, 4, 5);

INSERT INTO avaliacao (data, estrela, id_produto, id_usuario) VALUES ('2023-01-22', 3, 1, 1);
INSERT INTO avaliacao (comentario, data, estrela, id_produto, id_usuario)
                VALUES ('muito bão', '2022-11-09', 5, 3, 2);
INSERT INTO avaliacao (data, estrela, id_produto, id_usuario) VALUES ('2023-02-08', 1, 2, 1);