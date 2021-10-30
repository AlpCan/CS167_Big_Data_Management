#!/usr/bin/env sh
#mvn clean package

HDFS_ADDRESS="hdfs:///user/alp/AREAWATER.csv"
LOCAL_ADDRESS="file:///$HOME/workspace/aalog001_lab2/AREAWATER.csv"

LOCAL_ADDRESS_COPY1="file:///$HOME/workspace/aalog001_lab2/AREAWATER_localCopy.csv"
LOCAL_ADDRESS_COPY2="file:///$HOME/workspace/aalog001_lab2/AREAWATER_localCopy2.csv"

HDFS_ADDRESS_COPY1="hdfs:///user/alp/AREAWATER_hdfsCopy.csv"
HDFS_ADDRESS_COPY2="hdfs:///user/alp/AREAWATER_hdfsCopy2.csv"
#case 1 local to local
echo "CASE 1 : Local file system to local file system."
hadoop jar target/aalog001_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.aalog001.App $LOCAL_ADDRESS $LOCAL_ADDRESS_COPY1

#case 2 local to hdfs
echo "CASE 2 : Local file system to HDFS."
hadoop jar target/aalog001_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.aalog001.App $LOCAL_ADDRESS $HDFS_ADDRESS_COPY1

#case 3 hdfs to local
echo "CASE 3 : HDFS to Local file system."
hadoop jar target/aalog001_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.aalog001.App $HDFS_ADDRESS $LOCAL_ADDRESS_COPY2

#case 4 hdfs to local
echo "CASE 4 : HDFS to HDFS."
hadoop jar target/aalog001_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.aalog001.App $HDFS_ADDRESS $HDFS_ADDRESS_COPY2

#case 5 local to local
echo "CASE 5 : Local file system to local file system with command."
time cp "AREAWATER.csv" "AREAWATER_localCopy3.csv"

#bonus 1 hdfs random reads
echo "BONUS"
echo "CASE 1: HDFS."
hadoop jar target/aalog001_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.aalog001.AppB $HDFS_ADDRESS

#bonus 2 local random reads
echo "CASE 2: Local file system."
hadoop jar target/aalog001_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.aalog001.AppB $LOCAL_ADDRESS

