package com.issoft.entity;

import com.issoft.entity.dao.UserEntityDAO;
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
     * {@link com.issoft.entity.dao.UserEntityDAO} entity to test
     */
    private UserEntityDAO usersDAO;

    /**
     * Constructor for test class. Initialization of usersDAO entity
     */
    public UserDAOImplTest() {
        super();
        ApplicationContext ctx = super.getApplicationContext();
        usersDAO = (UserEntityDAO) ctx.getBean("dao");
        assertNotNull(usersDAO);
    }

    /**
     * Gets configuration locations paths
     *
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
     *
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
     * Test for {@link com.issoft.entity.dao.UserEntityDAOImpl#add(UserEntity)} method
     *
     * @throws Exception In case of error
     */
    public void testAdd() throws Exception {
        UserEntity userMock = (UserEntity) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER);
        usersDAO.add(userMock);
        UserEntity actualResultUser = usersDAO.getUserById(userMock.getUserId());
        assertNotNull(actualResultUser);
        assertEquals(userMock, actualResultUser);
    }

    /**
     * Test for {@link com.issoft.entity.dao.UserEntityDAOImpl#list()} method
     *
     * @throws Exception In case of error
     */
    public void testList() throws Exception {
        UserEntity userMock = (UserEntity) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER);
        List<UserEntity> usersList = usersDAO.list();
        assertNotNull(usersList);
        assertEquals(1, usersList.size());
        assertEquals(userMock, usersList.get(0));
    }

    /**
     * Test for {@link com.issoft.entity.dao.UserEntityDAOImpl#delete(String)} method
     *
     * @throws Exception In case of error
     */
    public void testDelete() throws Exception {
        UserEntity userMock = (UserEntity) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER);
        usersDAO.delete(userMock.getUserId());
        List<UserEntity> userList = usersDAO.list();
        assertNotNull(userList);
        assertEquals(0, userList.size());
    }

    /**
     * Test for {@link com.issoft.entity.dao.UserEntityDAOImpl#getUserById(String)} method
     *
     * @throws Exception In case of error
     */
    public void testGetUserById() throws Exception {
        UserEntity userMock = (UserEntity) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER);
        UserEntity user = usersDAO.getUserById(userMock.getUserId());
        assertNotNull(user);
        assertEquals(userMock, user);
    }

    /**
     * Test for {@link com.issoft.entity.dao.UserEntityDAOImpl#update(UserEntity)} method
     *
     * @throws Exception In case of error
     */
    public void testUpdate() throws Exception {
        UserEntity userMock = (UserEntity) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER);
        userMock.setDownloadRate(1);
        usersDAO.update(userMock);
        UserEntity user = usersDAO.getUserById(userMock.getUserId());
        assertNotNull(user);
        assertEquals(userMock, user);
    }

    /**
     * Test for {@link com.issoft.entity.dao.UserEntityDAOImpl#getUserRoles()} method
     *
     * @throws Exception In case of error
     */
    public void testGetUserRoles() throws Exception {
        UserRole userRoleMock = (UserRole) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER_ROLE);
        List<UserRole> roles = usersDAO.getUserRoles();
        assertNotNull(roles);
        assertEquals(userRoleMock, roles.get(0));
    }

    /**
     * Test for {@link com.issoft.entity.dao.UserEntityDAOImpl#getUserRoleById(long)} method
     *
     * @throws Exception In case of error
     */
    public void testGetUserRoleById() throws Exception {
        UserRole userRoleMock = (UserRole) CastorMockUtil.createMock(CastorMockUtil.MockEntity.USER_ROLE);
        UserRole role = usersDAO.getUserRoleById(userRoleMock.getId());
        assertNotNull(role);
        assertEquals(userRoleMock, role);
    }
}
