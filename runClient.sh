cd bin
java -cp client.MainClient;activemq-all-5.9.1.jar;. -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy="../permissions.policy" client.MainClient
