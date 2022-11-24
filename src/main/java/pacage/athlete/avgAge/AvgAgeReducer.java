package pacage.athlete.avgAge;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AvgAgeReducer extends Reducer<Text, AvgAgeWritable, AvgAgeWritable2, NullWritable> {
    AvgAgeWritable ageWritable;
    AvgAgeWritable2 avgAgeWritable2;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
    }

    @Override
    protected void reduce(Text key, Iterable<AvgAgeWritable> values,Context context) throws IOException, InterruptedException {
        avgAgeWritable2 = new AvgAgeWritable2();
        double maleCount=0;double femaleCount=0;
        double maleAge = 0;double femaleAge = 0;
        for (AvgAgeWritable aaw : values){
            if ("M".equals(aaw.getGender())){
                maleCount++;
                maleAge+= aaw.getAge();
            } else if ("F".equals(aaw.getGender())) {
                femaleCount++;
                femaleAge+=aaw.getAge();
            }
        }

        double avgM = Math.round(maleAge*100/maleCount)/100.0;
        System.out.println(maleAge + "---" + maleCount + "---" + avgM);
        if (femaleCount !=0 ){
            double avgF = Math.round(femaleAge*100/femaleCount)/100.0;
            avgAgeWritable2.setFemaleAvg(avgF);
        }

        avgAgeWritable2.setMaleAvg(avgM);
        avgAgeWritable2.setYear(Integer.parseInt(key.toString()));
        context.write(avgAgeWritable2,NullWritable.get());
    }

}
