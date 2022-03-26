all:
	rm -r out/
	javac -cp . -sourcepath src -d out/ src/*.java
	cp -R assets/ out/assets

run:
	rm -r out/
	javac -cp . -sourcepath src -d out/ src/*.java
	cp -R assets/ out/assets
	java -cp out/ Main
