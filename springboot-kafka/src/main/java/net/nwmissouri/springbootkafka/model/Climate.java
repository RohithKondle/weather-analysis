package net.nwmissouri.springbootkafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Climate implements Serializable {

    private int count;

    @JsonProperty("data")
    private List<Data> data;

    public Climate(int count, List<Data> data) {
        this.count = count;
        this.data = data;
    }

    public Climate() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}