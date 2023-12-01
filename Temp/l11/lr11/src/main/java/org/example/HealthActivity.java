package org.example;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "health_activity")
public class HealthActivity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "activity_id")
    private int activityId;

    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private Date date;
    @Column(name = "duration")
    private String duration;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "diaryId")
    private HealthDiary healthDiaryByDiaryId;

    public HealthActivity(String duration, String name, Date date) {
        this.duration = duration;
        this.name = name;
        this.date = date;
    }

    public HealthActivity() {
    }

    public int getActivityId() {
        return activityId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HealthDiary getHealthDiaryByDiaryId() {
        return healthDiaryByDiaryId;
    }

    public void setHealthDiaryByDiaryId(HealthDiary healthDiaryByDiaryId) {
        this.healthDiaryByDiaryId = healthDiaryByDiaryId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
