# Api de Registro BCI - Prueba técnica de evaluación
***

	Candidato:  David Ortiz Albornoz

## Contrato Swagger:
	https://app.swaggerhub.com/apis/davicho1979/RegisterUser_EvaluationAPI/1.0

## Pauta de Ejecución (Ambiente Local):

### Requisitos Previos
	* Java SDK 1.8 o superior
	* Gradle 6.6.1 o superior

### Base de datos

	No se incluyen script para generación de esquemas de datos para esta API.
	La base de datos viene implicita en memoria (H2) junto a esta aplicación

>spring.datasource.url=jdbc:h2:mem:bci_user_db

### Empaquetado de proyecto y resolución de dependencias(sin test)
>gradle clean build -x test

### Ejecución del proyecto
>gradle bootRun

Server Tomcat started on port(s): 8080 (http)
http://127.0.0.1:8080

### Ejecución de Test Unitarios
>gradle test