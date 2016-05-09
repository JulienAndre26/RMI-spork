# Run RMI Registry
echo 'Running RMI Registry...'
rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false -J-Djava.rmi.server.codebase=http://localhost:2000/ 8082
