package com.issoft.ftp.util;

/**
 * Search operations for <code>jQuery Grid</code>
 *
 * @author AS
 */
public enum SearchOperation {

    EQUAL("eq"),
    LITTLE("lt"),
    GREATER("gt"),
    CONTAIN("cn");

    private String code;

    private SearchOperation(String c) {
        code = c;
    }

    public static SearchOperation getEnumByString(String text) {
        if (text != null) {
            for (SearchOperation operation : SearchOperation.values()) {
                if (text.equalsIgnoreCase(operation.code)) {
                    return operation;
                }
            }
        }
        return null;
    }

}