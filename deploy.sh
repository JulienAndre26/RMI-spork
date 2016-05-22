#!/bin/sh

# Directories
BIN_DIR=./bin/
OUTPUT_DIR=./target/
CLASSSERVER_DIR=$OUTPUT_DIR/ClassServer
SERVER_DIR=$OUTPUT_DIR/Server
CLIENT_DIR=$OUTPUT_DIR/Client

# Clean output directory
echo 'Clean output directory...'
rm -rf $OUTPUT_DIR 2>/dev/null

# Class Server
echo 'Deploy class server...'
mkdir -p $CLASSSERVER_DIR/bin
cp -R $BIN_DIR/classserver $CLASSSERVER_DIR/bin
cp ./scripts/runClassServer.sh permissions.policy $CLASSSERVER_DIR && chmod +x $CLASSSERVER_DIR/runClassServer.sh

# Server
echo 'Deploy server...'
mkdir -p $SERVER_DIR/bin
cp -R $BIN_DIR/collection $SERVER_DIR/bin
cp ./scripts/runServer.sh permissions.policy $SERVER_DIR && chmod +x $SERVER_DIR/runServer.sh

# Client
echo 'Deploy client...'
mkdir -p $CLIENT_DIR/bin
cp -R $BIN_DIR/client $CLIENT_DIR/bin
cp ./scripts/runClient.sh permissions.policy $CLIENT_DIR && chmod +x $CLIENT_DIR/runClient.sh
mkdir -p $CLIENT_DIR/bin/collection                              # Need to add the codebase, because for mysterious
cp -R $BIN_DIR/collection/interfaces $CLIENT_DIR/bin/collection  # reason, rmi.codebase not working with the client
cp -R $BIN_DIR/collection/database $CLIENT_DIR/bin/collection    # (but it does with rmiregistry)

# Deploy RMI Registry script
echo 'Deploy RMI Registry script...'
cp ./scripts/runRMIRegistry.sh $OUTPUT_DIR/runRMIRegistry.sh && chmod +x $OUTPUT_DIR/runRMIRegistry.sh

# Some informaitons
echo "Deployment done in $OUTPUT_DIR"
echo 'Running order : ClassServer > RMI Registry > Server > Client'
