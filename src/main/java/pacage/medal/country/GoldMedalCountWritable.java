package pacage.medal.country;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class GoldMedalCountWritable implements Writable,Comparable<GoldMedalCountWritable> {

    //国家名称
    private String countryName;
    //奖牌
    private String medal;
    private int sum;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(countryName);
        dataOutput.writeUTF(medal);
        dataOutput.writeInt(sum);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        countryName = dataInput.readUTF();
        medal = dataInput.readUTF();
        sum = dataInput.readInt();
    }

    @Override
    public String toString() {
        return "国家：" + countryName + "金牌数：" + sum;
    }

    @Override
    public int compareTo(GoldMedalCountWritable o) {
        return o.getSum() - this.getSum();
    }
}
