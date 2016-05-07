# Run client
java -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy="permissions.policy" -cp bin/ -Djava.rmi.server.codebase=http://localhost:2000/ client.MainClient
