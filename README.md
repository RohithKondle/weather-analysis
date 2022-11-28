# weather-analysis
This project deals with fetching the data from real-time weather API and produce asynchronous Kafka messages to consumer application

## Run the Zookeeper from kafka root folder.
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

## Start the Kafka server from kafka root folder 
.\bin\windows\kafka-server-start.bat .\config\server.properties

