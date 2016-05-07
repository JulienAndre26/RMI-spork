# Run client
java -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy="permissions.policy" -Djava.rmi.server.codebase=http://localhost:2000/ -cp bin/ client.MainClient
