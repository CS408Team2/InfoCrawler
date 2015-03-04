JCC = javac

all: InfoCrawler.class GetURLContentTest.class GetURLContent.class WordByWordSearch.class EmptyFileException.class SearchSetting.class RegularExpressionSearch.class MyThread.class HTMLElement.class Search.class Searchbywords.class SearchResult.class SearchTest.class GUI.class

SearchTest.class: SearchTest.java
	$(JCC) SearchTest.java

SearchResult.class: SearchResult.java
	$(JCC) SearchResult.java

Searchbywords.class: Searchbywords.java
	$(JCC) Searchbywords.java

HTMLElement.class: HTMLElement.java
	$(JCC) HTMLElement.java

Search.class: Search.java
	$(JCC) Search.java

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

ExchangeWord.class: ExchangeWord.java
	$(JCC) ExchangeWord.java

WordByWordSearch.class: WordByWordSearch.java
	$(JCC) WordByWordSearch.java

InfoCrawler.class: InfoCrawler.java
	$(JCC) InfoCrawler.java

GUI.class: GUI.java
	$(JCC) GUI.java

clean: 
	$(RM) *.class

test:
	java SearchTest

run:
	java InfoCrawler


