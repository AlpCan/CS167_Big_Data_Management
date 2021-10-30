# Lab 4

## Student information
* Full name: Alp Can Aloglu
* E-mail: aalog001@ucr.edu
* UCR NetID: aalog001
* Student ID: 862190749

## Answers

* (Q1) Do you think it will use your local cluster? Why or why not?

```text
    We did not specify anything different than the previous run. So, I would not expect it to run some where 
    other than local cluster.
```

* (Q2) Does the application use the cluster that you started? How did you find out?

```text
    Application used the cluster I created. Submitted application appeared in completed application list in 
    the web interface.
```

* (Q3) What is the Spark master printed on the standard output on IntelliJ IDEA?

```text
    Using Spark master 'local[*]'
```

* (Q4) What is the Spark master printed on the standard output on the terminal?

```text
    Using Spark master 'local[*]'
```

* (Q5) For the previous command that prints the number of matching lines, list all the processed input splits.

```text
INFO HadoopRDD: Input split: file:/home/alp/workspace/aalog001_lab4/nasa_19950801.tsv:1169610+1169610
INFO HadoopRDD: Input split: file:/home/alp/workspace/aalog001_lab4/nasa_19950801.tsv:0+1169610
```
* (Q6) For the previous command that counts the lines and prints the output, how many splits were generated?

```text
... (DAG section for //logFile.filter(line -> line.split("\t")[5].equals(desiredCode));)
INFO HadoopRDD: Input split: file:/home/alp/workspace/aalog001_lab4/nasa_19950801.tsv:0+1169610
INFO HadoopRDD: Input split: file:/home/alp/workspace/aalog001_lab4/nasa_19950801.tsv:1169610+1169610
...
(DAG section for //matchingLines.saveAsTextFile(outputFile);)
INFO HadoopRDD: Input split: file:/home/alp/workspace/aalog001_lab4/nasa_19950801.tsv:1169610+1169610
INFO HadoopMapRedCommitProtocol: Using output committer class org.apache.hadoop.mapred.FileOutputCommitter
INFO FileOutputCommitter: File Output Committer Algorithm version is 1
INFO FileOutputCommitter: FileOutputCommitter skip cleanup _temporary folders under output directory:false, ignore cleanup failures: false
INFO HadoopRDD: Input split: file:/home/alp/workspace/aalog001_lab4/nasa_19950801.tsv:0+1169610
...
```

* (Q7) Compare this number to the one you got earlier.
  
```text
    This run have splits of two twice.
```

* (Q8) Explain why we get these numbers.

```text
    Two DAG is created. 1 for the filter, 1 for the output.
```

*  (Q9) What can you do to the current code to ensure that the file is read only once?

```text
    JavaRDD<String> logFile = spark.textFile(inputfile).cache();
    
    Using line above allow us tho cache file in memory. So, when we need to create next dag we wont need to 
    read the file from disk.
```