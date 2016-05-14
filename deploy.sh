EXPECTED_ARGS=1
NOW=$(date +"%Y%m%d%H%M%S")

if [ $# -lt $EXPECTED_ARGS ]
then
  echo "Usage: `basename $0` <file>"
  exit $E_BADARGS
fi

CONTEXT=app
FILE=$1
SERVER=root@192.81.222.241

REMOTE_FILE=/tmp/$CONTEXT-$NOW.war
REMOTE_WEBAPPS=/var/lib/tomcat7/webapps
REMOTE_WAR="/tmp/$CONTEXT.war"

START_TOMCAT="service tomcat7 start"
STOP_TOMCAT="service tomcat7 stop"m


# Copy local war to remote machine
copy_file() {
        log "Copying $FILE to $SERVER:$REMOTE_FILE"
        scp $FILE $SERVER:$REMOTE_FILE
}

# Stops tomcat on remote machine
stop_server() {
        log "Stopping remote tomcat"
        ssh $SERVER supervisorctl stop fixmycity
}

# Start tomcat on remote machine
start_server() {
        log "Starting remote tomcat"
        ssh $SERVER supervisorctl start fixmycity
}

# Copy remote temporary file to deployment path
deploy_file() {
        log "Deleting old version"
        ssh $SERVER sudo rm -rf $REMOTE_WAR

        log "Copying temporary file to $REMOTE_WAR"
        ssh $SERVER sudo mv $REMOTE_FILE $REMOTE_WAR
}

log() {
    echo "$(date +"%Y-%m-%d %H:%M:%S"): $1"
}

log "Deploying $FILE to $SERVER/$CONTEXT"

copy_file
stop_server
deploy_file
start_server

log "Done deploying."