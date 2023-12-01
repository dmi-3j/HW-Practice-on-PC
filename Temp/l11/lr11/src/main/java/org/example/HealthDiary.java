package org.example;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "health_diary")
public class HealthDiary {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "diary_id")
    private int diaryId;
    @Column(name = "nameDiary")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public HealthDiary(String name) {
        this.name = name;
    }

    public HealthDiary() {
    }

    //    @Column(name = "calories_received")
//    private int caloriesReceived;
//
//    @Column(name = "calories_spent")
//    private int caloriesSpent;
    @OneToMany(mappedBy = "healthDiaryByDiaryId", cascade = CascadeType.ALL)
    private List<HealthActivity> healthActivityList;


    public List<HealthActivity> getHealthActivityList() {
        return healthActivityList;
    }

    public void setHealthActivityList(List<HealthActivity> healthActivityList) {
        this.healthActivityList = healthActivityList;
    }

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
