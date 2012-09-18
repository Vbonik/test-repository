package com.issoft.database.log.entry;

/**
 * @author: AS
 */
public interface LogEntryDAO {

    void saveEntry(String userName, String authorities, String action, String status);

}

