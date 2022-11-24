package pacage.medal.athlete;

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

public class GoldMetalCountDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        conf.set("topn","10");
        Job job =Job.getInstance(conf,"topn");
        job.setJarByClass(GoldMetalCountDriver.class);

        job.setMapperClass(GoldMetalCountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(GoldMetalCountWritable.class);

        job.setReducerClass(GoldMetalCountReducer.class);
        job.setOutputKeyClass(GoldMetalCountWritable.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("input/CountryGoldMedal"));
        FileUtil.fullyDelete(new File("output/AthleteGoldMedal"));
        FileOutputFormat.setOutputPath(job,new Path("output/AthleteGoldMedal"));

        boolean result=job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
