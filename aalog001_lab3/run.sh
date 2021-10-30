#!/usr/bin/env sh
mvn clean package

# filter with 200
hadoop jar target/aalog001_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.aalog001.Filter nasa_19950801.tsv filter_output.tsv 200

# Aggregation
hadoop jar target/aalog001_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.aalog001.Aggregation nasa_19950801.tsv agregation_output.tsv
