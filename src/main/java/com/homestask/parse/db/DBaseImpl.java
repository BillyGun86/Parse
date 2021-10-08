package com.homestask.parse.db;

import com.homestask.parse.entity.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBaseImpl implements DBase{
    private SessionFactory factory;

    public DBaseImpl() {
        this.factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Text.class)
                .buildSessionFactory();
    }

    public Session getCurrentSession(){
        Session session = factory.getCurrentSession();

        return session;
    }

    public void closeCurrentSession(){
        factory.close();
    }
}