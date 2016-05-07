# Configuration
PORT=2000
SERVER_DIR=../Server/bin

# Run class server
java -Djava.security.policy="permissions.policy" -cp bin/ classserver.ClassFileServer $PORT $SERVER_DIR
