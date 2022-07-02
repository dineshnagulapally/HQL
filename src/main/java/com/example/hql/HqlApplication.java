package com.example.hql;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.spi.SessionFactoryServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.spi.ServiceRegistry;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class HqlApplication {

    public static void main(String[] args) {
        Configuration con = new Configuration().configure().addAnnotatedClass(Student.class);
        //ServiceRegistry re = (ServiceRegistry) new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        int b = 60;
        Query q = session.createQuery("select sum(marks) from Student where marks> :b");
        q.setParameter("b",b);
        Long marks = (Long) q.uniqueResult();
        System.out.println(marks);
        /*
        List<Student> st = q.list();
        for (Student sts:
             st) {
            System.out.println(st);
        }

        Random r = new Random();
        for (int i=0;i<50;i++){
            Student s = new Student();
            s.setId(i);
            s.setMarks(r.nextInt(100));
            s.setName("name"+i);
            session.save(s);
        }*/
        session.getTransaction().commit();
    }

}
