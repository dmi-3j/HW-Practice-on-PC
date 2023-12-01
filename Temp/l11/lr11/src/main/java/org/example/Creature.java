package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class Creature {
    public Creature(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private final SessionFactory sessionFactory;

    //create
    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void addActivity(HealthActivity activity, int diaryId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(activity);
            HealthDiary diary = session.get(HealthDiary.class, diaryId);
            activity.setHealthDiaryByDiaryId(diary);
            session.update(activity);
            session.update(diary);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void addDiary(HealthDiary healthDiary, int userId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(healthDiary);
            User user = session.get(User.class, userId);
            healthDiary.setUser(user);
            session.update(healthDiary);
            session.getTransaction().commit();
            session.close();
        }
    }

    //delete
    public void deleteUser(String userName) {
        try (Session session = sessionFactory.openSession()) {
            User user = findUsersByName(userName, session);
            if (user != null) {
                session.delete(user);
                System.out.println("Пользователь " + user.getName() + " " + user.getSurname() + " удалён");
            } else System.out.println("Пользователя c именем " + userName + " не существует");
            session.getTransaction().commit();
            session.close();
        }
    }

    public void deleteActivity(String activityName) {
        try (Session session = sessionFactory.openSession()) {
            int activityId;
            session.beginTransaction();
            Query query = session.createQuery("from HealthActivity where name = :activityName").setParameter("activityName", activityName);
            List<HealthActivity> healthActivities;
            HealthActivity healthActivity = null;
            healthActivities = query.getResultList();
            if (healthActivities.size() == 1) {
                healthActivity = healthActivities.get(0);
            } else if (!healthActivities.isEmpty()) {
                System.out.println("Найдено несколько активностей с названием " + activityName + " Выберите нужный id:");
                for (HealthActivity u : healthActivities) {
                    System.out.println(u.getActivityId() + " " + u.getName() + " " + u.getDate());
                }
                Scanner scanner = new Scanner(System.in);
                activityId = scanner.nextInt();
                healthActivity = session.get(HealthActivity.class, activityId);
            }
            if (healthActivity != null) {
                session.delete(healthActivity);
                System.out.println("Активность " + healthActivity.getName() + " " + healthActivity.getDate() + " удалена");
            } else System.out.println("Активности с названием " + activityName + " не существует");
            session.getTransaction().commit();
            session.close();
        }
    }

    public void deleteDiary(String diaryName) {
        try (Session session = sessionFactory.openSession()) {
            int diaryId;
            session.beginTransaction();
            Query query = session.createQuery("from HealthDiary where name = :diaryName").setParameter("diaryName", diaryName);
            List<HealthDiary> healthDiaries;
            HealthDiary healthDiary = null;
            healthDiaries = query.getResultList();
            if (healthDiaries.size() == 1) {
                healthDiary = healthDiaries.get(0);
            } else if (!healthDiaries.isEmpty()) {
                System.out.println("Найдено несколько дневников с названием " + diaryName + " Выберите нужный id:");
                for (HealthDiary u : healthDiaries) {
                    System.out.println(u.getDiaryId() + " " + u.getName());
                }
                Scanner scanner = new Scanner(System.in);
                diaryId = scanner.nextInt();
                healthDiary = session.get(HealthDiary.class, diaryId);
            }
            if (healthDiary != null) {
                session.delete(healthDiary);
                System.out.println("Дневник " + healthDiary.getName() + " удалён");
            } else System.out.println("Дневника c названием " + diaryName + " не существует");
            session.getTransaction().commit();
            session.close();
        }
    }

    //update
    public void setNewName(String oldName, String newName) {
        try (Session session = sessionFactory.openSession()) {
            User user = findUsersByName(oldName, session);
            if (user != null) {
                user.setName(newName);
                session.update(user);
                System.out.println("Имя пользователя " + oldName + " обновлено на " + newName);
            } else System.out.println("Пользователь с именем " + oldName + " не найден");
            session.getTransaction().commit();
            session.close();
        }
    }

    //select
    private User findUsersByName(String userName, Session session) {
        int userId;
        session.beginTransaction();
        Query query = session.createQuery("from User where userName = :userName").setParameter("userName", userName);
        User user = null;
        List<User> users = query.getResultList();
        if (users.size() == 1) {
            user = users.get(0);
        } else if (!users.isEmpty()) {
            System.out.println("Найдено несколько пользователей с именем " + userName + " Выберите нужный id:");
            for (User u : users) {
                System.out.println(u.getUserId() + " " + u.getName() + " " + u.getSurname());
            }
            Scanner scanner = new Scanner(System.in);
            userId = scanner.nextInt();
            user = session.get(User.class, userId);
        }
        return user;
    }

    public void getActivitiesByUserName(String userName) {
        try (Session session = sessionFactory.openSession()) {
            User user = findUsersByName(userName, session);
            if (user != null) {
                List<String> activityNames = user.getHealthDiariesByUserId().stream().flatMap(hd -> hd.getHealthActivityList().stream()).map(HealthActivity::getName).toList();
                System.out.println(user.getName() + " " + ((!activityNames.isEmpty()) ? activityNames : "[Нет активностей]"));
            } else System.out.println("Пользователя c именем " + userName + " не существует");
            session.getTransaction().commit();
            session.close();
        }
    }

    public void getDiaresByUserName(String userName) {
        try (Session session = sessionFactory.openSession()) {
            User user = findUsersByName(userName, session);
            if (user != null) System.out.println(user);
            else System.out.println("Пользователя c именем " + userName + " не существует");
            session.getTransaction().commit();
            session.close();
        }
    }
}