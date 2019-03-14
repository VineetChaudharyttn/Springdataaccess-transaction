package jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
@Repository
public class UserInsert {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private void insert() {
        String sql="insert into User values(?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{"Shubham","Seharawt","Shubham Seharawt",26,new Date(1991-12-12)});
        throw new RuntimeException("Exception occered");
    }



/*Question12*/
    @Transactional(propagation= Propagation.REQUIRED)
    public void insertRequired()  {
        insert();


    }
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void insertRequiredNew()  {
        insert();
        return;


    }


    public void insertMandatory() {
        insert();
    }



    @Transactional(propagation = Propagation.NEVER)
    public void insertNever() {
        insert();
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void insertNotSupport() {
        insert();
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public void insertSupport() {
        insert();
    }


/*Question13*/
    @Transactional(noRollbackFor = RuntimeException.class)
    public void insertNoRollBack() {
        insert();
    }
}
