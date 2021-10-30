package edu.ucr.cs.cs167.aalog001

import edu.ucr.cs.bdlab.beast.cg.SpatialJoinAlgorithms.{ESJDistributedAlgorithm, ESJPredicate}
import edu.ucr.cs.bdlab.beast.geolite.{Feature, IFeature}
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.locationtech.jts.geom.{Envelope, GeometryFactory}

import java.text.SimpleDateFormat
import java.util.{Calendar, Date, TimeZone}

/**
 * Scala examples for Beast
 */
object BeastScala {
  def main(args: Array[String]): Unit = {
    // Initialize Spark context

    val conf = new SparkConf().setAppName("Beast Example")
    // Set Spark master to local if not already set
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")

    val spark: SparkSession.Builder = SparkSession.builder().config(conf)

    val sparkSession = spark.getOrCreate()
    val sparkContext = sparkSession.sparkContext
    try {
      // Import Beast features
      import edu.ucr.cs.bdlab.beast._

      // Loading in data sets
      val tweets = sparkContext.readCSVPoint("tweets_ca.csv", delimiter = '\t', skipHeader = true)
      val counties = sparkContext.shapefile("tl_2018_us_county.zip")
      val timezones = sparkContext.shapefile("ne_10m_time_zones.zip")
      // File load in check
      //println(s"Number of tweets is ${tweets.count()}")
      //println(s"Number of counties ${counties.count()}")
      //println(s"Number of timezones is ${timezones.count()}")

      // Part III : Tweet relation between coffee and time
      val coffeeTweets = tweets
        .filter(features => features.getAs[String](2).contains("coffee"))
      val coffeeTweetsByHour = coffeeTweets
        .map(feature => {
          val dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
          val cal: Calendar = Calendar.getInstance()
          val time: Date = dateParser.parse(feature.getAs[String](1))
          cal.setTime(time)
          val hour = cal.get(Calendar.HOUR_OF_DAY)
          (hour,1)
        })
        .countByKey()

      for (h <- 0 to 23) {
        print(coffeeTweetsByHour.getOrElse(h, 0))
        if (h != 23)
          print(", ")
      }
      println

      //Part IV
      val coffeeTweetsTimeZone: RDD[(IFeature, IFeature)] = coffeeTweets.spatialJoin(timezones)

      val resultCoffeeTweetsTimeZone = coffeeTweetsTimeZone.map(feature => {
          val dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
          val time: Date = dateParser.parse(feature._1.getAs[String](1))
          val cal: Calendar = Calendar.getInstance(TimeZone.getTimeZone(feature._2.getAs[String]("time_zone")))
          cal.setTime(time)
          val hour = cal.get(Calendar.HOUR_OF_DAY)
          (hour , 1  )
      })
        .countByKey()

      for (h <- 0 to 23) {
        print(resultCoffeeTweetsTimeZone.getOrElse(h, 0))
        if (h != 23)
          print(", ")
      }
      println

      //Part V
      // DONUT
/*
      val donutTweets = tweets
        .filter(features => features.getAs[String](2).contains("donut"))

      val donutTweetsCounty: RDD[(IFeature, IFeature)] = counties.spatialJoin(donutTweets)

      val topTwentyDonutTweetsCounty = donutTweetsCounty
        .countByKey()
        .toArray
        .sortWith(_._2 >_._2)
        .take(20)
        .map(features => features._1)

      val top_counties = sparkContext
        .parallelize(topTwentyDonutTweetsCounty)

      //top_counties.plotImage(2000, 2000, "counties_donut.png")
      top_counties.plotPyramid("counties_donut", 12, opts = "mercator" -> true)

      // BAGEL
      val bagelTweets = tweets
        .filter(features => features.getAs[String](2).contains("bagel"))

      val bagelTweetsCounty: RDD[(IFeature, IFeature)] = counties.spatialJoin(bagelTweets)

      val topTwentyBagelTweetsCounty = bagelTweetsCounty
        .countByKey()
        .toArray
        .sortWith(_._2 >_._2)
        .take(20)
        .map(features => features._1)

      val top_counties_bagel = sparkContext
        .parallelize(topTwentyBagelTweetsCounty)

      //top_counties_bagel.plotImage(2000, 2000, "counties_bagel.png")
      top_counties_bagel.plotPyramid("counties_bagel", 12, opts = "mercator" -> true)

      // HAPPY
      val happyTweets = tweets
        .filter(features => features.getAs[String](2).contains("Happy"))

      val happyTweetsCounty: RDD[(IFeature, IFeature)] = counties.spatialJoin(happyTweets)

      val topTwentyHappyTweetsCounty = happyTweetsCounty
        .countByKey()
        .toArray
        .sortWith(_._2 >_._2)
        .take(20)
        .map(features => features._1)

      val top_counties_happy = sparkContext
        .parallelize(topTwentyHappyTweetsCounty)

      //top_counties_happy.plotImage(2000, 2000, "counties_happy.png")
      //top_counties_happy.plotPyramid("counties_happy", 12, opts = "mercator" -> true)
      */
    } finally {
      sparkSession.stop()
    }
  }
}