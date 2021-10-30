package edu.ucr.cs.cs167.aalog001;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Map;

public class Aggregation {
    public static void main( String[] args ) {
        final String inputfile = args[0];
        SparkConf conf = new SparkConf();
        if (!conf.contains("spark.master"))
            conf.setMaster("local[*]");
        System.out.printf("Using Spark master '%s'\n", conf.get("spark.master"));
        conf.setAppName("lab4");
        JavaSparkContext spark = new JavaSparkContext(conf);
        try {
            JavaRDD<String> logFile = spark.textFile(inputfile).cache();
            JavaPairRDD<String, Integer> response = logFile.mapToPair(Code -> new Tuple2<>(Code.split("\t")[5],1));
            response.countByKey().forEach((s, aLong) -> System.out.println("Code `" + s + "` : number of entries " + aLong));
        } finally {
            spark.close();
        }
    }
}