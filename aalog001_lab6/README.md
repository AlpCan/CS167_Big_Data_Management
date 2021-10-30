# Lab 6

## Student information
* Full name: Alp Can Aloglu
* E-mail: aalog001@ucr.edu
* UCR NetID: aalog001
* Student ID: 862190749

## Answers

* (Q1) What is the type of the attributes time and bytes this time? Why?

```text
    root
 |-- host: string (nullable = true)
 |-- logname: string (nullable = true)
 |-- time: string (nullable = true)
 |-- method: string (nullable = true)
 |-- url: string (nullable = true)
 |-- response: string (nullable = true)
 |-- bytes: string (nullable = true)
 |-- referer: string (nullable = true)
 |-- useragent: string (nullable = true)
 
 All attributes are string because spark is not inferring a data type for attributes from the data.
```

* (B)

```text
SELECT before.response, before.count_before, after.count_after 
FROM (SELECT response, count(*) AS count_before FROM log_lines WHERE time <= ${timeCode} GROUP BY response) AS before 
JOIN (SELECT response, count(*) AS count_after FROM log_lines WHERE time > ${timeCode} GROUP BY response) AS after 
ON before.response = after.response
```
