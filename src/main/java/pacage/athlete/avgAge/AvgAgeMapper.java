package pacage.athlete.avgAge;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AvgAgeMapper extends Mapper <Object, Text,Text, AvgAgeWritable>{
    Set<AvgAgeWritable> set = new HashSet<>();
    AvgAgeWritable avgAgeWritable ;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
    }

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] data = value.toString().split("\t");
        if (!("NA".equals(data[3]))){
            avgAgeWritable = new AvgAgeWritable();
            avgAgeWritable.setName(data[1]);
            avgAgeWritable.setAge(Integer.parseInt(data[3]));
            avgAgeWritable.setYear(data[9]);
            avgAgeWritable.setGender(data[2]);
            set.add(avgAgeWritable);
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (AvgAgeWritable age : set){
//            System.out.println(age.toString());
            context.write(new Text(age.getYear()),age);
        }
    }
}
