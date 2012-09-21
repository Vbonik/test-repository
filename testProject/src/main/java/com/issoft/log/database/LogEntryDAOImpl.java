package com.issoft.log.database;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author: AS
 */
public class LogEntryDAOImpl implements LogEntryDAO, Runnable {

    private HibernateTemplate hibernateTemplate;
    LogEntry logEntry;
    Thread runner;

    @Override
    public void saveEntry(String userName, String authorities, String action, String status) {
        logEntry = new LogEntry();

        logEntry.setUserName(userName);
        logEntry.setAuthorities(authorities);
        logEntry.setAction(action);
        logEntry.setStatus(status);

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Minsk")); //must be called once??
        logEntry.setDate(Calendar.getInstance().getTime());

        runner = new Thread(this);
        runner.start();
    }

    @Override
    public synchronized void run() {
        hibernateTemplate.saveOrUpdate(logEntry);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
}
