#!/usr/bin/env sh
mvn clean package

spark-submit --master local[2] target/aalog001_lab7-1.0-SNAPSHOT.jar kc_house_data.csv regression

spark-submit --master local[2] target/aalog001_lab7-1.0-SNAPSHOT.jar sentiment.csv classification