INSERT INTO utilisateur (cmatricule, lnom, lprenom) VALUES ('PTZO765', 'NomTest', 'PrenomTest');

INSERT INTO agent (lmail, ccivilite, dentree_poste, dnaissance, cgrade, cmatricule, lnom, ntelephone, lprenom) VALUES ('alice.bouchard@laposte.fr', '2', '1984-04-12', '1965-02-24', 'ACC23', 'PKDC976','BOUCHARD', '0243297947', 'ALICE');

INSERT INTO nature (cnature, lnature) VALUES (41, 'ALLOCATION');
INSERT INTO nature (cnature, lnature) VALUES (01, 'COMMUNICATION');
INSERT INTO nature (cnature, lnature) VALUES (21, 'ESTIMATION');
INSERT INTO nature (cnature, lnature) VALUES (22, 'RETRAITE');
INSERT INTO nature (cnature, lnature) VALUES (61, 'REQUETE');


INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('ATI', 60, 'ATI', 1);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('TIERCEPERS', 60, 'Tierce Personne', 1);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('MAIL', 3, 'Mail', 2);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('COURRIER', 5,'Courrier', 2);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('TELEPHONE', 1,'Téléphone', 2);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('AMENAGEMENT', 15,'Aménagement de fin d\'activité', 2);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('AGELEGAL', 15,'A l\'âge légal', 3);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('TRAVHAND', 9,'Travailleur handicapé', 3);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('SERVACTIF', 15,'Service Actif', 3);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('ESTINVALIDE', 15,'INV', 3);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('TROISENFANTS', 15,'Parent de 3 enfants / enfant handicapé', 3);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('CARRLONGUE', 15,'Carrière longue', 3);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('SRE', 9,'Entretien SRE', 3);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('CARRLONGUESENIOR', 15,'Carrière longue avec dispositif sénior', 3);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('RETCASGENERAL', 30,'Cas général', 4);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('RETINVALIDITE', 5,'Invalidité', 4);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('RETREVERSION', 5,'Réversion', 4);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('SORTANTE', 1,'Sortante', 2);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('DIVERS', 60,'Divers', 5);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('VALIDATION', 30,'Validation', 5);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('COORDINATION', 5,'Coordination', 5);
INSERT INTO type_demande (ctype, echeance, ltype, id_nature) VALUES ('LIMITEAGE', 15,'Limite d\'âge', 5);

INSERT INTO origine (lorigine) VALUES ('BATCH');
INSERT INTO origine (lorigine) VALUES ('MAIL');
INSERT INTO origine (lorigine) VALUES ('TELEPHONE');
INSERT INTO origine (lorigine) VALUES ('COURRIER');