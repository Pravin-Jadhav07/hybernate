package org.example;


import org.example.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {


        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Boolean isSuccess = false;

        try {
            sessionFactory = new Configuration().configure()
                    .addAnnotatedClass(Student.class).buildSessionFactory();
            session = sessionFactory.openSession();

            Student student1 = session.get(Student.class,1);
            if( student1 != null )
                System.out.println(student1);
            else
                System.out.println("no records..");
            isSuccess = true;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            e.printStackTrace();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();

            e.printStackTrace();
        } finally {
            if (transaction != null && isSuccess)
                transaction.commit();

            if (session != null && session.isOpen())
                session.close();

            if (sessionFactory != null)
                sessionFactory.close();
        }
    }
}