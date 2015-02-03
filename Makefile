JCC = javac

all: InfoCrawler.class GetURLContentTest.class GetURLContent.class EmptyFileException.class SearchSetting.class

EmptyFileException.class: EmptyFileException.java
	$(JCC) EmptyFileException.java

GetURLContent.class: GetURLContent.java
	$(JCC) GetURLContent.java

GetURLContentTest.class: GetURLContentTest.java
	$(JCC) GetURLContentTest.java

SearchSetting.class: SearchSetting.java
	$(JCC) SearchSetting.java

InfoCrawler.class: InfoCrawler.java
	$(JCC) InfoCrawler.java

clean: 
	$(RM) *.class