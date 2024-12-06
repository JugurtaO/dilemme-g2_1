Author: [Jugurta.O](https://github.com/JugurtaO/) - M1 MIAGE
# Template Dilemme du Prisonnier
The Prisoner's Dilemma is a web application (SPA) developed to initiate games and play as many times as you like with another player.
## Technical Stack & App architecture
#### Backend
* Java & Spring Boot 
* Spring boot Web Sockets
* Docker for deployment.
* Nginx as a reverse proxy
* Maven for dependency management
* Junit5 for unit tests
* Lombok for method creation automation
* REST API architecture style
* Hexagonal architecture
* CICD for Continuous Intégration & Continuous deployment.
#### Frontend
* Angular 17

## Patterns & Components
* Factory for strategies implementation : The pattern is Singleton based pattern
* State for GameEncounter state handling
* Adapter pattern for using external jar strategies file.
* Inversion of control (Ioc) through dependecy injection 

## Overview of how the game logic works !
The app is an SPA : 
  * Angular Frontend
  * Spring Boot Backend Server


### To run the app locally with docker
```angular2html
1.docker compose -f docker-compose-dev.yml down -v
2.docker compose -f docker-compose-dev.yml up -d 
```

### Access the app via Internet:
```angular2html
The application is deployed in an AWS EC2 instance.
```
```angular2html
15.188.52.15
```