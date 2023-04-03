LIB_PATH = lib
LIB_FILES = $(LIB_PATH)/jansi-2.4.0.jar

run: main
		java -classpath "$(LIB_PATH)/*:." Main
		make c

main:	*.java
		javac -classpath "$(LIB_FILES)" *.java

c:
		rm *.class
		rm Manifest.txt

check_man:
	@if [ ! -f Manifest.txt ]; then \
		echo "Creating Manifest.txt..."; \
		echo "Manifest-Version: 1.0" > Manifest.txt; \
		echo "Class-Path: $(LIB_FILES)" >> Manifest.txt; \
		echo "Main-Class: Main" >> Manifest.txt; \
	fi

jar:
		make main
		make check_man
		jar -cvfm TicTacToe.jar Manifest.txt *.class $(LIB_PATH)/
		make c

jar_run:
		java -jar TicTacToe.jar

tar:
		tar -cvz *.java -f TicTacToe.tar.gz

tar_unzip:
		tar -xzvf TicTacToe.tar.gz