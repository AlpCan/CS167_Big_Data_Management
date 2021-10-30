# Lab 3

## Student information
* Full name: Alp Can Aloglu
* E-mail: aalog001@ucr.edu
* UCR NetID: aalog001
* Student ID: 862190749

## Answers

* (Q1) What do you think the line job.setJarByClass(Filter.class); does?

```text
    Sets job by jar file class.
```
* (Q2) What is the effect of the line job.setNumReduceTasks(0);?
```text
    Sets number of reduce task with 0. We have 0 reducers.
```
* (Q3) Where does the main function run?
```text
    It runs in Driver node.
```
* (Q4) How many lines do you see in the output?
```text
    There are 27972 lines in output file.
```
* (Q5) How many files are produced in the output? 
```text
    4 in total.( part-m-00000  _SUCCESS and .crc versions)
    Only part-m-00000 have the output. So, we can say 1 output file.
```

* (Q6) Explain this number based on the input file size and default block size.

```text
    Input file size is 2.3MB and output is 2.1MB. When we compare these with block size, which is 128MB, both input 
    and output are smaller than a block so we used only 1 block. Hence, only one output file.
```

* (Q7) How many files are produced in the output? 

```text
    Ignoring _SUCCESS, there is only 1 output file part-m-00000. 
```

* (Q8) Explain this number based on the input file size and default block size.

```text
    Input file size is 2.3MB and output is 2.1MB. When we compare these with block size, which is 128MB, Input 
    is smaller than a block so we used only 1 block. Hence, only one output file.
```

* (Q9) How many files are produced in the output directory and how many lines are there in each file?

```text
    Two output files are produced, part-r-0000 and part-r-0001. part-r-0000 have 4 lines. part-r-0001 0 lines.
```

* (Q10) Explain these numbers based on the number of reducers and number of response codes in the input file.

```text
    We got 2 output files, because we had 2 reducers. We had distinct 4 response codes. So, we ended up with 
    4 lines of output in one reducer.
```

* (Q11) How many files are produced in the output directory and how many lines are there in each file?
```text
    Two output files are produced, part-r-0000 and part-r-0001. part-r-0000 have 5 lines. part-r-0001 2 lines.
```
* (Q12) Explain these numbers based on the number of reducers and number of response codes in the input file.

```text
    We got 2 output files, because we had 2 reducers. We had distinct 7 response codes. So, we ended up with 
    5 lines of output in part-r-0000 and 2 lines of output in part-r-0001.
```

* (Q13) How many files are produced in the output directory and how many lines are there in each file?

```text
    There are 5 output files, namely part-m-00000, part-m-00001, part-m-00002, part-m-00003, and part-m-00004.
    part-m-00000 has 379729 lines. part-m-00001 has 385942 lines. part-m-00002 has 378351 lines. 
    part-m-00003 has 389511 lines. part-m-00004 has 168001 lines.
```
* (Q14) Explain these numbers based on the number of reducers and number of response codes in the input file.

```text
    We did not use reducers in filtering, we only used mapper to filter. nasa_19950630.22-19950728.12.tsv got split in
    to blocks and each block filtered by a mapper. That is why we had 5 output files.
```