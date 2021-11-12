# Kata-Air-France

Test technique AirFrance pour Axileo.

Vous y retrouverez ci-joint les différentes feature demandées.

Le projet est sous une architecture monolithe réalisé sous Spring 2.5.5 En y exploitant une base de donnée H2.

# Information Base de données

Pour lancer le projet il vous suffit de lancer le serveur spring sous le port 8081. Pour accéder à la console H2 le lien est :

http://localhost:8081/h2-console/ 

JDBC URL: jdbc:h2:mem:mydb . Driver Class: org.h2.Driver . Credentials : sa | "" . 

Lors du lancement du programme des utilisateurs sont créés pour y tester directement les routes.

# Documentation

Vous disposez également d'une d'une documentation swagger pour consulter les routes.

http://localhost:8081/swagger-ui/

Le projet respecte toutes les contraintes demandées, vous y retrouverez également la partie AOP pour y analyser le temps 

d'exécution des requêtes HTTP.

Une fonction de gestion d'erreur est également implémentée avec un ErrorHandler adapté.

Importer la collection Postman pour les différentes routes afin de tester les fonctionnalités du code.

# Contact

Auteur : Axel ABOYEJI. Email : axel.aboyeji@axileo.fr
