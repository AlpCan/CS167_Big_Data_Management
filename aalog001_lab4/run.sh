#!/usr/bin/env sh
mvn clean package

# filter with 200
spark-submit --class edu.ucr.cs.cs167.aalog001.Filter --master local[2] target/aalog001_lab4-1.0-SNAPSHOT.jar nasa_19950630.22-19950728.12.tsv fiter_output 302

# Aggregation
spark-submit --class edu.ucr.cs.cs167.aalog001.Aggregation --master local[2] target/aalog001_lab4-1.0-SNAPSHOT.jar nasa_19950630.22-19950728.12.tsv -> aggregation_output
