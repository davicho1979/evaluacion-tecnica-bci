# Api de Registro BCI - Prueba t�cnica de evaluaci�n
***

	Candidato:  David Ortiz Albornoz

## Contrato Swagger:
	https://app.swaggerhub.com/apis/davicho1979/RegisterUser_EvaluationAPI/1.0

## Pauta de Ejecuci�n (Ambiente Local):

### Requisitos Previos
	* Java SDK 1.8 o superior
	* Gradle 6.6.1 o superior
	
### Clonar Repositorio	

>git clone https://github.com/davicho1979/evaluacion-tecnica-bci.git

### Base de datos

	No se incluyen script para generaci�n de esquemas de datos para esta API.
	La base de datos viene implicita en memoria (H2) junto a esta aplicaci�n

>spring.datasource.url=jdbc:h2:mem:bci_user_db

### Empaquetado de proyecto y resoluci�n de dependencias(sin test)
>gradle clean build -x test

### Ejecuci�n del proyecto
>gradle bootRun

Server Tomcat started on port(s): 8080 (http)
http://127.0.0.1:8080

### Ejecuci�n de Test Unitarios
>gradle test