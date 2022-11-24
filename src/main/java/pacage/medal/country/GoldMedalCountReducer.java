package pacage.medal.country;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoldMedalCountReducer extends Reducer<Text, GoldMedalCountWritable,GoldMedalCountWritable, NullWritable> {
    GoldMedalCountWritable gmcWritable = null;
    List<GoldMedalCountWritable> list;
    int topnum;
    @Override
    protected void setup(Reducer<Text, GoldMedalCountWritable, GoldMedalCountWritable, NullWritable>.Context context) throws IOException, InterruptedException {
        list = new ArrayList<>();
        topnum = context.getConfiguration().getInt("topn",10);
    }


    @Override
    protected void reduce(Text key, Iterable<GoldMedalCountWritable> values, Reducer<Text, GoldMedalCountWritable, GoldMedalCountWritable, NullWritable>.Context context) throws IOException, InterruptedException {
        gmcWritable = new GoldMedalCountWritable();
        int sum = 0;
        for (GoldMedalCountWritable g: values) {
            sum++;
            gmcWritable.setCountryName(g.getCountryName());
            gmcWritable.setSum(sum);
        }
        list.add(gmcWritable);
        Collections.sort(list);
        if(list.size() > topnum){
            list.remove(list.size()-1);
        }

    }
    @Override
    protected void cleanup( Context context) throws IOException, InterruptedException {
        for (GoldMedalCountWritable gmc : list){
            context.write(gmc,NullWritable.get());
        }
    }

}
