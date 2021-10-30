package edu.ucr.cs.cs167.aalog001;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.io.IOUtils;

import java.io.FileInputStream;
import static java.lang.Boolean.TRUE;

/**
 * File Copy Test
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        if(args.length >= 3) {
            System.out.println("Too many arguments.");
            System.exit(1);
        }
        Configuration conf = new Configuration();
        Path input = new Path(args[0]);
        Path output = new Path(args[1]);
        FileSystem fs_in = input.getFileSystem(conf);
        FileSystem fs_out = output.getFileSystem(conf);
        if(!fs_in.exists(input)){
            System.out.printf("%s does not exists.\n",args[0]);
            System.exit(1);
        }
        if(fs_out.exists(output)){
            System.out.printf("%s already exists.\n",args[1]);
            System.exit(1);
        }
        FSDataInputStream in = fs_in.open(input);
        FSDataOutputStream out = fs_out.create(output);
        long startTime = System.nanoTime();
        IOUtils.copyBytes(in,out,conf,TRUE); // io.utils
        long estimatedTime = System.nanoTime() - startTime;
        System.out.printf("Copied %d bytes from '%s' to '%s' in %f seconds.\n",fs_out.getUsed(output),args[0],args[1],estimatedTime/1000000000.00);
        fs_in.close();
        fs_out.close();
    }
}
