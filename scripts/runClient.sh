# Run client
# java -Djava.security.policy="permissions.policy" -Djava.rmi.server.useCodebaseOnly=false -Djava.rmi.server.codebase=http://localhost:2000/ -cp bin/ client.MainClient
java -Djava.security.policy="permissions.policy" -cp bin/ client.MainClient # Without remote codebase system (working with rmiregistry but not with the client...)
