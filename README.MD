Api de Registro - Prueba t�cnica de evaluaci�n

	Candidato:  David Ortiz Albornoz

Contrato Swagger:
	https://app.swaggerhub.com/apis/davicho1979/RegisterUser_EvaluationAPI/1.0

Pauta de Ejecuci�n (Ambiente Local):

Requisitos Previos
	Java SDK 1.8 o superior
	Gradle 6.6.1 o superior

###Empaquetado de proyecto y resoluci�n de dependencias(sin test)
">gradle clean build -x test"

###Ejecuci�n del proyecto
">gradle bootRun"

Server Tomcat started on port(s): 8080 (http)
http://127.0.0.1:8080

###Ejecuci�n de Test Unitarios
">gradle test"