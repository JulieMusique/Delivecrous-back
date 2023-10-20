# Delivecrous-back
# Projet de Commande de Repas - Backend

Ce dépôt contient le code source de la partie backend de l'application de commande de repas cross-plateforme.

## Technologies Utilisées
- Framework : Spring Boot
- Base de données : H2 (embarquée)
- Langage : Java

## Structure du projet
![Capture d'écran](architectureApp.PNG)

## Architecture du Projet
Structure du projet Spring Boot et l'organisation des packages.

## Modèles de Données
Description des entités de votre base de données, comme les plats et les utilisateurs, et leurs relations.

## Fonctionnalités des Web Services
- Web services REST pour la gestion des plats, utilisateurs et commandes
- Endpoints pour lister les plats, ajouter au panier, passer une commande, etc.

## Instructions pour Exécuter le Projet
1. Clonez ce dépôt : `git clone [lien du dépôt]`
2. Ouvrez le projet dans votre IDE Java préféré
3. Exécutez l'application : Réalisez mvn clean install puis cherchez la classe principale "DelivecrousApp.java" dans le dossier "src\main\java\com\imt\framework\web\delivecrous\DelivecrousApplication.java" et lancez-la en tant qu'application Java
4. Le serveur démarrera à l'adresse : `http://localhost:[port]`
