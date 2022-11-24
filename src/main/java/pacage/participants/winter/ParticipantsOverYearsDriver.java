package pacage.participants.winter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;

public class ParticipantsOverYearsDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job =Job.getInstance(conf,"participant");
        job.setJarByClass(ParticipantsOverYearsDriver.class);

        job.setMapperClass(ParticipantsOverYearsMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(ParticipantsOverYearsWritable.class);

        job.setReducerClass(ParticipantsOverYearsReducer.class);
        job.setOutputKeyClass(ParticipantsOverYearsWritable.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path("input/CountryGoldMedal"));
        FileUtil.fullyDelete(new File("output/ParticipantsOverYears/Winter"));
        FileOutputFormat.setOutputPath(job,new Path("output/ParticipantsOverYears/Winter"));

        boolean result=job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
