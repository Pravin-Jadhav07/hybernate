package org.example;


import org.example.model.Cource;
import org.example.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        System.out.println("let's get start it...............!");

//        Cource cource = new Cource();
//        cource.setName("java");
//        cource.setPrice(100);
//
//        Cource cource1 = new Cource();
//        cource1.setName("Python");
//        cource1.setPrice(200);
//
//        Cource cource2 = new Cource();
//        cource2.setName("cpp");
//        cource2.setPrice(150);
//
//        // s1 has three cources
//        Set<Cource> s1 = new HashSet<>();
//        s1.add(cource);
//        s1.add(cource1);
//        s1.add(cource2);
//
//        // s2 has two cources
//        Set<Cource> s2 = new HashSet<>();
//        s2.add(cource);
//        s2.add(cource1);
//
//
//        // student1 has s1 courceSet
//        Student student1 = new Student();
//        student1.setName("pravin");
//        student1.setCity("Pune");
//        student1.setCourceSet(s1);
//
//        // student2 has s2 courceSet
//        Student student2 = new Student();
//        student2.setName("Govind");
//        student2.setCity("Beed");
//        student2.setCourceSet(s2);

        SessionFactory sf = new Configuration().addAnnotatedClass(Student.class)
                            .addAnnotatedClass(Cource.class).buildSessionFactory();

        Session session = null;
        Transaction transaction = null;
        boolean isSuccess = false;

        try {
            session = sf.openSession();
//            transaction = session.beginTransaction();
//            session.persist(student1);
//            session.persist(student2);

            Student student1 = session.get(Student.class,1);
            Student student2 = session.get(Student.class,2);

            System.out.println(student1);
            System.out.println(student2);

            isSuccess = true;

        }
        catch (HibernateException e){
            if ( transaction != null )
                transaction.rollback();
            e.printStackTrace();
        }
        catch ( Exception e){
            if ( transaction != null )
                transaction.rollback();
            e.printStackTrace();
        }
        finally {
            if ( transaction != null && isSuccess )
                transaction.commit();
            if ( session != null )
                session.close();

            if ( sf != null )
                sf.close();
        }

        System.out.println("your job is done.........");

    }
}