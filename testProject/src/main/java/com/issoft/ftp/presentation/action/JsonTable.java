package com.issoft.ftp.presentation.action;

import com.issoft.log.database.LogEntryDAO;
import com.issoft.log.database.entity.LogEntry;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class JsonTable extends ActionSupport {

    //Your result List
    private List<LogEntry> gridModel;

    //get how many rows we want to have into the grid - rowNum attribute in the grid
    private Integer rows = 0;

    //Get the requested page. By default grid sets this to 1.
    private Integer page = 0;

    // sorting order - asc or desc
    private String sord;

    // get index row - i.e. user click to sort.
    private String sidx;

    // Search Field
    private String searchField;

    // The Search String
    private String searchString;

    // The Search Operation ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
    private String searchOper;

    // Your Total Pages
    private Integer total = 0;

    // All Record
    private Integer records = 0;

    LogEntryDAO logEntryDAO;

    public String execute() {

        int to = (rows * page);
        int from = to - rows;

        //Count Rows (select count(*) from custumer)
        records = logEntryDAO.list().size();

        //Your logic to search and select the required data.
        gridModel = logEntryDAO.list().subList(from, to);

        //calculate the total pages for the query
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
