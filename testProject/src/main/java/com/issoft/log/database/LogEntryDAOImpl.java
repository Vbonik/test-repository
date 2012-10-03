package com.issoft.log.database;

import com.issoft.log.database.entity.LogEntry;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * @author: AS
 */

public class LogEntryDAOImpl implements LogEntryDAO {

    private HibernateTemplate hibernateTemplate;
    LogEntry logEntry;

    @Override
    public void saveEntry(String userName, String authorities, String action, String status) {
        logEntry = new LogEntry();

        logEntry.setUserName(userName);
        logEntry.setAuthorities(authorities);
        logEntry.setAction(action);
        logEntry.setStatus(status);

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Minsk")); //must be called once??
        logEntry.setDate(Calendar.getInstance().getTime());

        hibernateTemplate.saveOrUpdate(logEntry);
    }

    @Override
    public List<LogEntry> list() {
        DetachedCriteria criteria = DetachedCriteria.forClass(LogEntry.class);
        List result = hibernateTemplate.findByCriteria(criteria);
        return result;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
        hibernateTemplate.setCacheQueries(true);
    }
}
