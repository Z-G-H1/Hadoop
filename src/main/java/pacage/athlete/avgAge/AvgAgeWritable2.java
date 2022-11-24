package pacage.athlete.avgAge;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AvgAgeWritable2 implements Writable {
    private int year;

    private double maleAvg;

    private double femaleAvg;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMaleAvg() {
        return maleAvg;
    }

    public void setMaleAvg(double maleAvg) {
        this.maleAvg = maleAvg;
    }

    public double getFemaleAvg() {
        return femaleAvg;
    }

    public void setFemaleAvg(double femaleAvg) {
        this.femaleAvg = femaleAvg;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(year);
        dataOutput.writeDouble(maleAvg);
        dataOutput.writeDouble(femaleAvg);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        year = dataInput.readInt();
        maleAvg = dataInput.readDouble();
        femaleAvg = dataInput.readDouble();
    }

    @Override
    public String toString() {
        return year+"年:\t参赛男运动员的平均年龄为：" + maleAvg + "\t参赛女运动员的平均年龄为：" + femaleAvg;
    }
}
