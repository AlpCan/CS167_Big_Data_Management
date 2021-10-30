package edu.ucr.cs.cs167.aalog001;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ByteBufferPool;

import java.io.IOException;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * 10000 Random Read
 *
 */
public class AppB {
    public static void main( String[] args ) throws IOException {
        Random rand = new Random();

        // Check for number of inputs
        if(args.length >= 2) {
            System.out.println("Too many arguments.");
            System.exit(1);
        }
        // FS setup
        Configuration conf = new Configuration();
        Path input = new Path(args[0]);
        FileSystem fs = input.getFileSystem(conf);
        // File existence check
        if(!fs.exists(input)){
            System.out.printf("%s does not exists.\n",args[0]);
            System.exit(1);
        }
        //Buffer
        FSDataInputStream in = fs.open(input);
        byte[] temp = new byte[8192];
        int num_reads = 10000;
        long startTime = System.nanoTime();
        for(int i = 0 ; i < num_reads ; i++){
            in.seek(abs(rand.nextLong())%fs.getUsed(input));
            in.read(temp,0, 8192);
        }
        long estimatedTime = System.nanoTime() - startTime;

        System.out.printf("%d random reads takes %f seconds in %s.\n",num_reads,estimatedTime/1000000000.00,args[0]);
        in.close();
        fs.close();
    }
}
