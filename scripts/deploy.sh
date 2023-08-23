#!/usr/bin/env bash

mvn clean package

echo 'Copy files..'

scp -i ~/.ssh/id_rsa \
  target/OfficeSpring-1.0-SNAPSHOT.jar \
  ghost@192.168.0.165:/home/ghost/

echo 'Restart server..'

ssh -i ~/.ssh/id_rsa ghost@192.168.0.165 << EOF

pgrep java | xargs kill -9
nohup java -jar -D"jasypt.encryptor.password"="$1" OfficeSpring-1.0-SNAPSHOT.jar > log.txt &

EOF

echo 'Bye'