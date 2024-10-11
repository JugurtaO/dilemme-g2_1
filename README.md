# Template Dilemme du Prisonnier

Ce template va avoir deux mains différentes :
* `Main` -> pour une application Java simple
* `PrisonersDilemmaApp` -> le nom complet de la seconde application doit être ici

## SonarQube Analysis 
```angular2html
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=dilemme-g2_1 \
  -Dsonar.host.url=http://im2ag-sonar.u-ga.fr:9000 \
  -Dsonar.login=e66c949af166e3e45edc1f97529515f306fcd7b0
```
