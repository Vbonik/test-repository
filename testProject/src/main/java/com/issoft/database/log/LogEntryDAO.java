package com.issoft.database.log;

/**
 * @author: AS
 */
public interface LogEntryDAO {

    void saveEntry(String userName, String authorities, String action, String status);

}

