TwitterCrawler
==============

A simple twitter crawler. Uses tweets/tweetIDs and mines information. Sample .txt files are included, and it is shown how from the id's or raw text we mine sentiment analysis information. The format of the .txt files created are for use in Weka.

Using the twitter4j library for the twitter interaction with the program. http://twitter4j.org/en/index.html


How to Use
===============

<b>Phase 1:</b>
We have athenticate our application through Twitter, so that we can use the API.
Classes: TwitterCrawlerMain.java

<b>Phase 2:</b>
We have two text files containing id's and tweet id's, of which one is annotated and the other is not. We use the tweet id's  to get the text which we will later analyze.
Classes: AnnotatedCrawler.java, NotAnnotatedCrawler.java

<b>Phase 3:</b>
We perform the analysis depending on 2 types of info: person id and twwet text.
1. ddvdvs
