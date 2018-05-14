!#/bin/bash

set -e

mvn clean install
reset
java -jar target/ox-game-project-1.0.jar
