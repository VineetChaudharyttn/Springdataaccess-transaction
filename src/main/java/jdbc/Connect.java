package jdbc;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Connect{

    public static void main(String[] args) throws Exception{

        ApplicationContext cxt = new ClassPathXmlApplicationContext("Spring-config.xml");

        DataBase dataBase = cxt.getBean(DataBase.class);

/* Question 3*/
        System.out.println("--------Question 3--------");
        dataBase.useDataSource();
        dataBase.useBasicDataSource();

/*Question4*/
        System.out.println("--------Question 4--------");
        dataBase.useSingleConnectionDataSource();

/*Question5*/
        System.out.println("--------Question 5--------");
        dataBase.useJdbcTemplate();


/*Question6*/
        System.out.println("--------Question 6--------");
        dataBase.findNameByUsername();


/*Question7*/
        //dataBase.insertByJdbcTemplete();


/*Question8*/
        System.out.println("--------Question 8--------");
        dataBase.quaryForMap();


/*Question9*/
        System.out.println("--------Question 9--------");
        dataBase.quaryForList();

/*Question10*/
        System.out.println("--------Question 10--------");
        dataBase.rowMapper();


/*Question11*/
        System.out.println("--------Question 11--------");
        dataBase.countByHibernate();


/*Question12*/
        System.out.println("--------Question 12-------");
        try {
//            dataBase.insertWithRequired();
//            dataBase.insertWithRequiredNew();
//            dataBase.insertWithMandatory();
//            dataBase.insertWithNever();
//            dataBase.insertWithNotSupport();
//            dataBase.insertWithSupport();


/*Question13*/
//            dataBase.insertWithReadOnly();
//            dataBase.insertWithTimeOut();
//            dataBase.insertWithRollBack();
            dataBase.insertWithNoRollBack();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
