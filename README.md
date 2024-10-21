Author: [Jugurta.O](https://github.com/JugurtaO/) - M1 MIAGE
# Template Dilemme du Prisonnier
The Prisoner's Dilemma is a web application (SPA) developed to initiate games and play as many times as you like with another player.
## Technical Stack & App architecture
#### Backend
* Java & Spring Boot 
* Maven for dependency management
* Junit5 for unit tests
* Lombok for method creation automation
* REST API architecture style
* Hexagonal architecture
* CI (Continuous Intégration).
#### Frontend
* Angular 17

## Patterns & Components
* Factory for strategies implementation : The pattern is Singleton based pattern
* State for GameEncounter state handling
* Inversion of control (Ioc) through dependecy injection 

## Overview of how the game logic works !
L'application est une Single Page Application: 
  * Server Frontend en Angular
  * Server Backend en Spring Boot

L'application est basée sur une ***API REST + WebSockets***

***Gestion de l’état de la partie avec l’API REST & Connexion des joueurs :***

Lorsqu’une partie est créée, on garde une structure de données  en mémoire qui stocke l’état de la partie et ses joueurs asosciés.

Exemples d'états :
*En attente* : Un joueur a rejoint, mais le deuxième n'est pas encore là.
*En cours* : Les deux joueurs sont là, et les décisions sont en attente.
*Terminée* : Les résultats sont calculés.

API REST pour l’interaction initiale  & Mise à jour en temps réel:
Lorsque les joueurs se connectent, ils peuvent faire une requête à l’API REST (ex : [HTTP POST --> /join) pour indiquer qu’ils veulent participer à une partie.
Lorsqu'on gère cette requête on vérifie s'il existe déjà un joueur dans la partie créee:
- si OUI, la requête concerne le deuxième joueur qui vient d'arriver donc on l'enregistre en tant que J2 et on change l'état de la partie à 'En cours' et on notifie via la WebSocket.
- si NON, la requête concerne alors le premier joueur et donc le joueur qui a initié la partie, on l'enregistre alors en tant que J1 et on change l'état de la partie à 'En attente'et on notifie via la WebSocket.

Chaque joueur est identifié par un ID au moment de la connexion (soit J1 soit J2) qui lui est transmis en retour à la requête POST /join.

***Gestion des étapes du jeu :***
Une fois les joueurs sont connectés, la partie est en cours. Le serveur frontend garde la trace du nombre de tours jouées.
À chaque tour, chaque joueur soumet une réponse (coopérer ou trahir), envoyée via la WebSocket au server.
Le server écoute les actions et enregistre les réponses des joueurs: comment savoir quelle réponse est à qui ?
Comme indiqué précédemment, au moment de la connexion de chaque joueur, le server renvoie l'ID de celui-ci qui est transmis avec sa décision à chaque tour.

Après chaque tour, les scores des 2 joueurs sont calculés et envoyés à travers la WebSocket au front qui met à jour l'affichage.

Le frontend coupe la connexion de la WebSocket quand le nombre maximal de tours est atteint et notifie le backend avec un appel API (ex: HTTPO GET -> /scores)
qui calcule les scores finaux  des deux joueurs, les renvoie et met à jour l'état de la partie à 'Terminée'.


***Le mixte entre API REST & WebSockets permet de répondre en temps réel aux requêtes des joeurs en les notifiant de l'état de la partie mais aussi du fait que le joueur adverse a joué. 
Cette approche permet également de gérer l'accès à l'information (API REST) dans le cas ou on décide de garder un historique de parties en mémoire, ex: rechercher une partie par ID ou son score.***


### Technically, how the app should works ?
Spring offre un mécanisme très puissant d'**inversion de contrôle** (Ioc) à travers l'**injection de dépendances**.
Pour exploiter ce mécanisme, on crée un **Bean** (une classe annotée **@Service**) qui continedra la structure de données (Liste, HashMap, etc) des parties.
Cette dernière serait injectée dans les classes manipulant cette structure de données. (MAJ d'une partie, création d'une nouvelle partie, MAJ d'un score etc ...).

Exemples:
* Injection dans le controller surveillant l'endpoint (POST /join): lorsqu'un joueur rejoint la partie, on utilise l'instance du service pour mettre à jour l'état de la partie.
* Injection dans la classe de la WebSocket qui écoute les actions des joueurs et qui utilise donc ce service pour calculer les scores.

Le **service** créé est un **Singleton** partagé dans toute l'application (chargé dans le **Application Context** après le démarrage du server Spring). 
Toutes les méthodes de gestion d'une partie modifient la même instance de service et cela nous facilite énormément la tâche de gérer une partie en mémoire..


