package com.issoft.database.log;

import java.util.List;

/**
 * @author: AS
 */
public interface LogEntryDAO {

    public List<LogEntry> listEntry();

    void saveEntry(String userName, String authorities, String action, String status);
}

