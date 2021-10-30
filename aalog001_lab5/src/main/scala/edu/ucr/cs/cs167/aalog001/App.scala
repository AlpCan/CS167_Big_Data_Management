package edu.ucr.cs.cs167.aalog001
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object App {

  def main(args : Array[String]) {
    val command: String = args(0)
    val inputfile: String = args(1)

    val conf = new SparkConf
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    println(s"Using Spark master '${conf.get("spark.master")}'")
    conf.setAppName("lab5")
    val sparkContext = new SparkContext(conf)
    try {
      val inputRDD: RDD[String] = sparkContext.textFile(inputfile)
      // TODO Parse the input file using the tab separator and skip the first line
      val data = inputRDD.filter(!_.startsWith("host\tlogname")).map(_.split("\t"))
      val Host: Int = 0
      val Time: Int = 2
      val ResponseCode: Int = 5
      val Bytes: Int = 6
      val t1 = System.nanoTime
      command match {
        case "count-all" =>
          // TODO count total number of records in the file
          val count: Long = data.count();
          println(s"Total count for file '${inputfile}' is ${count}")
        case "code-filter" =>
          // TODO Filter the file by response code, args(2), and print the total number of matching lines
          val responseCode: String = args(2)
          val codeFilter: Long = data.filter(s => s(ResponseCode).equals(responseCode)).count()
          println(s"Total count for file '${inputfile}' with response code ${responseCode} is ${codeFilter}")
        case "time-filter" =>
          // TODO Filter by time range [from = args(2), to = args(3)], and print the total number of matching lines
          val timeStart: Long = args(2).toLong
          val timeEnd: Long = args(3).toLong
          val timeFilter: Long = data.filter(s => s(Time).toLong >= timeStart && s(Time).toLong <= timeEnd ).count()
          println(s"Total count for file '${inputfile}' in time range [${timeStart},${timeEnd}]  is ${timeFilter}")
        case "count-by-code" =>
          // TODO Group the lines by response code and count the number of records per group
          val countByCode = data.map(s=> (s(ResponseCode),1)).countByKey()
          println(s"Number of lines per code for the file ${inputfile}")
          println(s"Code,Count")
          countByCode.foreach(s => println(s"${s._1},${s._2}"))
        case "sum-bytes-by-code" =>
        // TODO Group the lines by response code and sum the total bytes per group
          val sumBytesByCode = data.map(s => (s(ResponseCode),s(Bytes).toLong)).reduceByKey(_+_).collect()
          println(s"Total bytes per code for the file ${inputfile}")
          println(s"Code,Sum(bytes)")
          sumBytesByCode.foreach(s => println(s"${s._1},${s._2}"))
        case "avg-bytes-by-code" =>
        // TODO Group the liens by response code and calculate the average bytes per group
          val avgBytesByCode = data.map(s => (s(ResponseCode),s(Bytes).toLong))
            .aggregateByKey((0.0,0))((acc,value) => (acc._1+value, acc._2 + 1) ,(acc1,acc2) => (acc1._1 + acc2._1,acc1._2 + acc2._2))
            .mapValues(v => 1.0*v._1/v._2)
            .collect()
          println(s"Average bytes per code for the file ${inputfile}")
          println(s"Code,Avg(bytes)")
          avgBytesByCode.foreach(s => println(s"${s._1},${s._2}"))
        case "top-host" =>
        // TODO print the host the largest number of lines and print the number of lines
          val topHost: (String, Int) = data.map(s => (s(Host),1)).reduceByKey(_+_).sortBy(_._2,false).first()
          println(s"Top host in the file ${inputfile} by number of entries")
          println(s"Host: ${topHost._1}")
          println(s"Number of entries: ${topHost._2}")
        case "comparison" =>
        // TODO Given a specific time, calculate the number of lines per response code for the
        // entries that happened before that time, and once more for the lines that happened at or after
        // that time. Print them side-by-side in a tabular form.
          val timeCode = args(2).toLong
          val before = data.filter(s => s(Time).toLong < timeCode).map(s => (s(ResponseCode),1)).countByKey()
          val after = data.filter(s => s(Time).toLong >= timeCode).map(s => (s(ResponseCode),1)).countByKey()
          val result = (before.toSeq ++ after.toSeq).groupBy(_._1).mapValues(_.map(_._2).toList)
          println(s"Comparison of the number of lines per code before and after ${timeCode} on file ${inputfile}")
          println(s"Code,Count before,Count after")
          result.foreach(s => println(s"${s._1},${s._2(0)},${s._2(1)}"))
      }
      val t2 = System.nanoTime
      println(s"Command '${command}' on file '${inputfile}' finished in ${(t2-t1)*1E-9} seconds")
    } finally {
      sparkContext.stop
    }
  }
}
