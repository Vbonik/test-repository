package com.issoft.database.log;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Calendar;
import java.util.List;
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
    public void saveEntry(User user, String action, String status) {
        LogEntry logEntry = new LogEntry();
        logEntry.setUserName(user.getUsername());
        logEntry.setAuthorities(getAuthorities(user));
        logEntry.setAction(action);
        logEntry.setStatus(status);
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Minsk")); //must be called once??
        logEntry.setDate(Calendar.getInstance().getTime());
        hibernateTemplate.saveOrUpdate(logEntry);
    }

    /**
     * Gets collection of user authorities and converting them to one string.
     *
     * @param user
     * @return String authorities
     */
    private String getAuthorities(User user) {
        StringBuffer authorities = new StringBuffer();
        for (GrantedAuthority authority : user.getAuthorities()) {
            authorities.append(authority.getAuthority() + ", ");
        }
        return authorities.toString();
    }

    @Override
    public List<LogEntry> listEntry() {
        return hibernateTemplate.find("from Entry");
    }
}
