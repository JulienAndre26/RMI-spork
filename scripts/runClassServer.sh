# Configuration
PORT=2000
CODEBASE_DIRS="../Server/bin;../Client/bin"

# Run class server
java -Djava.security.policy="permissions.policy" -cp bin/ classserver.ClassFileServer $PORT $CODEBASE_DIRS
