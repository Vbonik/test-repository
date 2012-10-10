package com.issoft.ftp.presentation.action;

import com.issoft.entity.dao.LogEntryDAO;
import com.issoft.entity.LogEntry;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class JsonTable extends ActionSupport {

    //Result list
    private List<LogEntry> gridModel;

    //How many rows we want to have into the grid - rowNum attribute in the grid
    private Integer rows = 0;

    //Requested page. By default grid sets this to 1.
    private Integer page = 0;

    //Sorting order - asc or desc
    private String sord;

    //Get index row - i.e. user click to sort.
    private String sidx;

    //Search field
    private String searchField;

    //Search string
    private String searchString;

    /*Search operation ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
        eq - equal ( = )
        ne - not equal ( <> )
        lt - little ( < )
        le - little or equal ( <= )
        gt - greater ( > )
        ge - greater or equal ( >= )
        bw - begins with ( LIKE val% )
        ew - ends with (LIKE %val )
        cn - contain (LIKE %val% )
        bn - does not begins with
        in - is in
        ni - is not in
        en - does not ends with
        nc - does not contain
        */
    private String searchOper;

    //Total pages
    private Integer total = 0;

    //All records quantity
    private Integer records = 0;

    LogEntryDAO logEntryDAO;

    public String execute() {

        records = logEntryDAO.count(searchOper, searchField, searchString);

        int to;
        int from;
        if (rows * page <= records) {
            to = rows * page;
            from = to - rows;
        } else {
            to = records;
            from = records - to % rows;
        }

        //Search and select the required data.
        gridModel = logEntryDAO.search(from, rows, sord, sidx, searchOper, searchField, searchString);

        //Calculate the total pages for the query
        total = (int) Math.ceil((double) records / (double) rows);

        return SUCCESS;
    }

    public String getJSON() {
        return execute();
    }

    public List<LogEntry> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<LogEntry> gridModel) {
        this.gridModel = gridModel;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public LogEntryDAO getLogEntryDAO() {
        return logEntryDAO;
    }

    public void setLogEntryDAO(LogEntryDAO logEntryDAO) {
        this.logEntryDAO = logEntryDAO;
    }
}
