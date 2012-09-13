package com.issoft.database.log;

import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author: AS
 */
public interface LogEntryDAO {

    public void saveEntry(User user, String action, String status);

    public List<LogEntry> listEntry();
}

