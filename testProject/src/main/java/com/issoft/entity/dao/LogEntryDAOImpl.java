package com.issoft.entity.dao;

import com.issoft.ftp.util.SearchOperation;
import com.issoft.entity.LogEntry;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
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
        if (searchOperation == null) return;
        SearchOperation searchOperationEnum = SearchOperation.getEnumByString(searchOperation);
        if (searchOperationEnum == null) return;
        switch (searchOperationEnum) {
            case CONTAIN:
                criteria.add(Restrictions.like(searchField, searchString, MatchMode.ANYWHERE));
                break;
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
}
