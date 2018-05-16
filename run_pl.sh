!#/bin/bash
mvn clean install
reset
cd target
var=`ls | grep .jar`
java -jar ${var} PL
