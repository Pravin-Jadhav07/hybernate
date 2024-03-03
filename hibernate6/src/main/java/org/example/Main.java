package org.example;


import org.example.model.Employee;
import org.example.model.Laptop;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

// consider in this example one employee has multiple and unique laptops.

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Employee.class).addAnnotatedClass(Laptop.class)
                .buildSessionFactory();
        Session session = null;
        Transaction transaction = null;
        Boolean isSucess = false;

//        Employee employee = new Employee();
//        employee.setName("govind");
//
//        Laptop laptop1 = new Laptop();  // first laptop
//        laptop1.setName("dell");
//        laptop1.setEmployee(employee);  // MANY to ONE
//
//        Laptop laptop2 = new Laptop();  // second laptop
//        laptop2.setName("lenovo");
//        laptop2.setEmployee(employee);  // MANY to ONE
//
//        List<Laptop> laptops = new ArrayList<>();    // creating list of laptop
//        laptops.add(laptop1);
//        laptops.add(laptop2);
//
//        employee.setLaptop(laptops);   // ONE to MANY

        try{
            session = sessionFactory.openSession();

//            transaction = session.beginTransaction();
//            session.persist(employee);

            // This is one type to fetch data
//            Employee employee = session.get(Employee.class,1);
//            System.out.println(employee.getName());
//            employee.getLaptop().forEach( laptop -> System.out.println(laptop)  );

            // Another type to fetch data using toString()
            Employee employee = session.get(Employee.class,2);
            System.out.println(employee);
            // output :
            // Employee{id=2, name='govind', laptop=[Laptop{id=3, name='dell'}, Laptop{id=4, name='lenovo'}]}


            isSucess=true;
        }
        catch (HibernateException e){
            if( transaction != null )
                transaction.rollback();

            e.printStackTrace();
        }
        catch (Exception e){
            if( transaction != null )
                transaction.rollback();

            e.printStackTrace();
        }
        finally {
            if( transaction != null && isSucess )
                transaction.commit();

            if( session != null  )
                session.close();

            if( sessionFactory != null )
                sessionFactory.close();
        }

    }
}