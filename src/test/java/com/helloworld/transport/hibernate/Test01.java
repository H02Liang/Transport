package com.helloworld.transport.hibernate;

import com.helloworld.transport.entity.Passenger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class Test01 {
    SessionFactory sessionFactory = null;
    Session session = null;
    Transaction transaction = null;

    @Before
    public void init() {
        Configuration cfg = new Configuration();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    @Test
    public void testInsert() {
        try {
            session = sessionFactory.openSession();
            Passenger passenger = new Passenger();
            Date date = new Date();
            passenger.setName("美洋洋").setAge(18).setSex(1)
                    .setAddress("大森林").setRemarks("眉飞色舞")
                    .setCreateTime(date).setUpdateTime(date);
            transaction = session.beginTransaction();
            session.save(passenger);
            transaction.commit();
            System.out.println("Insert success！！\n" + passenger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery() {
        session = sessionFactory.openSession();
        Passenger passenger = new Passenger();
        passenger.setName("光头强");
        transaction = session.beginTransaction();
        String querySql = "from Passenger p where p.name=?1 and 1=1";
        Query query = session.createQuery(querySql, Passenger.class);
        query.setParameter(1, passenger.getName());
        List<Passenger> list = query.list();
        for (Passenger o : list) {
            System.out.println(o);
        }
        System.out.println("Query success！！");
        transaction.commit();
    }

    @Test
    public void testUpdate() {
        session = sessionFactory.openSession();
        Passenger passenger = new Passenger();
        passenger.setName("光头强").setRemarks("阴阳怪气").setUpdateTime(new Date());
        transaction = session.beginTransaction();
        String updateSql = "update Passenger p set p.remarks=?1, p.updateTime=?2 where p.name=?3";
        Query update = session.createQuery(updateSql);
        update.setParameter(1, passenger.getRemarks());
        update.setParameter(2, passenger.getUpdateTime());
        update.setParameter(3, passenger.getName());
        update.executeUpdate();
        System.out.println("Update success!!\n");
        transaction.commit();
    }

    @Test
    public void testDelete() {
        session = sessionFactory.openSession();
        Passenger passenger = new Passenger();
        passenger.setName("光头强").setAge(36);
        String delete = "delete from passender where name='光头强'";
        transaction = session.beginTransaction();
        NativeQuery query = session.createSQLQuery(delete);
        query.executeUpdate();
        transaction.commit();
        System.out.println("Delete success!!\n" + passenger.getName());
    }

    @After
    public void release() {
        if (session != null) {
            session.close();
        }
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
