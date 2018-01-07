DROP TABLE IF EXISTS drugs;
DROP TABLE IF EXISTS pkparameters;
DROP TABLE IF EXISTS pk;

--create databases
CREATE TABLE drugs (
  id int(11) NOT NULL,
  parent_id int(11) DEFAULT NULL,
  name varchar(500) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_DRUGS_PARENT_ID FOREIGN KEY (parent_id) REFERENCES drugs (id)
);

CREATE TABLE pkparameters (
  id int(11) NOT NULL,
  parent_id int(11) DEFAULT NULL,
  name varchar(500) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_PKPARAMETERS_PARENT_ID FOREIGN KEY (parent_id) REFERENCES drugs (id)
);

CREATE TABLE pk (
  id int(11) NOT NULL,
  source varchar(500) DEFAULT NULL,
  drug_id int(11) DEFAULT NULL,
  study_number varchar(500) DEFAULT NULL,
  study_number_old varchar(500) DEFAULT NULL,
  study_name varchar(500) DEFAULT NULL,
  species varchar(500) DEFAULT NULL,
  study_group varchar(500) DEFAULT NULL,
  number int(11) DEFAULT NULL,
  sex varchar(500) DEFAULT NULL,
  age varchar(500) DEFAULT NULL,
  route varchar(500) DEFAULT NULL,
  dose varchar(500) DEFAULT NULL,
  duration varchar(500) DEFAULT NULL,
  comment varchar(500) DEFAULT NULL,
  assay varchar(500) DEFAULT NULL,
  pk_analysys varchar(500) DEFAULT NULL,
  concomitant_drug varchar(500) DEFAULT NULL,
  parameter_final varchar(500) DEFAULT NULL,
  parameter_id int(11) DEFAULT NULL,
  parameter_comment varchar(500) DEFAULT NULL,
  value double DEFAULT NULL,
  sd double DEFAULT NULL,
  range1 double DEFAULT NULL,
  range2 double DEFAULT NULL,
  unit varchar(500) DEFAULT NULL,
  t varchar(500) DEFAULT NULL,
  file_name varchar(500) DEFAULT NULL,
  page int(11) DEFAULT NULL,
  radiolabelled varchar(500) DEFAULT NULL,
  metabolites_and_enantiomers varchar(500) DEFAULT NULL,
  comcomitant varchar(500) DEFAULT NULL,
  tissue_specific varchar(500) DEFAULT NULL,
  september varchar(500) DEFAULT NULL,
  february_april varchar(500) DEFAULT NULL,
  april_june varchar(500) DEFAULT NULL,
  june_august varchar(500) DEFAULT NULL,
  august_october varchar(500) DEFAULT NULL,
  october_december varchar(500) DEFAULT NULL,
  december_february_2017 varchar(500) DEFAULT NULL,
  december varchar(500) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_PK_DRUG_ID FOREIGN KEY (drug_id) REFERENCES drugs (id),
  CONSTRAINT FK_PK_PARAMETER_ID FOREIGN KEY (parameter_id) REFERENCES pkparameters (id)
);

-- Fill the database

insert into drugs(id, parent_id, name) values (-1, null, null);
insert into drugs(id, parent_id, name) values (7526, -1, 'Interferon Alfa-N3');
insert into drugs(id, parent_id, name) values (7527, -1, 'Lamivudine; Stavudine; Nevirapine');
insert into drugs(id, parent_id, name) values (7528, -1, 'Elbasvir; Grazoprevir');
insert into drugs(id, parent_id, name) values (7529, -1, 'Abacavir Sulfate');
insert into drugs(id, parent_id, name) values (7573, -1, 'Anxiolytics');
insert into drugs(id, parent_id, name) values (7574, 7573, 'Bentazepam');
insert into drugs(id, parent_id, name) values (1, -1, 'Bqwsd1');
insert into drugs(id, parent_id, name) values (2, 1, 'Bqwsd2');
insert into drugs(id, parent_id, name) values (3, 1, 'Bqwsd3');
insert into drugs(id, parent_id, name) values (4, 1, 'Bqwsd4');
insert into drugs(id, parent_id, name) values (5, 1, 'Bqwsd5');
insert into drugs(id, parent_id, name) values (6, 1, 'Bqwsd6');
insert into drugs(id, parent_id, name) values (7, 1, 'Bqwsd7');
insert into drugs(id, parent_id, name) values (8, 1, 'Bqwsd8');
insert into drugs(id, parent_id, name) values (9, 1, 'Bqwsd9');
insert into drugs(id, parent_id, name) values (10, 1, 'Bqwsd10');
insert into drugs(id, parent_id, name) values (11, 1, 'Bqwsd99');
insert into drugs(id, parent_id, name) values (12, 1, 'Kamist');
insert into drugs(id, parent_id, name) values (13, 1, 'Kamistads');

insert into pkparameters(id, parent_id, name) values (-1, null, null);
insert into pkparameters(id, parent_id, name) values (64, -1, 'Plasma protein binding');
insert into pkparameters(id, parent_id, name) values (68, -1, 'Bioavailability');
insert into pkparameters(id, parent_id, name) values (69, -1, 'Time values');
insert into pkparameters(id, parent_id, name) values (70, -1, 'Tmax');
insert into pkparameters(id, parent_id, name) values (1, -1, 'Reqtss1');
insert into pkparameters(id, parent_id, name) values (2, 1, 'Reqtss2');
insert into pkparameters(id, parent_id, name) values (3, 1, 'Reqtss3');
insert into pkparameters(id, parent_id, name) values (4, 1, 'Reqtss4');
insert into pkparameters(id, parent_id, name) values (5, 1, 'Reqtss5');
insert into pkparameters(id, parent_id, name) values (6, 1, 'Reqtss6');
insert into pkparameters(id, parent_id, name) values (7, 1, 'Reqtss7');
insert into pkparameters(id, parent_id, name) values (8, 1, 'Reqtss8');
insert into pkparameters(id, parent_id, name) values (9, 1, 'Reqtss9');
insert into pkparameters(id, parent_id, name) values (10, 1, 'Reqtss10');
insert into pkparameters(id, parent_id, name) values (11, 1, 'Reqtss99');
insert into pkparameters(id, parent_id, name) values (12, 1, 'Quali');
insert into pkparameters(id, parent_id, name) values (13, 1, 'Qualis');



insert into pk (id, source, drug_id, study_number, study_number_old, study_name, species, study_group, number, sex, age, route, dose, duration, comment, assay,
pk_analysys, concomitant_drug, parameter_final, parameter_id, parameter_comment, value, sd, range1, range2, unit, t, file_name, page, radiolabelled,
metabolites_and_enantiomers, comcomitant, tissue_specific, september, february_april, april_june, june_august, august_october, october_december,
december_february_2017, december) values
(1, null, 7529, null, 2, null, 'human', null, null, null, null, 'oral', 'unreported', 'unreported', null, null,
null, null, 'bioavailability', 68, null, 83, null, null, null, '%', null, 'WC500050162', 5, null,
null, null, null, 'KEEP', 'KEEP', 'KEEP', 'KEEP', 'KEEP', 'KEEP', 'KEEP', 'KEEP');

insert into pk (id, source, drug_id, study_number, study_number_old, study_name, species, study_group, number, sex, age, route, dose, duration, comment, assay,
pk_analysys, concomitant_drug, parameter_final, parameter_id, parameter_comment, value, sd, range1, range2, unit, t, file_name, page, radiolabelled,
metabolites_and_enantiomers, comcomitant, tissue_specific, september, february_april, april_june, june_august, august_october, october_december,
december_february_2017, december) values
(2, null, 7528, null, 2, null, 'human', null, null, null, null, 'oral', 'unreported', 'unreported', null, null,
null, null, 'plasma protein binding', 64, null, 49, null, null, null, '%', null, 'WC500050162', 5, null,
null, null, null, 'KEEP', 'KEEP', 'KEEP', 'KEEP', 'KEEP', 'KEEP', 'KEEP', 'KEEP');


insert into pk (id, source, drug_id, study_number, study_number_old, study_name, species, study_group, number, sex, age, route, dose, duration, comment, assay,
pk_analysys, concomitant_drug, parameter_final, parameter_id, parameter_comment, value, sd, range1, range2, unit, t, file_name, page, radiolabelled,
metabolites_and_enantiomers, comcomitant, tissue_specific, september, february_april, april_june, june_august, august_october, october_december,
december_february_2017, december) values
(3, null, 7527, null, 1, null, 'human', null, null, null, null, 'oral', 'unreported', 'unreported', null, null,
null, null, 'bioavailability', 68, null, 83, null, null, null, '%', null, 'WC500050343', 15, null,
null, null, null, null, null, null, null, 'KEEP', 'UPD', 'KEEP', null);

insert into pk (id, source, drug_id, study_number, study_number_old, study_name, species, study_group, number, sex, age, route, dose, duration, comment, assay,
pk_analysys, concomitant_drug, parameter_final, parameter_id, parameter_comment, value, sd, range1, range2, unit, t, file_name, page, radiolabelled,
metabolites_and_enantiomers, comcomitant, tissue_specific, september, february_april, april_june, june_august, august_october, october_december,
december_february_2017, december) values
(4, null, 7526, null, 1, null, 'human', null, null, null, null, 'oral', 'unreported', 'unreported', 'tablet', null,
null, null, 'Tmax', 68, null, 1.5, null, null, null, 'h', null, 'WC500050343', 15, null,
null, null, null, null, null, null, null, 'KEEP', 'KEEP', 'KEEP', null);

insert into pk (id, source, drug_id, study_number, study_number_old, study_name, species, study_group, number, sex, age, route, dose, duration, comment, assay,
pk_analysys, concomitant_drug, parameter_final, parameter_id, parameter_comment, value, sd, range1, range2, unit, t, file_name, page, radiolabelled,
metabolites_and_enantiomers, comcomitant, tissue_specific, september, february_april, april_june, june_august, august_october, october_december,
december_february_2017, december) values
(5, null, 7573, null, 1, null, 'human', null, null, null, null, 'oral', 'unreported', 'unreported', 'solution', null,
null, null, 'Tmax', 69, null, 1, null, null, null, 'h', null, 'WC500050343', 7, null,
null, null, null, null, null, null, null, 'KEEP', 'UPD', 'KEEP', null);

insert into pk (id, source, drug_id, study_number, study_number_old, study_name, species, study_group, number, sex, age, route, dose, duration, comment, assay,
pk_analysys, concomitant_drug, parameter_final, parameter_id, parameter_comment, value, sd, range1, range2, unit, t, file_name, page, radiolabelled,
metabolites_and_enantiomers, comcomitant, tissue_specific, september, february_april, april_june, june_august, august_october, october_december,
december_february_2017, december) values
(6, null, 7574, null, 1, null, 'human', null, null, null, null, 'oral', 'unreported', 'unreported', 'solution', null,
null, null, 'Tmax', 69, null, 1, null, null, null, 'h', null, 'WC500050343', 9, null,
null, null, null, null, null, null, null, null, 'UPD', null, null);



