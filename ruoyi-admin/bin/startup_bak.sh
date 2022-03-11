#!/bin/sh
# script directory
SCRIPT_PATH=$(readlink -f $0)
# bin directory, It's like /opt/appname/bin, also same with SCRIPT_PATH
BIN_DIR=$(dirname $SCRIPT_PATH)
echo "BIN_DIR=$BIN_DIR"
# work directory
WORK_DIR=$(dirname $BIN_DIR)
echo "WORK_DIR=$WORK_DIR"
# where is app root path in location
APP_ROOT=`cat $BIN_DIR'/base.conf' | grep 'app.root.dir' | cut -d = -f 2`
echo "APP_ROOT=$APP_ROOT"
# app mainclass
MAINCLASS=`cat $BIN_DIR'/base.conf' |grep 'app.mainclass' | cut -d = -f 2`
echo "Execution=$MAINCLASS"
# where is app lib path in location
LIB_DIR=${APP_ROOT}'/lib/*'
# app conf directory
APP_CONF=${APP_ROOT}'/conf/'

if [ "$JAVA_HOME" = "" ]; then
        echo "JAVA_HOME not found."
        exit 1
fi
echo "Use JAVA_HOME=$JAVA_HOME"
JAVA_CMD=${JAVA_HOME}'/bin/java'
echo "JAVA_CMD=$JAVA_CMD"
JAVA_OPTIONS="-Xms1g -Xmx1g -XX:MaxPermSize=128m "
CLASSPATH="-cp ${APP_CONF}:${LIB_DIR}"
echo "CLASSPATH=$CLASSPATH"
$JAVA_CMD $JAVA_OPTIONS $CLASSPATH $MAINCLASS >> $WORK_DIR/logs/catalina.out 2>&1 &
JAVA_PID=$!
echo "$JAVA_PID">$BIN_DIR/server.pid