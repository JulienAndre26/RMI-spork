cd target

cd ClassServer
chmod +x runClassServer.sh
./runClassServer.sh &

sleep 3  # Waits 5 seconds.

cd ..
chmod +x runRMIRegistry.sh
./runRMIRegistry.sh &

sleep 1  # Waits 5 seconds.

cd Server
chmod +x runServer.sh
./runServer.sh
