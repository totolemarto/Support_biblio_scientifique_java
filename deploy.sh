#!/bin/bash

if [[ $# -ne 1 ]]
then
    echo "Il faut un numéro pour la release !!"
    exit 1
fi

sed -i "7s/.*/    <version>$1-SNAPSHOT<\/version>/" pom.xml
mvn --batch-mode deploy
