# Lab 2

## Student information
* Full name: Alp Can Aloglu
* E-mail: aalog001@ucr.edu
* UCR NetID: aalog001
* Student ID: 862190749

## Answers

* (Q1) Verify the file size and record the running time.

```text
Coping file with hadoop:
Copied 2271210910 bytes from 'file:////home/alp/workspace/aalog001_lab2/AREAWATER.csv' to 'file:////home/alp/workspace/aalog001_lab2/AREAWATER_localCopy.csv' in 23.207992 seconds.
```

* (Q2) Record the running time of the copy command.

```text
real	0m13.797s
user	0m0.043s
sys	0m3.239s
```

* (Q3)  How do the two numbers compare?

```text
Command copy is faster, because copying file with hadoop API
 also includes hadoop copyByte background execution.
```
* (Q4) Does the program run after you change the default file system to HDFS? What is the error message, if any, that you get?

```text
It did run, without any error. It used the file in hdfs, and made copy in hdfs.
```

* (Q5) Use your program to test the following cases and record the running time for each case.

```text
CASE 1 : Local file system to HDFS.
Copied 2271210910 bytes from 'file:////home/alp/workspace/aalog001_lab2/AREAWATER.csv' to 'hdfs:///user/alp/AREAWATER_hdfsCopy.csv' in 21.687063 seconds.
CASE 2 : HDFS to Local file system.
Copied 2271210910 bytes from 'hdfs:///user/alp/AREAWATER.csv' to 'file:////home/alp/workspace/aalog001_lab2/AREAWATER_localCopy2.csv' in 21.299949 seconds.
CASE 3 : HDFS to HDFS.
Copied 2271210910 bytes from 'hdfs:///user/alp/AREAWATER.csv' to 'hdfs:///user/alp/AREAWATER_hdfsCopy2.csv' in 10.769174 seconds.

```
* (Q6) Test your program on two files, one file stored on the local file system, and another file stored on HDFS. Compare the running times of both tasks. What do you observe?

```text
Run time for hdfs is a lot slower compare to local file system with random reads.

CASE 1: HDFS.
10000 random reads takes 23.674689 seconds in hdfs:///user/alp/AREAWATER.csv.
CASE 2: Local file system.
10000 random reads takes 1.075441 seconds in file:////home/alp/workspace/aalog001_lab2/AREAWATER.csv.
```