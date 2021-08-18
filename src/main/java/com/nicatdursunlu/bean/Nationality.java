package com.nicatdursunlu.bean;

public class Nationality {

    private int id;
    private String name;
    private String countyName;

    public Nationality() {

    }

    public Nationality(int id, String name, String countyName) {
        this.id = id;
        this.name = name;
        this.countyName = countyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    @Override
    public String toString() {
        return "Nationality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countyName='" + countyName + '\'' +
                '}';
    }
}
