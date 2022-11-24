package pacage.medal.country;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class GoldMedalCountMapper extends Mapper<Object, Text,Text,GoldMedalCountWritable> {

    GoldMedalCountWritable gmcWritable = null;

    //将国家名称作为输出的key
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] data = value.toString().split("\t");
        gmcWritable = new GoldMedalCountWritable();
        if(data[14].equals("Gold")){
            gmcWritable.setCountryName(data[6]);
            gmcWritable.setMedal(data[14]);
            context.write(new Text(data[6]),gmcWritable);
        }

    }

}
