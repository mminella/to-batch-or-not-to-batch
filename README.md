# Monolithic Integration Application

* Comment out all other `@Configuration` annotations
* Uncomment out the `@Configuration` annotation on `IntegrationConfiguration`
* Delete the `spring_batch` MySql db
* Create the `spring_batch` MySql db
* Run the Boot application

# Spring Cloud Stream Applications

* `rabbitmq-server`
* `delete from foo;`
* `cd /Users/mminella/Documents/IntelliJWorkspace/to-batch-or-not-to-batch/bin`
* `java -jar jdbc-sink-rabbit-2.1.4.BUILD-SNAPSHOT.jar --jdbc.tableName=foo --jdbc.columns=item_id:headers[sequenceNumber],first:one,second:two,third:three --spring.datasource.driver-class-name=org.mariadb.jdbc.Driver --spring.datasource.url='jdbc:mysql://localhost:3306/spring_batch' --spring.datasource.username=root --spring.datasource.password=p@ssw0rd --spring.cloud.stream.bindings.input.destination=messageParser-out-0 --logging.level.org.springframework.integration=DEBUG`
* `cd /Users/mminella/Documents/IntelliJWorkspace/to-batch-or-not-to-batch/csv-to-map-transformer/target`
* `java -jar csv-to-map-transformer-0.0.1-SNAPSHOT.jar --spring.cloud.stream.bindings.input.destination=messageParser-in-0 --spring.cloud.stream.bindings.output.destination=messageParser-out-0 --server.port=8585 --logging.level.org.springframework.integration=DEBUG`
* `cd /Users/mminella/Documents/IntelliJWorkspace/to-batch-or-not-to-batch/bin`
* `java -jar file-source-rabbit-2.1.1.RELEASE.jar --file.consumer.mode=lines --file.directory=/Users/mminella/Documents/IntelliJWorkspace/to-batch-or-not-to-batch/source --file.filename-pattern=*.txt --trigger.fixed-delay=60 --server.port=9090 --spring.cloud.stream.bindings.output.destination=messageParser-in-0 --logging.level.org.springframework.integration=DEBUG`

# Batch Application

* 


# Troubleshooting

* `java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 -jar jdbc-sink-rabbit-2.1.4.BUILD-SNAPSHOT.jar --jdbc.tableName=foo --jdbc.columns=item_id:headers[sequenceNumber],first:one,second:two,third:three --spring.datasource.driver-class-name=org.mariadb.jdbc.Driver --spring.datasource.url='jdbc:mysql://localhost:3306/spring_batch' --spring.datasource.username=root --spring.datasource.password=p@ssw0rd --spring.cloud.stream.bindings.input.destination=messageParser-out-0 --logging.level.org.springframework.integration=DEBUG`
* `cd /Users/mminella/Documents/IntelliJWorkspace/to-batch-or-not-to-batch/bin`
* `java -jar log-sink-rabbit-2.1.1.RELEASE.jar --spring.cloud.stream.bindings.input.destination=messageParser-out-0 --logging.level.org.springframework.integration=DEBUG --log.expression=payload.one`
