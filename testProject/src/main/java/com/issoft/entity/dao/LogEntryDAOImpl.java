package com.issoft.entity.dao;

import com.issoft.entity.LogEntry;
import com.issoft.ftp.util.SearchOperation;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * @author: AS
 */

public class LogEntryDAOImpl implements LogEntryDAO {

    private static final Logger logger = Logger.getLogger(LogEntryDAOImpl.class);

    private HibernateTemplate hibernateTemplate;
    LogEntry logEntry;
    DetachedCriteria criteria;

    /**
     * Creates and saves log entry into database.
     *
     * @param userName    user who did certain action
     * @param authorities user's authorities
     * @param action      certain action made by user
     * @param status      status of action (success, failed)
     */
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

    /**
     * Get all log entries from database.
     *
     * @return List<LogEntry>
     */
    @Override
    public List<LogEntry> list() {
        criteria = DetachedCriteria.forClass(LogEntry.class);
        List result = hibernateTemplate.findByCriteria(criteria);
        return result;
    }

    /**
     * Searches suitable entries, sorts them in <code>sortOrder</code> by field <code>sortColumn</code> and
     * gets <code>amount</code> log entries starting at the <code>from<code/> entry.
     *
     * @param from            first entry to get
     * @param amount          number of entries to get from first entry
     * @param sortOrder       sorting order
     * @param sortColumn      sorting field
     * @param searchOperation search operation see {@link SearchOperation}
     * @param searchField     search field
     * @param searchString    search argument
     * @return List<LogEntry>
     */
    @Override
    public List<LogEntry> search(int from, int amount, String sortOrder, String sortColumn,
                                 String searchOperation, String searchField, String searchString) {
        criteria = DetachedCriteria.forClass(LogEntry.class);
        addOrderToCriteria(sortOrder, sortColumn);
        addSearchParametersToCriteria(searchOperation, searchField, searchString);
        List result = hibernateTemplate.findByCriteria(criteria, from, amount);
        return result;
    }

    /**
     * Counts all suitable entries in database.
     *
     * @param searchOperation search operation see {@link SearchOperation}
     * @param searchField     search field
     * @param searchString    search argument
     * @return int
     */
    @Override
    public int count(String searchOperation, String searchField, String searchString) {
        criteria = DetachedCriteria.forClass(LogEntry.class);
        addSearchParametersToCriteria(searchOperation, searchField, searchString);
        criteria.setProjection(Projections.rowCount());
        List result = hibernateTemplate.findByCriteria(criteria);
        return ((Long) result.get(0)).intValue();
    }

    /**
     * Adds parameters for search to criteria.
     *
     * @param searchOperation search operation see {@link SearchOperation}
     * @param searchField     search field
     * @param searchString    search argument
     */
    private void addSearchParametersToCriteria(String searchOperation, String searchField, String searchString) {
        try {
            if (searchOperation == null) return;
            SearchOperation searchOperationEnum = SearchOperation.getEnumByString(searchOperation);
            if (searchOperationEnum == null) return;
            Object searchValue;
            if (searchField.equals("date")) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date date = formatter.parse(searchString);
                searchValue = date;
            } else {
                searchValue = searchString;
            }
            switch (searchOperationEnum) {
                case GREATER:
                    criteria.add(Restrictions.gt(searchField, searchValue));
                    break;
                case LITTLE:
                    criteria.add(Restrictions.lt(searchField, searchValue));
                    break;
                case CONTAIN:
                    criteria.add(Restrictions.like(searchField, searchString, MatchMode.ANYWHERE));
                    break;
                case EQUAL:
                    if (searchField.equals("date")) {
                        Date dateMin = (Date) searchValue;
                        Date dateMax = new Date(dateMin.getTime() + TimeUnit.DAYS.toMillis(1));
                        Conjunction conjunction = Restrictions.conjunction();
                        conjunction.add(Restrictions.ge(searchField, dateMin));
                        conjunction.add(Restrictions.lt(searchField, dateMax));
                        criteria.add(conjunction);
                        break;
                    }
                    criteria.add(Restrictions.eq(searchField, searchValue));
                    break;
            }
        } catch (ParseException e) {
            logger.error("Date parsing error", e);
        }

    }

    /**
     * Adds order rule to criteria.
     *
     * @param sortOrder  sorting order
     * @param sortColumn sorting field
     */
    private void addOrderToCriteria(String sortOrder, String sortColumn) {
        if (!sortColumn.equals("")) {
            criteria.addOrder(sortOrder.equals("asc") ? Order.asc(sortColumn) : Order.desc(sortColumn));
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
        hibernateTemplate.setCacheQueries(true);
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

}
