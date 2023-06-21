package org.example;

public class FindLongestProject {
    private long id;
    private int month_count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMonth_count() {
        return month_count;
    }

    public void setMonth_count(int month_count) {
        this.month_count = month_count;
    }

    @Override
    public String toString() {
        return "FindLongestProject{" +
                "id=" + id +
                ", month_count=" + month_count +
                '}';
    }
}
