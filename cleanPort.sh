rmipid=$(ps -C rmiregistry|grep rmiregistry|awk '{print $1}')
kill $rmipid
