package org.example.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String city;
    @ManyToMany (cascade = CascadeType.ALL)
    private Set<Cource> courceSet;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", courceSet=" + courceSet +
                '}';
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Cource> getCourceSet() {
        return courceSet;
    }

    public void setCourceSet(Set<Cource> courceSet) {
        this.courceSet = courceSet;
    }
}
