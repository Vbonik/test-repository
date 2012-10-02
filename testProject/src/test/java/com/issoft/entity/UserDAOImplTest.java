package com.issoft.entity;

import com.issoft.util.CastorMockUtil;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.List;

/*
* Tests for {@link com.issoft.entity.UserDAOImpl} class
* */
public class UserDAOImplTest extends AbstractTransactionalDataSourceSpringContextTests {
    /**
     * Path to test context file
     */
    private static final String TEST_CONTEXT_FILE_PATH = "testContext.xml";
    /**
     * Path to dbunit data file
     */
    private static final String DBUNIT_DATA_FILE_PATH = "src/test/resources/dbunit/usersDAOImpl.xml";
    /**
     * {@link UsersDAO} entity to test
     */
    private UsersDAO usersDAO;

    /**
     * Constructor for test class. Initialization of usersDAO entity
     */
    public UserDAOImplTest() {
        super();
        ApplicationContext ctx = super.getApplicationContext();
        usersDAO = (UsersDAO) ctx.getBean("dao");
        assertNotNull(usersDAO);
    }

    /**
     * Gets configuration locations paths
     * @return Configuration locations paths
     */
    @Override
    protected String[] getConfigLocations() {
        return new String[]{
                TEST_CONTEXT_FILE_PATH
        };
    }

    /**
     * Calls before each test method.
     * Creates connection and connects to test database, puts test data from xml on each transaction.     *
     * @throws Exception In case of error
     */
    @Override
    protected void onSetUpInTransaction() throws Exception {
        DataSource dataSource = jdbcTemplate.getDataSource();
        Connection con = DataSourceUtils.getConnection(dataSource);
        IDatabaseConnection dbUnitCon = new DatabaseConnection(con);
        IDataSet dataSet = new FlatXmlDataSet(new FileInputStream(DBUNIT_DATA_FILE_PATH));
        try {
            DatabaseOperation.REFRESH.execute(dbUnitCon, dataSet);
        } finally {
            DataSourceUtils.releaseConnection(con, dataSource);
        }
    }

    /**
     * Test for {@link UsersDAOImpl#add(User)} method
     * @throws Exception In case of error
     */
    public void testAdd() throws Exception {
       User userMock = (User) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER);
       usersDAO.add(userMock);
       User actualResultUser = usersDAO.getUserById(userMock.getUser_id());
       assertNotNull(actualResultUser);
       assertEquals(userMock, actualResultUser);
    }

    /**
     * Test for {@link com.issoft.entity.UsersDAOImpl#list()} method
     * @throws Exception In case of error
     */
    public void testList() throws Exception {
        User userMock = (User) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER);
        List<User> usersList = usersDAO.list();
        assertNotNull(usersList);
        assertEquals(1, usersList.size());
        assertEquals(userMock, usersList.get(0));
    }

    /**
     * Test for {@link UsersDAOImpl#delete(String)} method
     * @throws Exception In case of error
     */
    public void testDelete() throws Exception {
        User userMock = (User) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER);
        usersDAO.delete(userMock.getUser_id());
        List<User> userList = usersDAO.list();
        assertNotNull(userList);
        assertEquals(0, userList.size());
    }

    /**
     * Test for {@link UsersDAOImpl#getUserById(String)} method
     * @throws Exception In case of error
     */
    public void testGetUserById() throws Exception {
        User userMock = (User) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER);
        User user = usersDAO.getUserById(userMock.getUser_id());
        assertNotNull(user);
        assertEquals(userMock, user);
    }

    /**
     * Test for {@link UsersDAOImpl#update(User)} method
     * @throws Exception In case of error
     */
    public void testUpdate() throws Exception {
        User userMock = (User) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER);
        userMock.setDownload_rate(1);
        usersDAO.update(userMock);
        User user = usersDAO.getUserById(userMock.getUser_id());
        assertNotNull(user);
        assertEquals(userMock, user);
    }

    /**
     * Test for {@link com.issoft.entity.UsersDAOImpl#getUserRoles()} method
     * @throws Exception In case of error
     */
    public void testGetUserRoles() throws Exception {
        UserRole userRoleMock = (UserRole) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER_ROLE);
        List<UserRole> roles = usersDAO.getUserRoles();
        assertNotNull(roles);
        assertEquals(userRoleMock, roles.get(0));
    }

    /**
     * Test for {@link UsersDAOImpl#getUserRoleById(long)} method
     * @throws Exception In case of error
     */
    public void testGetUserRoleById() throws Exception {
        UserRole userRoleMock = (UserRole) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER_ROLE);
        UserRole role = usersDAO.getUserRoleById(userRoleMock.getId());
        assertNotNull(role);
        assertEquals(userRoleMock, role);
    }
}
