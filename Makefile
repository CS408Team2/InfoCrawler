JCC = javac

all: InfoCrawler.class GetURLContentTest.class GetURLContent.class EmptyFileException.class SearchSetting.class RegularExpressionSearch.class WordByWordSearch.class MyThread.class

MyThread.class: MyThread.java
	$(JCC) MyThread.java

EmptyFileException.class: EmptyFileException.java
	$(JCC) EmptyFileException.java

GetURLContent.class: GetURLContent.java
	$(JCC) GetURLContent.java

GetURLContentTest.class: GetURLContentTest.java
	$(JCC) GetURLContentTest.java

SearchSetting.class: SearchSetting.java
	$(JCC) SearchSetting.java

RegularExpressionSearch.class: RegularExpressionSearch.java
	$(JCC) RegularExpressionSearch.java

WordByWordSearch.class: WordByWordSearch.java
	$(JCC) WordByWordSearch.java

InfoCrawler.class: InfoCrawler.java
	$(JCC) InfoCrawler.java

clean: 
	$(RM) *.class