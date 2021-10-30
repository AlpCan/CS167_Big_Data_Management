# Lab 5

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

* (Bonus) 
```text
    To implement bonus I used following lines,
    
    val avgBytesByCode = data.map(s => (s(ResponseCode),s(Bytes).toLong))
            .aggregateByKey((0.0,0))((acc,value) => (acc._1+value, acc._2 + 1) ,(acc1,acc2) => (acc1._1 + acc2._1,acc1._2 + acc2._2))
            .mapValues(v => 1.0*v._1/v._2)
            .collect()
          println(s"Average bytes per code for the file ${inputfile}")
          println(s"Code,Avg(bytes)")
          avgBytesByCode.foreach(s => println(s"${s._1},${s._2}"))
          
    AgregateByKey is initialized with zero pair( double, int) as (0.0,0). seqOp is adding the value of each key to
    first element of the pair, and increments second element of the pair with each element. CombOp, just combines the
    results from each RDD. Then we use mapValues to combine the result of pairs for each key, and calculate the average 
    for each key.
```
