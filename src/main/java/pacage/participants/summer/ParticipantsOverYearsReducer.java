package pacage.participants.summer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ParticipantsOverYearsReducer extends Reducer<IntWritable,ParticipantsOverYearsWritable,ParticipantsOverYearsWritable, IntWritable> {
    Set<ParticipantsOverYearsWritable> set ;
    ParticipantsOverYearsWritable poyWritable ;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        set = new HashSet<>();
        poyWritable = new ParticipantsOverYearsWritable();
    }

    @Override
    protected void reduce(IntWritable key, Iterable<ParticipantsOverYearsWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (ParticipantsOverYearsWritable p:values ){
            sum++;
            poyWritable.setAthleteName(p.getAthleteName());
            poyWritable.setYear(p.getYear());
            poyWritable.setSeason(p.getSeason());
        }
        context.write(poyWritable,new IntWritable(sum));
    }

}
