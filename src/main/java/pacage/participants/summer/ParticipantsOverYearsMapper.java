package pacage.participants.summer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ParticipantsOverYearsMapper extends Mapper<Object, Text, IntWritable,ParticipantsOverYearsWritable> {
    Set<ParticipantsOverYearsWritable> set ;
    ParticipantsOverYearsWritable poyWritable ;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        set = new HashSet<>();

    }

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] data = value.toString().split("\t");
        if("Summer".equals(data[10])){
            poyWritable = new ParticipantsOverYearsWritable();
            poyWritable.setAthleteName(data[1]);
            poyWritable.setYear(Integer.parseInt(data[9]));
            poyWritable.setSeason(data[10]);
            set.add(poyWritable);
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (ParticipantsOverYearsWritable p : set){
            System.out.println(p.getAthleteName() + ": " + p.getYear()+p.getSeason());
            context.write(new IntWritable(p.getYear()),p);
        }
    }
}
