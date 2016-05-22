cd target
chmod +x runRMIRegistry.sh
./runRMIRegistry.sh &

cd ClassServer
chmod +x runClassServer.sh
./runClassServer.sh &

cd ../Server
chmod +x runServer.sh
./runServer.sh
