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
public class EntryDAOImpl implements EntryDAO {

    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    public void saveEntry(User user, String action, String status) {
        Entry entry = new Entry();
        entry.setUserName(user.getUsername());

        StringBuffer authorities = new StringBuffer();
        for (GrantedAuthority authority : user.getAuthorities()) {
            authorities.append(authority.getAuthority() + ", ");
        }
        entry.setAuthorities(authorities.toString());

        entry.setAction(action);
        entry.setStatus(status);

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Minsk")); //must be called once??
        entry.setDate(Calendar.getInstance().getTime());
        hibernateTemplate.saveOrUpdate(entry);
    }

    @Override
    public List<Entry> listEntry() {
        return hibernateTemplate.find("from Entry");
    }
}
