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
INSERT INTO estado (nome, sigla) VALUES ('Tocantins', 'TO');

INSERT INTO municipio (nome, id_estado) VALUES ('Manaus', 2);
INSERT INTO municipio (nome, id_estado) VALUES ('Palmas', 4);
INSERT INTO municipio (nome, id_estado) VALUES ('Guaraí', 4);
INSERT INTO municipio (nome, id_estado) VALUES ('Belém', 3);