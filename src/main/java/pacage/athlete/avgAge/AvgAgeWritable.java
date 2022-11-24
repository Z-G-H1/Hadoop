package pacage.athlete.avgAge;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class AvgAgeWritable implements Writable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int age;
    private String gender;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AvgAgeWritable that = (AvgAgeWritable) o;
        return age == that.age && name.equals(that.name) && gender.equals(that.gender) && Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, gender, year);
    }

    private String year;



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(name);
        dataOutput.writeInt(age);
        dataOutput.writeUTF(gender);
        dataOutput.writeUTF(year);
    }

    @Override
    public String toString() {
        return "AvgAgeWritable{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        name = dataInput.readUTF();
        age = dataInput.readInt();
        gender = dataInput.readUTF();
        year = dataInput.readUTF();
    }
}
