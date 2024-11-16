Author: [Jugurta.O](https://github.com/JugurtaO/) - M1 MIAGE
# Template Dilemme du Prisonnier
The Prisoner's Dilemma is a web application (SPA) developed to initiate games and play as many times as you like with another player.
## Technical Stack & App architecture
#### Backend
* Java & Spring Boot 
* Spring boot Web Sockets
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


### To run the app locally with docker
```angular2html
1.docker compose -f docker-compose-dev.yml down -v
2.docker compose -f docker-compose-dev.yml up -d 
```