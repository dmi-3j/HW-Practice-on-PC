package org.example;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.OptimisticLockException;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static final int THREAD_COUNT = 8;
    public static void main(String[] args) {
        SessionFactory configuration = new Configuration()
                .addAnnotatedClass(Items.class)
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = configuration.getCurrentSession();
//           try{
//            for(int i = 0; i < 40; i++)
//            {
//                session = configuration.getCurrentSession();
//                session.beginTransaction();
//                Items item = new Items(0);
//                session.save(item);
//                session.getTransaction().commit();
//            }
//        }
//        finally {
//            configuration.close();
//            session.close();
//        }
        threadTest();

    }
    public static void threadTest() {
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        Thread[] threads = new Thread[THREAD_COUNT];
        try(SessionFactory sf = new Configuration().configure().addAnnotatedClass(Items.class).buildSessionFactory()){

            for (int i = 0; i < THREAD_COUNT; i++) {
                final int ii = i+1;
                threads[i] = new Thread(() -> {
                    System.out.println("Thread #" + ii + " start");
                    for (int k = 0; k < 20000; k++) {
                        System.out.println("k " + k);
                        boolean upd = false;
                        while (!upd) {
                            Session session = sf.getCurrentSession();
                            Long rndRow = (long)((Math.random() * 40) + 1);
                            try {
                                session.beginTransaction();
                                Items items = session.get(Items.class, rndRow);
                                long tmp = items.getVal();
                                items.setVal(tmp + 1);
                                sleep(5);
                                session.save(items);
                                session.getTransaction().commit();
                                upd = true;
                            }
                            catch (OptimisticLockException | HibernateException e) {
                                session.getTransaction().rollback();
                                e.printStackTrace();
                            }
                            session.close();
                        }
                    }
                    countDownLatch.countDown();
                });
                threads[i].start();
            }
            try {
                countDownLatch.await();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end");
        }
    }
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}