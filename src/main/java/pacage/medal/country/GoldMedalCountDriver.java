package pacage.medal.country;

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

public class GoldMedalCountDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        conf.set("topn","10");
        Job job =Job.getInstance(conf,"topn");
        job.setJarByClass(GoldMedalCountDriver.class);

        job.setMapperClass(GoldMedalCountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(GoldMedalCountWritable.class);

        job.setReducerClass(GoldMedalCountReducer.class);
        job.setOutputKeyClass(GoldMedalCountWritable.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("input/CountryGoldMedal"));
        FileUtil.fullyDelete(new File("output/CountryGoldMedal"));
        FileOutputFormat.setOutputPath(job,new Path("output/CountryGoldMedal"));

        boolean result=job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
