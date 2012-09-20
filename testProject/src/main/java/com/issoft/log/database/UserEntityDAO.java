package com.issoft.log.database;

import java.util.List;

/**
 * @author: AS
 */
public interface UserEntityDAO {
    List<Object[]> selectReceivers(String action);
}
