#!/bin/bash

rm -r out/
javac -cp . -sourcepath src -d out/ src/*.java
cp -R assets/ out/assets