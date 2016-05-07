# RMI Registry
cd bin/
echo 'Running RMI Registry...'
rmiregistry 8082 &
cd ../

# Run server
java -Djava.security.policy="permissions.policy" -cp bin/ collection.MainServer
