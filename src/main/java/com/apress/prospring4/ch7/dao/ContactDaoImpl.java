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

    @Transactional(readOnly = true)
    public List<Contact> findAllWithDetail() {
        return sessionFactory.getCurrentSession().getNamedQuery("Contact.findAllWithDetail").list();
    }

    @Transactional(readOnly = true)
    public Contact findById(Long id) {
        return (Contact)sessionFactory.getCurrentSession().getNamedQuery("Contact.findById").
                setParameter("id", id).uniqueResult();
    }

    public Contact save(Contact contact) {
        sessionFactory.getCurrentSession().saveOrUpdate(contact);
        LOGGER.info("Contact saved with id: "+contact.getId());
        return contact;
    }

    public void delete(Contact contact) {
        sessionFactory.getCurrentSession().delete(contact);
        LOGGER.info("Delete the contact with ID: "+contact.getId());
    }
}
