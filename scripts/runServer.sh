# RMI Registry
cd bin/
echo 'Running RMI Registry...'
rmiregistry 8082 &
cd ../

# Run server
java -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy="permissions.policy" -cp bin/ -Djava.rmi.server.codebase=http://localhost:2000/ collection.MainServer
