package pacage.participants.summer;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class ParticipantsOverYearsWritable implements Writable {
    private String athleteName;
    private int year;
    private String season;

    @Override
    public String toString() {
        return year + "年参赛人数为：";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantsOverYearsWritable that = (ParticipantsOverYearsWritable) o;
        return year == that.year && athleteName.equals(that.athleteName) && season.equals(that.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(athleteName, year, season);
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(athleteName);
        dataOutput.writeInt(year);
        dataOutput.writeUTF(season);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        athleteName = dataInput.readUTF();
        year = dataInput.readInt();
        season = dataInput.readUTF();
    }
}
