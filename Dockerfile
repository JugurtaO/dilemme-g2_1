# Étape 1 : Construire l'application avec Maven
FROM maven:3.9.0-eclipse-temurin-17 AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et télécharger les dépendances
COPY  pom.xml .
COPY /server/pom.xml server/


# Télécharger les dépendances sans builder le projet (cela permet d'optimiser la construction en cache)
RUN mvn dependency:go-offline -f pom.xml

# Copier le reste du projet et builder l'application
COPY server/src server/src

# Lancer le build et repackage avec Spring Boot
RUN mvn   clean package spring-boot:repackage -DskipTests

# Étape 2 : Exécuter l'application avec une image Java Runtime
FROM eclipse-temurin:17-jdk-alpine

# Définir le répertoire de travail dans l'image finale
WORKDIR /app

# Copier le jar généré depuis l'étape de build
COPY --from=build /app/server/target/server-0.0.1-SNAPSHOT.jar /app/app.jar

# Exposer le port sur lequel l'application écoute
EXPOSE 8080

# Commande pour démarrer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
