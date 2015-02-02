JCC = javac

all: InfoCrawler.class GetURLContentTest.class GetURLContent.class EmptyFileException.class

EmptyFileException.class: EmptyFileException.java
	$(JCC) EmptyFileException.java

GetURLContent.class: GetURLContent.java
	$(JCC) GetURLContent.java

GetURLContentTest.class: GetURLContentTest.java
	$(JCC) GetURLContentTest.java

InfoCrawler.class: InfoCrawler.java
	$(JCC) InfoCrawler.class

clean: 
	$(RM) *.class