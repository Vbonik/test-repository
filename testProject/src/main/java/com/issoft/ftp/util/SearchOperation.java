package com.issoft.ftp.util;

/**
 * Search operations for <code>jQuery Grid</code>
 *
 * @author AS
 */
public enum SearchOperation {
    EQUAL("eq"),
    NOT_EQUAL("ne"),
    LITTLE("lt"),
    LITTLE_OR_EQUAL("le"),
    GREATER("gt"),
    GREATER_OR_EQUAL("ge"),
    BEGINS_WITH("bw"),
    NOT_BEGINS_WITH("bn"),
    ENDS_WITH("ew"),
    NOT_ENDS_WITH("en"),
    CONTAIN("cn"),
    NOT_CONTAIN("nc");

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