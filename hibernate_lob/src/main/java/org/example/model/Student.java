package org.example.model;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String city;
    @Lob
    @Column( columnDefinition = "LONGBLOB")
    private byte[] profilePic;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private char[] resume;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", profilePic=" + Arrays.toString(profilePic) +
                ", resume=" + Arrays.toString(resume) +
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

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    public char[] getResume() {
        return resume;
    }

    public void setResume(char[] resume) {
        this.resume = resume;
    }
}
