package edu.ucr.cs.cs167.aalog001
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.ml.classification.{LogisticRegression, LogisticRegressionModel}
import org.apache.spark.ml.evaluation.{BinaryClassificationEvaluator, RegressionEvaluator}
import org.apache.spark.ml.feature.{HashingTF, StringIndexer, Tokenizer, VectorAssembler, Word2Vec}
import org.apache.spark.ml.regression.{LinearRegression, LinearRegressionModel}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.ml.tuning.{CrossValidator, ParamGridBuilder, TrainValidationSplit}
import org.apache.spark.ml.linalg.Vectors

object App {

  def main(args : Array[String]) {
    if (args.length < 2) {
      println("Usage <input file> <algorithm>")
      println("<input file> path to a CSV file input")
      println("<algorithm> is either regression or classification")
    }
    val inputfile = args(0)
    val method = args(1)
    val conf = new SparkConf
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    println(s"Using Spark master '${conf.get("spark.master")}'")

    val spark = SparkSession
      .builder()
      .appName("CS167 Lab9")
      .config(conf)
      .getOrCreate()


    val t1 = System.nanoTime
    try {
      if (method.equals("regression"))
      {
        // TODO apply regression model on house prices
        //Reading in the data
        val input = spark
          .read
          .option("header","true")
          .option("inferSchema", "true")
          .option("printSchema","show")
          .csv(inputfile)

        // Assembling features column
        val vectorAssembler = new VectorAssembler()
          .setInputCols(Array("bedrooms","bathrooms","sqft_living","sqft_lot"))
          .setOutputCol("features")

        //Bonus 1
        //val vectorAssembler = new VectorAssembler()
        // .setInputCols(Array("bedrooms", "bathrooms", "sqft_living", "sqft_lot", "condition", "waterfront", "view"))
        //  .setOutputCol("features")

        //Bonus 2
        //val vectorAssembler = new VectorAssembler()
        //  .setInputCols(Array("sqft_living", "sqft_lot", "sqft_above", "sqft_basement", "sqft_living15", "sqft_lot15"))
        //  .setOutputCol("features")

        //Bonus 3
        //val vectorAssembler = new VectorAssembler()
        //  .setInputCols(Array("bedrooms", "bathrooms", "condition", "waterfront", "view", "sqft_living", "sqft_lot", "sqft_above", "sqft_basement", "sqft_living15", "sqft_lot15"))
        //  .setOutputCol("features")

        //Linear Regression model building
        val linearRegression = new LinearRegression()
          .setLabelCol("price")
          .setFeaturesCol("features")

        val pipeline = new Pipeline()
          .setStages(Array(vectorAssembler, linearRegression))

        val paramMaps = new ParamGridBuilder()
          .addGrid(linearRegression.regParam, Array(0.01, 0.1, 0.3))
          .addGrid(linearRegression.elasticNetParam, Array(0.0, 0.3, 0.8, 1.0))
          .build()

        val crossValidator = new CrossValidator()
          .setEstimator(pipeline)
          .setEvaluator(new RegressionEvaluator().setLabelCol("price"))
          .setEstimatorParamMaps(paramMaps)
          .setNumFolds(5)
          .setParallelism(2)

        val Array(trainingData, testData) = input.randomSplit(Array(0.8, 0.2))

        val model = crossValidator.fit(trainingData)

        val chosenModel = model.bestModel.asInstanceOf[PipelineModel].stages(1).asInstanceOf[LinearRegressionModel]
        println(s"ElasticNetParam: ${chosenModel.getElasticNetParam}")
        println(s"regParam: ${chosenModel.getRegParam}")
        println(s"coefficients: ${chosenModel.coefficients}")

        val predictions = model.transform(testData)
        predictions.select("price", "prediction").show(5)

        val rmse = new RegressionEvaluator()
          .setLabelCol("price")
          .setPredictionCol("prediction")
          .setMetricName("rmse")
          .evaluate(predictions)
        println(s"RMSE on test set is $rmse")

        val corr = Correlation.corr(new VectorAssembler()
          .setInputCols(Array("prediction", "price"))
          .setOutputCol("features2")
          .transform(predictions), "features2").head
        println(s"Correlation is $corr")
      } else if (method.equals("classification")) {
        // TODO process the sentiment data
        val input = spark
          .read
          .option("header","true")
          .option("quote","\"")
          .option("escape","\"")
          .option("inferSchema", "true")
          .option("printSchema","show")
          .csv(inputfile)

        input.printSchema()
        input.show(5)

        val tokenizer = new Tokenizer()
          .setInputCol("text")
          .setOutputCol("words")

        val hashingTF = new HashingTF()
          .setInputCol("words")
          .setOutputCol("features")

        val stringIndexer = new StringIndexer()
          .setInputCol("sentiment")
          .setOutputCol("label")
          .setHandleInvalid("skip")

        val logisticRegression = new LogisticRegression()
          .setLabelCol("label")
          .setFeaturesCol("features")

        val pipeline = new Pipeline()
          .setStages(Array(tokenizer, hashingTF, stringIndexer, logisticRegression))

        val paramMaps = new ParamGridBuilder()
          .addGrid(hashingTF.numFeatures, Array(10, 100, 1000))
          .addGrid(logisticRegression.regParam, Array(0.01, 0.1, 0.3, 0.8))
          .build()

        val cv = new TrainValidationSplit()
          .setEstimator(pipeline)
          .setEvaluator(new BinaryClassificationEvaluator())
          .setEstimatorParamMaps(paramMaps)
          .setTrainRatio(0.8)
          .setParallelism(2)

        val Array(trainingData, testData) = input.randomSplit(Array(0.8, 0.2))

        val logisticModel = cv.fit(trainingData)

        val numFeatures = logisticModel.bestModel.asInstanceOf[PipelineModel].stages(1).asInstanceOf[HashingTF].getNumFeatures
        val regParam = logisticModel.bestModel.asInstanceOf[PipelineModel].stages(3).asInstanceOf[LogisticRegressionModel].getRegParam

        println("numFeatures: ", numFeatures)
        println("regParam: ",regParam)

        val predictions = logisticModel.transform(testData)
        predictions.select("text", "label", "prediction", "probability").show()

        val binaryClassificationEvaluator = new BinaryClassificationEvaluator()
          .setLabelCol("label")
          .setRawPredictionCol("prediction")

        val accuracy = binaryClassificationEvaluator.evaluate(predictions)
        println(s"Accuracy of the test set is $accuracy")

      } else {
        println(s"Unknown algorithm ${method}")
        System.exit(1)
      }
      val t2 = System.nanoTime
      println(s"Applied algorithm $method on input $inputfile in ${(t2 - t1) * 1E-9} seconds")

    } finally {
      spark.stop
    }
  }
}
