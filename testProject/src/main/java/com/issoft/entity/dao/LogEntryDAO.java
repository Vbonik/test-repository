package com.issoft.entity.dao;

import com.issoft.entity.LogEntry;

import java.util.List;

/**
 * @author: AS
 */
public interface LogEntryDAO {

    void saveEntry(String userName, String authorities, String action, String status);

    List<LogEntry> list();

    List<LogEntry> search(int from, int to, String sortOrder, String sortColumn,
                          String searchOperation, String searchColumn, String searchString);

    int count(String searchOper, String searchField, String searchString);
}

