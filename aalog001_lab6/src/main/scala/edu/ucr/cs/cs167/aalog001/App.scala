package edu.ucr.cs.cs167.aalog001
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf

object App {

  def main(args : Array[String]) {
    val command: String = args(0)
    val inputfile: String = args(1)
    val conf = new SparkConf
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    println(s"Using Spark master '${conf.get("spark.master")}'")

    val spark = SparkSession
      .builder()
      .appName("CS167 Lab6")
      .config(conf)
      .getOrCreate()

    try {
      val input = spark.read.format("csv")
        .option("sep", "\t")
        .option("inferSchema", "true")
        .option("header", "true")
        .load(inputfile)


      import spark.implicits._
      input.createOrReplaceTempView("log_lines")

      val t1 = System.nanoTime
      command match {
        case "count-all" =>
        // TODO count total number of records in the file
          val count = spark.sql("""SELECT count(*) FROM log_lines""")
            .first()
            .getAs[Long](0)
          println(s"Total count for file '${inputfile}' is ${count}");
        case "code-filter" =>
        // TODO Filter the file by response code, args(2), and print the total number of matching lines
          val responseCode: String = args(2)
          import spark.implicits._
          val codeFilter = input
            .filter($"response" === responseCode)
            .count()
          println(s"Total count for file '${inputfile}' with response code ${responseCode} is ${codeFilter}")
        case "time-filter" =>
        // TODO Filter by time range [from = args(2), to = args(3)], and print the total number of matching lines
          val timeStart: Long = args(2).toLong
          val timeEnd: Long = args(3).toLong
          val timeFilter = input
            .filter($"time" >= timeStart && $"time" <= timeEnd)
            .count()
          println(s"Total count for file '${inputfile}' in time range [${timeStart},${timeEnd}]  is ${timeFilter}")
        case "count-by-code" =>
        // TODO Group the lines by response code and count the number of records per group
          val countByCode = input
            .groupBy("response")
            .count()
          println(s"Number of lines per code for the file ${inputfile}")
          countByCode.show()
        case "sum-bytes-by-code" =>
        // TODO Group the lines by response code and sum the total bytes per group
          val sumBytesByCode = input
            .groupBy("response")
            .sum("bytes")
          println(s"Total bytes per code for the file ${inputfile}")
          sumBytesByCode.show()
        case "avg-bytes-by-code" =>
        // TODO Group the liens by response code and calculate the average bytes per group
          val avgBytesByCode = input
            .groupBy("response")
            .avg("bytes")
          println(s"Average bytes per code for the file ${inputfile}")
          avgBytesByCode.show()
        case "top-host" =>
        // TODO print the host the largest number of lines and print the number of lines
          val topHost = spark.sql(
            """SELECT host, count(*) AS count
              |FROM log_lines
              |GROUP BY host
              |ORDER BY count DESC LIMIT 1""".stripMargin)
            .first()
          //val topHost = input
          //  .groupBy("host")
          //  .count()
          //  .orderBy($"count".desc)
          //  .first()
          println(s"Top host in the file ${inputfile} by number of entries")
          println(s"Host: ${topHost.getAs[String](0)}")
          println(s"Number of entries: ${topHost.getAs[String](1)}")
        case "comparison" =>
        // TODO Given a specific time, calculate the number of lines per response code for the
        // entries that happened before that time, and once more for the lines that happened at or after
        // that time. Print them side-by-side in a tabular form.
          val timeCode = args(2).toLong
          val before = input
            .filter($"time" <= timeCode)
            .groupBy("response")
            .count()
            .withColumnRenamed("count","count_before")
          val after = input
            .filter($"time" > timeCode)
            .groupBy("response")
            .count()
            .withColumnRenamed("count","count_after")
          println(s"Comparison of the number of lines per code before and after ${timeCode} on file ${inputfile}")
          before.join(after,"response").show()
          //val result = spark.sql(s"SELECT before.response, before.count_before, after.count_after " +
          //  s"FROM (SELECT response, count(*) AS count_before FROM log_lines WHERE time <= ${timeCode} GROUP BY response) AS before " +
          //  s"JOIN (SELECT response, count(*) AS count_after FROM log_lines WHERE time > ${timeCode} GROUP BY response) AS after " +
          //  s"ON before.response = after.response")
          //println(s"Comparison of the number of lines per code before and after ${timeCode} on file ${inputfile}")
          //result.show()
      }
      val t2 = System.nanoTime
      println(s"Command '${command}' on file '${inputfile}' finished in ${(t2-t1)*1E-9} seconds")
    } finally {
      spark.stop
    }
  }
}
