package com.issoft.database.log.entry;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author: AS
 */
public class LogEntryDAOImpl implements LogEntryDAO {

    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    public void saveEntry(String userName, String authorities, String action, String status) {
        LogEntry logEntry = new LogEntry();

        logEntry.setUserName(userName);
        logEntry.setAuthorities(authorities);
        logEntry.setAction(action);
        logEntry.setStatus(status);

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Minsk")); //must be called once??
        logEntry.setDate(Calendar.getInstance().getTime());

        hibernateTemplate.saveOrUpdate(logEntry);
    }
}
