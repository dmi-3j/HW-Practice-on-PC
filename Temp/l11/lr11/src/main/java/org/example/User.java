package org.example;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "Users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "surname")
    private String surname;

    @Column(name = "userName")
    private String userName;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "gender")
    private char gender;
    @Column(name = "weight")
    private float weight;
    @Column(name = "height")
    private float height;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<HealthDiary> healthDiariesByUserId;

    public User(String surname, String userName, String patronymic, Date dateOfBirth, String phoneNumber, char gender, float weight, float height) {
        this.surname = surname;
        this.userName = userName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public User() {
    }

    public Collection<HealthDiary> getHealthDiariesByUserId() {
        return healthDiariesByUserId;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setHealthDiariesByUserId(Collection<HealthDiary> healthDiariesByUserId) {
        this.healthDiariesByUserId = healthDiariesByUserId;
    }
    @Override
    public String toString(){
        StringBuilder diares = new StringBuilder();
    for (HealthDiary hd : this.healthDiariesByUserId) {
        diares.append(hd.getName()).append(" ");
    }
    if (this.healthDiariesByUserId.isEmpty()) diares.append("Дневники отсутствуют");
    return this.userName + " " + this.surname + "\nДневники здоровья: " + diares;
    }
}

