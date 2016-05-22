cd ..
cd bin
java -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy="../permissions.policy" client.MainClient
