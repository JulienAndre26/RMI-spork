cd ..
cd bin
rmiregistry 8082 &
java -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy="../permissions.policy" collection.MainServer
