# Lab 1

## Student information
* Full name: Alp Can Aloglu
* E-mail: aalog001@ucr.edu
* UCR NetID: aalog001
* Student ID: 862190749

## Answers

* (Q1) What is the name of the created directory?

```text
$HOME/workspace/aalog001_lab1
```
  
* (Q2) What do you see at the console output?

```text
Hello World!
```

* (Q3) What do you see at the output?

```text
log4j:WARN No appenders could be found for logger (org.apache.hadoop.metrics2.lib.MutableMetricsFactory).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 0
	at edu.ucr.cs.cs167.aalog001.App.main(App.java:63)

Process finished with exit code 1
```
* (Q4) What is the output that you see at the console?

```text
log4j:WARN No appenders could be found for logger (org.apache.hadoop.metrics2.lib.MutableMetricsFactory).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.

Process finished with exit code 0
```

* (Q5) Does it run? Why or why not?

 When we try to run it with previous command, we get the following error, which means haddop classes does not have 
 definition connections in java
```text
Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/hadoop/conf/Configuration
	at edu.ucr.cs.cs167.aalog001.App.main(App.java:55)
Caused by: java.lang.ClassNotFoundException: org.apache.hadoop.conf.Configuration
	at java.net.URLClassLoader.findClass(URLClassLoader.java:382)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:418)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:355)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:351)
	... 1 more

```
