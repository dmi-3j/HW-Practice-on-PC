package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(HealthDiary.class).addAnnotatedClass(HealthActivity.class).addAnnotatedClass(User.class);
        configuration.configure();
        try (SessionFactory sf = configuration.buildSessionFactory()) {
            Creature creature = new Creature(sf);
            User user = new User("Vekker", "Elia", "Viktorovna", Date.valueOf("2002-07-16"), "89091329714", 'w', 66, 186);
            creature.addUser(user);
            User user1 = new User("Vekker2", "Elia2", "Viktorovna", Date.valueOf("2002-07-16"), "89091329714", 'w', 66, 186);
            creature.addUser(user1);
            User user2 = new User("Ivanov", "Ivan", "Ivanovich", Date.valueOf("1980-05-20"), "89101234567", 'm', 75, 175);
            creature.addUser(user2);
            User user3 = new User("Petrova", "Anna", "Petrovna", Date.valueOf("1990-10-30"), "89202345678", 'w', 60, 165);
            creature.addUser(user3);
            User user4 = new User("Sidorov", "Sergey", "Sergeevich", Date.valueOf("2000-01-01"), "89303456789", 'm', 80, 180);
            creature.addUser(user4);

            HealthActivity activity1 = new HealthActivity("1 hour", "Running", Date.valueOf("2023-11-21"));
            HealthActivity activity2 = new HealthActivity("30 seconds", "Planka", Date.valueOf("2023-10-11"));
            HealthActivity activity3 = new HealthActivity("2 hours", "Cycling", Date.valueOf("2023-09-01"));
            HealthActivity activity4 = new HealthActivity("45 minutes", "Swimming", Date.valueOf("2023-08-21"));
            HealthDiary healthDiary = new HealthDiary("LazyDiary");
            creature.addDiary(healthDiary, 1);
            HealthDiary healthDiary2 = new HealthDiary("HardDiary");
            creature.addDiary(healthDiary2, 2);
            HealthDiary healthDiary3 = new HealthDiary("My Diary");
            creature.addDiary(healthDiary3, 3);
            HealthDiary healthDiary4 = new HealthDiary("ForLab");
            creature.addDiary(healthDiary4, 4);
            creature.addActivity(activity1, 1);
            creature.addActivity(activity2, 2);
            creature.addActivity(activity3, 3);
            creature.addActivity(activity4, 1);
            //creature.getActivitiesByUserName("Elia");
            //creature.deleteActivity("Planka");
            //creature.deleteDiary("L1azyDiary");
            //creature.setNewName(2, "Elechka");
            //creature.getDiaresByUserName("Elia");
            // creature.deleteUser("Elia");
           // System.out.println(user2);
            creature.getDiaresByUserName("Elia");
            creature.getDiaresByUserName("Sergey");
            creature.getActivitiesByUserName("Elia");
//            creature.deleteDiary("gfg");
            //creature.getDiares(user);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Введите команду:");
                String command = scanner.nextLine();
                String[] parts = command.split(" ", 2);
                if (Objects.equals(parts[0], "/exit")) {
                    break;
                }
                if (parts.length == 1) {
                    System.out.println("Вы не ввели имя/название");
                } else if (Objects.equals(parts[0], "/getActivitiesByUserName")) {
                    creature.getActivitiesByUserName(parts[1]);
                } else if (Objects.equals(parts[0], "/getDiaresByUserName")) {
                    creature.getDiaresByUserName(parts[1]);
                } else if (Objects.equals(parts[0], "/deleteDiary")) {
                    creature.deleteDiary(parts[1]);
                } else if (Objects.equals(parts[0], "/deleteActivity")) {
                    creature.deleteActivity(parts[1]);
                } else if (Objects.equals(parts[0], "/deleteUser")) {
                    creature.deleteUser(parts[1]);
                } else if (Objects.equals(parts[0], "/setNewName")) {
                    System.out.println("Введите новое имя:");
                    String newName = scanner.nextLine();
                    creature.setNewName(parts[1], newName);
                } else {
                    System.out.println("Неверная команда");
                }
            }
        }
    }
}