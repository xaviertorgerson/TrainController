@echo off
title BUILD
echo Compiling TrainModel...
javac src/TrainModel/*.java -d ./build/
echo Compiling TrainController...
javac -cp "./build" src/TrainController/*.java  -d ./build/
echo Compiling TrackModel...
javac -cp "./build" src/TrackModel/*.java  -d ./build/
echo Compiling WaysideController...
javac -cp "./build;./src/CTCOffice" src/WaysideController/*.java  -d ./build/
echo Compiling CTCOffice...
javac -cp "./build" src/CTCOffice/*.java  -d ./build/
pause