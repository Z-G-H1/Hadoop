package pacage.athlete.avgAge;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


import java.io.File;
import java.io.IOException;

public class AvgAgeDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();

        Job job =Job.getInstance(conf,"age");
        job.setJarByClass(AvgAgeDriver.class);

        job.setMapperClass(AvgAgeMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(AvgAgeWritable.class);

        job.setReducerClass(AvgAgeReducer.class);
        job.setOutputKeyClass(AvgAgeWritable2.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("input/AthleteData"));
        FileUtil.fullyDelete(new File("output/athlete/avgAge"));
        FileOutputFormat.setOutputPath(job,new Path("output/athlete/avgAge"));

        boolean result=job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
