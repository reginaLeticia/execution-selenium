#!/bin/bash
wtd=$(cat $1)
java -Dfile.encoding=UTF-8 -classpath /media/sf_winhome/Dropbox/Dario\ PhD/SleepToWait\ tool/tedd_test_suites/testsuite-addressbook-testng/target/classes:/home/dario/.m2/repository/io/github/bonigarcia/webdrivermanager/3.0.0/webdrivermanager-3.0.0.jar:/home/dario/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:/home/dario/.m2/repository/commons-io/commons-io/2.6/commons-io-2.6.jar:/home/dario/.m2/repository/org/apache/commons/commons-lang3/3.7/commons-lang3-3.7.jar:/home/dario/.m2/repository/org/apache/httpcomponents/httpclient/4.5.5/httpclient-4.5.5.jar:/home/dario/.m2/repository/org/apache/httpcomponents/httpcore/4.4.9/httpcore-4.4.9.jar:/home/dario/.m2/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar:/home/dario/.m2/repository/commons-codec/commons-codec/1.10/commons-codec-1.10.jar:/home/dario/.m2/repository/org/rauschig/jarchivelib/0.8.0/jarchivelib-0.8.0.jar:/home/dario/.m2/repository/org/apache/commons/commons-compress/1.14/commons-compress-1.14.jar:/home/dario/.m2/repository/org/jsoup/jsoup/1.11.3/jsoup-1.11.3.jar:/home/dario/.m2/repository/junit/junit/4.12/junit-4.12.jar:/home/dario/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/home/dario/.m2/repository/org/seleniumhq/selenium/selenium-java/3.141.59/selenium-java-3.141.59.jar:/home/dario/.m2/repository/org/seleniumhq/selenium/selenium-api/3.141.59/selenium-api-3.141.59.jar:/home/dario/.m2/repository/org/seleniumhq/selenium/selenium-chrome-driver/3.141.59/selenium-chrome-driver-3.141.59.jar:/home/dario/.m2/repository/org/seleniumhq/selenium/selenium-edge-driver/3.141.59/selenium-edge-driver-3.141.59.jar:/home/dario/.m2/repository/org/seleniumhq/selenium/selenium-firefox-driver/3.141.59/selenium-firefox-driver-3.141.59.jar:/home/dario/.m2/repository/org/seleniumhq/selenium/selenium-ie-driver/3.141.59/selenium-ie-driver-3.141.59.jar:/home/dario/.m2/repository/org/seleniumhq/selenium/selenium-opera-driver/3.141.59/selenium-opera-driver-3.141.59.jar:/home/dario/.m2/repository/org/seleniumhq/selenium/selenium-remote-driver/3.141.59/selenium-remote-driver-3.141.59.jar:/home/dario/.m2/repository/org/seleniumhq/selenium/selenium-safari-driver/3.141.59/selenium-safari-driver-3.141.59.jar:/home/dario/.m2/repository/net/bytebuddy/byte-buddy/1.8.15/byte-buddy-1.8.15.jar:/home/dario/.m2/repository/org/apache/commons/commons-exec/1.3/commons-exec-1.3.jar:/home/dario/.m2/repository/com/squareup/okhttp3/okhttp/3.11.0/okhttp-3.11.0.jar:/home/dario/.m2/repository/com/squareup/okio/okio/1.14.0/okio-1.14.0.jar:/home/dario/.m2/repository/org/seleniumhq/selenium/selenium-support/3.141.59/selenium-support-3.141.59.jar:/home/dario/.m2/repository/mysql/mysql-connector-java/6.0.5/mysql-connector-java-6.0.5.jar:/home/dario/.m2/repository/com/google/inject/guice/4.2.2/guice-4.2.2.jar:/home/dario/.m2/repository/javax/inject/javax.inject/1/javax.inject-1.jar:/home/dario/.m2/repository/aopalliance/aopalliance/1.0/aopalliance-1.0.jar:/home/dario/.m2/repository/com/google/guava/guava/23.0/guava-23.0.jar:/home/dario/.m2/repository/com/google/code/findbugs/jsr305/1.3.9/jsr305-1.3.9.jar:/home/dario/.m2/repository/com/google/errorprone/error_prone_annotations/2.0.18/error_prone_annotations-2.0.18.jar:/home/dario/.m2/repository/com/google/j2objc/j2objc-annotations/1.1/j2objc-annotations-1.1.jar:/home/dario/.m2/repository/org/codehaus/mojo/animal-sniffer-annotations/1.14/animal-sniffer-annotations-1.14.jar:/home/dario/.m2/repository/com/google/code/gson/gson/2.8.2/gson-2.8.2.jar:/home/dario/.m2/repository/org/testng/testng/7.1.0/testng-7.1.0.jar:/home/dario/.m2/repository/com/beust/jcommander/1.72/jcommander-1.72.jar:/home/dario/.m2/repository/com/google/inject/guice/4.1.0/guice-4.1.0-no_aop.jar:/home/dario/.m2/repository/org/yaml/snakeyaml/1.21/snakeyaml-1.21.jar -Dport=3000 -DbrowserPort=4445 -Dhost="127.0.0.1" org.testng.TestNG -testclass $wtd