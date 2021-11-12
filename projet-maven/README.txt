Kata Air France

Test technique AirFrance pour Axileo.

Vous y retrouverez ci-joint les diff�rentes feature demand�es.

Le projet est sous une architecture monolithe r�alis� sous Spring 2.5.5 En y exploitant une base de donn�e H2.

Information Base de donn�es

Pour lancer le projet il vous suffit de lancer le serveur spring sous le port 8081. Pour acc�der � la console H2 le lien est :

http://localhost:8081/h2-console/ 

JDBC URL: jdbc:h2:mem:mydb . Driver Class: org.h2.Driver . Credentials : sa | "" . 

Lors du lancement du programme des utilisateurs sont cr��s pour y tester directement les routes.

Documentation

Vous disposez �galement d'une d'une documentation swagger pour consulter les routes.

http://localhost:8081/swagger-ui/

Le projet respecte toutes les contraintes demand�es, vous y retrouverez �galement la partie AOP pour y analyser le temps 

d'ex�cution des requ�tes HTTP.

Une fonction de gestion d'erreur est �galement impl�ment�e avec un ErrorHandler adapt�.

Contact

Auteur : Axel ABOYEJI. Email : axel.aboyeji@axileo.fr
