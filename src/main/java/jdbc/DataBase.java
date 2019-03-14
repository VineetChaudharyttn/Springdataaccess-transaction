package jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;


@Component
public class DataBase {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BasicDataSource basicDataSource;

    @Autowired
    private SingleConnectionDataSource singleConnectionDataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private void useConnection(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") + resultSet.getString("age") + resultSet.getString("dob"));
        }
        connection.close();
    }
/*Question3*/
    public void useDataSource() throws Exception{

        Connection connection = dataSource.getConnection();
        useConnection(connection);
    }

    public void useBasicDataSource() throws Exception{
        Connection connection=basicDataSource.getConnection();
        useConnection(connection);
    }


/*Question4*/
    public void useSingleConnectionDataSource() throws Exception{
        Connection connection=singleConnectionDataSource.getConnection();
        useConnection(connection);
    }

/*Question5*/
    public void useJdbcTemplate() throws Exception{
        String quary="select count(*) from User";
        System.out.println(jdbcTemplate.queryForObject(quary,Integer.class));
    }

/*Question6*/
    public void findNameByUsername(){
        String uname="Dhanendra";
        String quary="select name from User where username=?;";
        System.out.println(jdbcTemplate.queryForObject(quary,new Object[]{uname},String.class));
    }


/*Question7*/
    public void insertByJdbcTemplete(){
        String quary="insert into User values('vineet.chaudhary@tothenew.com','Chaudhary','Vineet Chaudhary',22,'1996-12-12')";
        jdbcTemplate.update(quary);
    }


/*Question8*/
    public void quaryForMap(){
        String quary="select * from User where username= ?";
        System.out.println(jdbcTemplate.queryForMap(quary,new Object[]{"vineet.chaudhary@tothenew.com"}));
    }


/*Question9*/
    public void quaryForList() {
        String quary="select * from User";
        System.out.println(jdbcTemplate.queryForList(quary));
    }


/*Question10*/
    public void rowMapper(){
        String quary="select * from User where username=?";
        User user=jdbcTemplate.queryForObject(quary,new Object[]{"vineet.chaudhary@tothenew.com"},new UserMaapper());
        System.out.println(user);
    }


/*Question11*/
    @Autowired
    SessionFactory sessionFactoryBean;
    public void countByHibernate(){
        String quary="select count(*) from User";
        Query query1 = sessionFactoryBean.openSession().createQuery(quary);
        System.out.println(query1.uniqueResult());
    }


/*Question12*/
    @Autowired
    UserInsert userInsert;
    @Transactional(propagation= Propagation.REQUIRED)
    public void insertWithRequired()  {

        String sql="insert into User values(?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{"ak","ak","AK",24,new Date(1996-12-12)});
        try {
            userInsert.insertRequired();
        } catch (Exception e) {

        }

    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void insertWithRequiredNew() {

        String sql = "insert into User values(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{"ak", "ak", "AK", 24, new Date(1996 - 12 - 12)});
        try {
            userInsert.insertRequiredNew();
        } catch (Exception e) {

        }
    }

    @Transactional(propagation= Propagation.MANDATORY)
    public void insertWithMandatory() {

        String sql = "insert into User values(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{"ak", "ak", "AK", 24, new Date(1996 - 12 - 12)});
        try {
            userInsert.insertMandatory();
        } catch (Exception e) {

        }
    }


    @Transactional(propagation= Propagation.NEVER)
    public void insertWithNever() {

        String sql = "insert into User values(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{"ak", "ak", "AK", 24, new Date(1996 - 12 - 12)});
        try {
            userInsert.insertNever();
        } catch (Exception e) {

        }
    }


    @Transactional(propagation= Propagation.REQUIRED)
    public void insertWithNotSupport() {

        String sql = "insert into User values(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{"ak", "ak", "AK", 24, new Date(1996 - 12 - 12)});
        try {
            userInsert.insertNotSupport();
        } catch (Exception e) {

        }
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void insertWithSupport() {

        String sql = "insert into User values(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{"ak", "ak", "AK", 24, new Date(1996 - 12 - 12)});
        try {
            userInsert.insertSupport();
        } catch (Exception e) {

        }
    }




/*Question13*/
    @Transactional(readOnly = true)
    public void insertWithReadOnly()  {

        String sql="insert into User values(?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{"ak","ak","AK",24,new Date(1996-12-12)});
        try {
            userInsert.insertRequired();
        } catch (Exception e) {
        }
    }

    @Transactional(timeout= 2)
    public void insertWithTimeOut()  {
        try {
            Thread.sleep(3000);
        String sql="insert into User values(?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{"ak","ak","AK",24,new Date(1996-12-12)});

            userInsert.insertRequired();
        } catch (Exception e) {

        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void insertWithRollBack()  {
        try {
            String sql="insert into User values(?,?,?,?,?)";
            jdbcTemplate.update(sql,new Object[]{"ak","ak","AK",24,new Date(1996-12-12)});

            userInsert.insertRequired();
        } catch (Exception e) {

        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertWithNoRollBack()  {
        try {
            String sql="insert into User values(?,?,?,?,?)";
            jdbcTemplate.update(sql,new Object[]{"ak","ak","AK",24,new Date(1996-12-12)});

            userInsert.insertNoRollBack();
        } catch (Exception e) {

        }
    }
}
