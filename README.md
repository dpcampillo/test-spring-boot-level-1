# Importar Proyecto Maven

# Cobertura del codigo (Test) 
	-Aproximadamente 70% de cobertura entre pruebas de integracion y unitarias

# Importar Schema de base de datos
	- En este caso se utilizo mysql version 8.0.12 MySQL Community Server - GPL
	
# Correr la aplicacion
	- Se debe poner a correr la aplicacion para tener acceso a los diferentes endpoints
	- El path bade la aplicacion despues de correr localmente es http://localhost:8080/api
	
# EndPoints Disponibles
	
	 - "{[/accounts],methods=[POST]}"
    - "{[/accounts/{id}],methods=[DELETE]}"
    - "{[/accounts],methods=[GET]}"
    - "{[/accounts/{id}],methods=[GET]}"
    - "{[/accountholders/{id}],methods=[DELETE]}"
    - "{[/accountholders],methods=[GET]}"
    - "{[/legalpersons/{id}],methods=[PUT]}"
    - "{[/legalpersons],methods=[POST]}"
    - "{[/legalpersons/{id}],methods=[GET]}"
    - "{[/movements],methods=[POST]}"
    - "{[/movements],methods=[GET]}"
    - "{[/movements/{id}],methods=[GET]}"
    - "{[/physicalpersons/{id}],methods=[PUT]}"
    - "{[/physicalpersons],methods=[POST]}"
    - "{[/physicalpersons/{id}],methods=[GET]}"
    - "{[/register],methods=[POST]}"
    - "{[/error]}")
    
# Probando Endpoints

	- Post Register

curl --request POST \
  --url http://localhost:8080/api/register \
  --header 'content-type: application/json' \
  --data '{
	"username": "junalcarlos",
	"password": "123456"
}'


	- Post Login
	
curl --request POST \
  --url http://localhost:8080/api/login \
  --header 'content-type: application/json' \
  --data '{
	"username": "dario",
	"password": "123456"
}'

	- Post PhysicalPerson

curl --request POST \
  --url http://localhost:8080/api/physicalpersons \
  --header 'authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYXJpbyIsIkNMQUlNX1RPS0VOIjoiUk9MRV9BRE1JTiIsImlhdCI6MTU2MjE2NDQxNiwiaXNzIjoiSVNTVUVSIiwiZXhwIjoxNTYyMTkzMjE2fQ.a-nKSQnCTVYTrYxZzqZWe3sQuyDMeDgML1EAGwjCvJs' \
  --header 'content-type: application/json' \
  --data '{
	"rut": "1051445885",
	"firstname": "Dario",
	"lastname": "Perez",
	"identification": "1234567890"
}'

	- Post LegalPerson
curl --request POST \
  --url http://localhost:8080/api/legalpersons \
  --header 'authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYXJpbyIsIkNMQUlNX1RPS0VOIjoiUk9MRV9BRE1JTiIsImlhdCI6MTU2MjE2NDQxNiwiaXNzIjoiSVNTVUVSIiwiZXhwIjoxNTYyMTkzMjE2fQ.a-nKSQnCTVYTrYxZzqZWe3sQuyDMeDgML1EAGwjCvJs' \
  --header 'content-type: application/json' \
  --data '{
	"rut": "105144588531",
	"businessname": "Dario",
	"founded": 1990
}'
	

-- ---------------------------------------------------------------------------------------

## Herramientas necesarias:

	- GIT     ( Version homologada: 2.11.0 )
	- Maven   ( Version homologada: 3.3.9 )
	- Java    ( Version homologada: 1.8.0_181, vendor: Oracle Corporation )
	- Eclipse ( Version homologada: Photon Release (4.8.0) )
	- curl    ( Version homologada: 7.52.1 )


## Comando para ejecutar la aplicacion base utilizando Maven:

	mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=dev"


## Setup del proyecto en Eclipse:

	- Importar como proyecto maven existente.
	- Abrir vista "Boot Dashboard".
	- Click derecho sobre el proyecto + "(Re)start" / "(Re)debug".
	
## Workflow:
	
	- Validar funcionamiento del proyecto básico provisto ejecutando la aplicacion via Maven y utilizando los requests de prueba (sección "Probando Controllers")
	mediante la utilizacion de "curl". Puede utilizarse cualquier otra herramienta en lugar de ejecutar "curl" por consola.
	
	- Desde Eclipse, importar el proyecto (importar como proyecto Maven existente).
	
	- Resolver los ejercios indicados. Ver enuciados en archivo "EJERCICIOS.md".
	
	- Agregar un archivo llamado "RESPUESTAS.md" que contenga todas las "curl requests" que permitan probar las funcionalidades creadas. 
	
	- Crear nuevo proyecto git (en GitHub, BitBucket o similar). Pushear en este nuevo espacio el proyecto con todos los ejercicios resueltos y enviar la url del repositorio 
	para revisión de las soluciones implementadas.  
	
	- Asegurarse de que los permisos de acceso al repositorio sean adecuados para que quien reciba la url pueda realizar el clonado.
	
	
	
	
