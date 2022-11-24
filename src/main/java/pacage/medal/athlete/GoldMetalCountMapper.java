package pacage.medal.athlete;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class GoldMetalCountMapper extends Mapper<Object,Text,Text,GoldMetalCountWritable>{
    GoldMetalCountWritable gmcWritable = null;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
    }

    @Override
    protected void map(Object key, Text value,Context context) throws IOException, InterruptedException {
        String[] data = value.toString().split("\t");
        gmcWritable = new GoldMetalCountWritable();
        if(data[14].equals("Gold")){
            gmcWritable.setCountry(data[6]);
            gmcWritable.setAthleteName(data[1]);
            context.write(new Text(data[1]),gmcWritable);
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        super.cleanup(context);
    }
}
