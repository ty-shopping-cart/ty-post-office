# Post Office 
This microservice consume UserNotificationMessage topic :
* Writes notifications to cassandra
* Send email to users

## Tech Stack
* Java JDK 11
* Maven 3.8.1
* Spring Boot v2.9.2
* Kafka 2.8.0
* Cassandra
* Swagger
* Spring Email

## Running the Application
#### Build the app
`mvn clean install`

### Download and Install Kafka
https://kafka.apache.org/downloads

https://www.youtube.com/watch?v=EUzH9khPYgs

### Run Kafka
`.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties`

`.\bin\windows\kafka-server-start.bat .\config\server.properties`

Create Test Topic:

`.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic TestTopic`

### Up Cassandra DB
`docker pull cassandra`

`docker run --name cassandra -p 9042:9042 -d cassandra`

Go to Cassandra panel and run `cqlsh` command

In the cqlsh `describe keyspaces;`

`CREATE KEYSPACE mykeyspace WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};`


## Application Details

Once application is up, api documentation can be seen at `http://localhost:8084/swagger-ui.html`

### Sample Endpoint

#### [POST] /email/ (sendEmail)

```
Body: 
{
  "message": "Sepetenizdeki ürünün fiyatı düştü. Acele edin!",
  "subject": "Ürün Fiyat Güncellemesi",
  "to": "test@gmail.com"
}
```

