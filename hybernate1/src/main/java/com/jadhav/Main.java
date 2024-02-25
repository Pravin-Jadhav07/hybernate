package com.jadhav;


import com.jadhav.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        System.out.print("Let's get start..!");

        // 1. Create Configuration
        Configuration configuration = new Configuration();

        // 2. configure hibernate.cfg.xml file to configuration object
        configuration.configure();
//        configuration.configure("fileName");       if we cannot not use hibernate.cfg.xml then specify given filename.

        // 3. Create Session Factory Object
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // 4. get Session Object from SessionFactory.
        Session session = sessionFactory.openSession();

        // 5. begin transaction with session.
        Transaction transaction = session.beginTransaction();

        Student student = new Student();
//        student.setId(1);
        student.setName("shubham");
        student.setEmail("s@s.com");
        student.setAge(23);
        student.setCity("Latur");

        // 6. perform operation
        session.save(student);

        // 7. perform trasaction operation
        transaction.commit();

        // 8. close the session.
        session.close();


        System.out.println("Your job is done...");

    }
}