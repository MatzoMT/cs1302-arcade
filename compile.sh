#!/bin/bash -ex

mvn -q -e clean
mvn -q -e compile

export MAVEN_OPTS=-Dprism.order=sw;
mvn -q -e exec:java -Dexec.mainClass="cs1302.arcade.ChessDriver"
