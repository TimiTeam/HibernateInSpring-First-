package com.apress.prospring4.ch7.dao;


import com.apress.prospring4.ch7.componets.Contact;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;


@Transactional
// @Transactional annotation defines the requirements for transactions
@Repository("contactDao")
public class ContactDaoImpl implements ContactDao,Serializable {
    private static final Logger LOGGER = Logger.getLogger(ContactDaoImpl.class);

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Contact c").list();
    }

    public List<Contact> findAllWithDetail() {
        return null;
    }

    public Contact findById(Long id) {
        return null;
    }

    public Contact save(Contact contact) {
        return null;
    }

    public void delete(Contact contact) {

    }
}
