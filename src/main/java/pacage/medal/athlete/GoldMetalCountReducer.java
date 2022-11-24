package pacage.medal.athlete;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoldMetalCountReducer extends Reducer<Text,GoldMetalCountWritable,GoldMetalCountWritable, NullWritable> {
    GoldMetalCountWritable gmcWritable = null;
    List<GoldMetalCountWritable> list;
    int topnum;
    @Override
    protected void setup(Reducer<Text, GoldMetalCountWritable, GoldMetalCountWritable, NullWritable>.Context context) throws IOException, InterruptedException {
        list = new ArrayList<>();
        topnum = context.getConfiguration().getInt("topn",10);
    }

    @Override
    protected void reduce(Text key, Iterable<GoldMetalCountWritable> values, Reducer<Text, GoldMetalCountWritable, GoldMetalCountWritable, NullWritable>.Context context) throws IOException, InterruptedException {
        gmcWritable = new GoldMetalCountWritable();
        int sum = 0;
        for ( GoldMetalCountWritable g: values) {
            sum++;
            gmcWritable.setAthleteName(g.getAthleteName());
            gmcWritable.setCountry(g.getCountry());
            gmcWritable.setSum(sum);
        }
        list.add(gmcWritable);
        Collections.sort(list);
        if(list.size() > topnum){
            list.remove(list.size()-1);
        }
    }

    @Override
    protected void cleanup(Reducer<Text, GoldMetalCountWritable, GoldMetalCountWritable, NullWritable>.Context context) throws IOException, InterruptedException {
        for (GoldMetalCountWritable gmc : list){
            context.write(gmc,NullWritable.get());
        }
    }
}
