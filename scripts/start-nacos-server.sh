#!/usr/bin/env bash



workdir=$(dirname $(cd $(dirname "$0"); pwd))

$(workdir)/start-net.sh

echo "current dir is: $workdir"

cd $workdir/lura-nacos-server

docker-compose up -d





