package pacage.participants.winter;

import java.util.Objects;

public class test {
    public static void main(String[] args) {
//        Set<Person> set = new HashSet<>();
//        set.add(new Person("summer","1","1992"));
//        set.add(new Person("winter","1","1992"));
//        set.add(new Person("winter","1","1992"));
//        set.add(new Person("winter","1","1992"));
//        set.add(new Person("winter","1","1992"));
//        set.add(new Person("winter","1","1994"));
//        set.add(new Person("winter","2","1994"));
//        set.add(new Person("summer","2","1994"));
//        for (Person p : set){
//            System.out.println(p.toString());
//        }
        double a = 1666*100/72/100.0;
        System.out.println(a);
    }
}
class Person implements Comparable<Person>{
    String id;
    String year;
    String season;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id) && year.equals(person.year) && season.equals(person.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, season);
    }

    public Person(String season, String id, String year ) {
        this.id = id;
        this.year = year;
        this.season = season;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", year='" + year + '\'' +
                ", season='" + season + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return Integer.parseInt(o.getYear()) - Integer.parseInt(this.getYear());
    }
}