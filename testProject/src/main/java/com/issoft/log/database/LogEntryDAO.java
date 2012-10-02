package com.issoft.log.database;

import com.issoft.entity.User;
import com.issoft.log.database.entity.LogEntry;

import java.util.List;

/**
 * @author: AS
 */
public interface LogEntryDAO {

    void saveEntry(String userName, String authorities, String action, String status);

    List<LogEntry> list();
}

