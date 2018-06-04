package com.apress.prospring4.ch7;


import com.apress.prospring4.ch7.componets.Contact;
import com.apress.prospring4.ch7.componets.ContactTelDetail;
import com.apress.prospring4.ch7.componets.Hobby;
import com.apress.prospring4.ch7.dao.ContactDao;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringHibernateSample {
    private static final Logger LOGGER = Logger.getLogger(SpringHibernateSample.class);

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("app-context-annotation.xml");

        ContactDao dao = context.getBean("contactDao",ContactDao.class);

        listContacts(dao.findAllWithDetail());



    }
    private static void simpleList(List<Contact> contacts){
        for(Contact c: contacts){
            LOGGER.info(c);
        }
    }

    private static void listContacts(List<Contact> contacts){
        for(Contact c: contacts){
            LOGGER.info(c);
            if(c.getContactTelDetails() != null){
                for(ContactTelDetail contactTelDetail : c.getContactTelDetails()){
                    LOGGER.info("----"+contactTelDetail);
                }
                if(c.getHobbies() != null){
                    for(Hobby h: c.getHobbies()){
                        LOGGER.info("****"+h);
                    }
                }
            }
        }
    }
}
