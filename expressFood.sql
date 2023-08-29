
CREATE TABLE public.plat_du_jour (
                reference VARCHAR NOT NULL,
                categorie VARCHAR(100) NOT NULL,
                nom VARCHAR(100) NOT NULL,
                description VARCHAR(1000) NOT NULL,
                prix_unitaire_ht NUMERIC(10) NOT NULL,
                taux_tva_100 VARCHAR(6) NOT NULL,
                date_creation DATE NOT NULL,
                CONSTRAINT plat_du_jour_pk PRIMARY KEY (reference)
);


CREATE SEQUENCE public.livreur_id_seq;

CREATE TABLE public.livreur (
                id INTEGER NOT NULL DEFAULT nextval('public.livreur_id_seq'),
                nom VARCHAR(100) NOT NULL,
                prenom VARCHAR(100) NOT NULL,
                statut BOOLEAN NOT NULL,
                CONSTRAINT livreur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.livreur_id_seq OWNED BY public.livreur.id;

CREATE INDEX livreur_idx
 ON public.livreur
 ( nom );

CREATE TABLE public.client (
                numero VARCHAR NOT NULL,
                nom VARCHAR(100) NOT NULL,
                prenom VARCHAR(100) NOT NULL,
                numero_telephone VARCHAR(12) NOT NULL,
                adrese_email VARCHAR(50) NOT NULL,
                CONSTRAINT client_pk PRIMARY KEY (numero)
);


CREATE INDEX client_idx
 ON public.client
 ( nom );

CREATE SEQUENCE public.adresse_id_seq;

CREATE TABLE public.adresse (
                id INTEGER NOT NULL DEFAULT nextval('public.adresse_id_seq'),
                numero_voirie VARCHAR(3) NOT NULL,
                voie VARCHAR(100) NOT NULL,
                ville VARCHAR(100) NOT NULL,
                code_postale VARCHAR(5) NOT NULL,
                numero_client VARCHAR NOT NULL,
                CONSTRAINT adresse_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.adresse_id_seq OWNED BY public.adresse.id;

CREATE TABLE public.commande (
                numero_commande VARCHAR NOT NULL,
                date TIMESTAMP NOT NULL,
                client_numero VARCHAR NOT NULL,
                adresse_id INTEGER NOT NULL,
                CONSTRAINT commande_pk PRIMARY KEY (numero_commande)
);


CREATE TABLE public.ligne_panier_commande (
                plat_du_jour_reference VARCHAR NOT NULL,
                commande_numero VARCHAR NOT NULL,
                quantite INTEGER NOT NULL,
                prix_unitaire_ht NUMERIC(9,2) NOT NULL,
                taux_tva_100 NUMERIC NOT NULL,
                CONSTRAINT ligne_panier_commande_pk PRIMARY KEY (plat_du_jour_reference, commande_numero)
);


CREATE TABLE public.livraison (
                reference VARCHAR NOT NULL,
                statut VARCHAR(100) NOT NULL,
                date TIMESTAMP NOT NULL,
                heure TIME NOT NULL,
                liveur_id INTEGER NOT NULL,
                commande_numero VARCHAR NOT NULL,
                CONSTRAINT livraison_pk PRIMARY KEY (reference)
);


CREATE TABLE public.facture (
                numero VARCHAR NOT NULL,
                date_facture DATE NOT NULL,
                montant_TTC NUMERIC NOT NULL,
                livraison_reference VARCHAR NOT NULL,
                CONSTRAINT facture_pk PRIMARY KEY (numero)
);


ALTER TABLE public.ligne_panier_commande ADD CONSTRAINT plat_du_jour_ligne_panier_commande_fk
FOREIGN KEY (plat_du_jour_reference)
REFERENCES public.plat_du_jour (reference)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.livraison ADD CONSTRAINT livreur_livraison_fk
FOREIGN KEY (liveur_id)
REFERENCES public.livreur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commande ADD CONSTRAINT client_commande_fk
FOREIGN KEY (client_numero)
REFERENCES public.client (numero)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.adresse ADD CONSTRAINT client_adresse_fk
FOREIGN KEY (numero_client)
REFERENCES public.client (numero)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commande ADD CONSTRAINT adresse_commande_fk
FOREIGN KEY (adresse_id)
REFERENCES public.adresse (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.livraison ADD CONSTRAINT commande_livraison_fk
FOREIGN KEY (commande_numero)
REFERENCES public.commande (numero_commande)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ligne_panier_commande ADD CONSTRAINT commande_ligne_panier_commande_fk
FOREIGN KEY (commande_numero)
REFERENCES public.commande (numero_commande)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.facture ADD CONSTRAINT livraison_facture_fk
FOREIGN KEY (livraison_reference)
REFERENCES public.livraison (reference)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
