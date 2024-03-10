package org.example;

import org.example.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("let's get start............!");


        FileInputStream fis = null;
        byte image[] = null;
        FileReader fileReader = null;
        char resume[] = null;

        try{
            // reading image
            fis  = new FileInputStream("C:\\Users\\Ash\\Desktop\\profile.JPG");
            image = new byte[fis.available()];
            fis.read(image);

            //reading char file
            File file = new File("C:\\Users\\Ash\\Desktop\\git_cmd.txt");
            fileReader = new FileReader(file);
            resume = new char[(int) file.length()];
            fileReader.read(resume);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                fileReader.close();
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        System.out.println("set data to student................");
        Student student = new Student();
        student.setName("Pravin");
        student.setCity("Pune");
        student.setProfilePic(image);
        student.setResume(resume);



        SessionFactory sf = new Configuration().addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean isSucess = false;

        try{
            session = sf.openSession();
            transaction = session.beginTransaction();
            session.persist(student);
            isSucess = true;
        }
        catch (HibernateException e)
        {
            if( transaction != null )
                transaction.rollback();
            e.printStackTrace();
        }
        catch (Exception e)
        {
            if( transaction != null )
                transaction.rollback();
            e.printStackTrace();
        }
        finally {
            if( transaction != null && isSucess )
                transaction.commit();
            if( session != null && session.isOpen() )
                session.close();
            if ( sf != null )
                sf.close();
        }


//        ******  Reading data from database and write it to local system *******


//        SessionFactory sf = new Configuration().addAnnotatedClass(Student.class)
//        .buildSessionFactory();
//        Session session = null;
//        boolean isSucess = false;
//
//        FileOutputStream fos = null;
//        FileWriter fileWriter = null;
//
//        try {
//            session = sf.openSession();
//            Student student = session.get(Student.class,1);
//
//            fos = new FileOutputStream("pravin.JPG");
//            fileWriter = new FileWriter("git_cmd.txt");
//
//            fos.write(student.getProfilePic());
//            fileWriter.write(student.getResume());
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch ( HibernateException e ){
//            e.printStackTrace();
//        }
//        catch ( Exception e){
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                fos.close();
//                fileWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        System.out.println("your job is donee................");
    }
}