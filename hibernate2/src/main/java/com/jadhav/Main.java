package com.jadhav;


import com.jadhav.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        System.out.print("Hello and welcome!");
        Configuration configuration = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction trasaction = null;
        boolean isSucess = false;

        configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();


        Student student = new Student();
//        student.setId(1);
        student.setName("pravin");
        student.setEmail("p@j.com");
        student.setAge(23);
        student.setCity("Pune");


        try {
            trasaction = session.beginTransaction();
            session.persist(student);
            isSucess = true;
        }
        catch ( HibernateException e ){
            e.printStackTrace();
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
            if( isSucess ){
                trasaction.commit();

            }
            else {
                trasaction.rollback();
            }
            session.close();
            sessionFactory.close();
        }


    }
}