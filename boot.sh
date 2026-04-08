#!/bin/bash

javac -d out computer/*.java

jar cfm ComputerSimulation.jar MANIFEST.MF -C out .

chmod +x ComputerSimulation.jar

java -jar ./ComputerSimulation.jar
