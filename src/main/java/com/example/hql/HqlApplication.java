package com.example.hql;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.spi.SessionFactoryServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.spi.ServiceRegistry;
import java.util.List;
import java.util.Map;
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
    //Native Queries
        SQLQuery q = session.createSQLQuery("select name,marks from student");
        //q.addEntity(Student.class);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List st = q.list();
        for (Object sts:
             st) {
            Map m = (Map)sts;
            System.out.println(m.get("name")+" : "+m.get("marks"));
        }
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
