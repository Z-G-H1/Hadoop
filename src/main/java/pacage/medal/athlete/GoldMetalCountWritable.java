package pacage.medal.athlete;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class GoldMetalCountWritable implements Writable,Comparable<GoldMetalCountWritable>{

    private String athleteName;
    private String country;
    private int sum;

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(athleteName);
        dataOutput.writeUTF(country);
        dataOutput.writeInt(sum);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        athleteName = dataInput.readUTF();
        country = dataInput.readUTF();
        sum = dataInput.readInt();
    }

    @Override
    public String toString() {
        return "运动员\t" + athleteName + "国家\t" + country + "金牌数\t" + sum;
    }
    @Override
    public int compareTo(GoldMetalCountWritable o) {
        return o.getSum() - this.getSum();
    }
}
