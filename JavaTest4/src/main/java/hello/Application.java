package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;


@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;
    //jdbcTemplate.update适合于insert 、update和delete操作；
    @Override
    public void run(String... strings) throws Exception {
        log.info("Creating tables");
        //如果数据库中存在address_book表，就把它从数据库中drop掉
        jdbcTemplate.execute("DROP TABLE customers IF EXISTS ");
        jdbcTemplate.execute("CREATE TABLE customers(" + "id SERIAL, first_name VARCHAR(255),last_name VARCHAR (255)) ");
        List<Object[]> splitUpName = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());
        splitUpName.forEach(name -> log.info(String.format("Inserting customers record for %s %s",name[0],name[1])));


        //批量操作    适合于增、删、改操作
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name,last_name) VALUES (?,?)",splitUpName);

        log.info("Querying for customers records where first_name = 'Josh':");
        jdbcTemplate.query("SELECT id,first_name,last_name FROM customers WHERE first_name = ?",new Object[]{"Josh"},
                (rs,rowNum) -> new Customer(rs.getLong("id"),rs.getString("first_name"),rs.getString("last_name")))
                .forEach(customer -> log.info(customer.toString()));

        String name = (String) jdbcTemplate.queryForObject("SELECT last_name FROM customers WHERE id = ?", new Object[] {1},java.lang.String.class);
        log.info(String.format("this is test name = %s",name));

        List rows = jdbcTemplate.queryForList("SELECT * FROM customers");
        Iterator it = rows.iterator();

        log.info(String.format("this is test all"));
        while(it.hasNext()) {
            Map userMap = (Map) it.next();
            System.out.print(userMap.get("id") + "\t");
            System.out.print(userMap.get("first_name") + "\t");
            System.out.print(userMap.get("last_name") + "\t");
        }

        jdbcTemplate.update("UPDATE  customers SET first_name = ? WHERE id = ?", new Object[] {"hoggen", 1});
        jdbcTemplate.query("SELECT * FROM customers",
                (rs,rowNum) -> new Customer(rs.getLong("id"),rs.getString("first_name"),rs.getString("last_name")))
                .forEach(customer -> log.info(customer.toString()));
        log.info(String.format("=================="));
        jdbcTemplate.execute("DROP TABLE customers IF EXISTS ");


        //   JdbcTemplate主要提供以下五类方法：
//        execute方法：可以用于执行任何SQL语句，一般用于执行DDL语句；
//        update方法及batchUpdate方法：update方法用于执行新增、修改、删除等语句；batchUpdate方法用于执行批处理相关语句；
//        query方法及queryForXXX方法：用于执行查询相关语句；
//        call方法：用于执行存储过程、函数相关语句。

        //使用JdbcTemplate的execute()方法执行SQL语句
        //jdbcTemplate.execute("CREATE TABLE USER (user_id integer, name varchar(100))");

        //如果是UPDATE或INSERT,可以用update()方法。
       // jdbcTemplate.update("INSERT INTO USER VALUES('"
        //        + user.getId() + "', '"
        //        + user.getName() + "', '"
        //        + user.getSex() + "', '"
        //        + user.getAge() + "')");

        //带参数的更新
        //jdbcTemplate.update("UPDATE USER SET name = ? WHERE user_id = ?", new Object[] {name, id});
        //jdbcTemplate.update("INSERT INTO USER VALUES(?, ?, ?, ?)", new Object[] {user.getId(), user.getName(), user.getSex(), user.getAge()});


        //使用JdbcTemplate进行查询时，使用queryForXXX()等方法
        //int count = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM USER");//这个没实现
        //String name = (String) jdbcTemplate.queryForObject("SELECT name FROM USER WHERE user_id = ?", new Object[] {id}, java.lang.String.class);

        //
//        List rows = jdbcTemplate.queryForList("SELECT * FROM USER");
//        Iterator it = rows.iterator();
//        while(it.hasNext()) {
//            Map userMap = (Map) it.next();
//            System.out.print(userMap.get("user_id") + "\t");
//            System.out.print(userMap.get("name") + "\t");
//            System.out.print(userMap.get("sex") + "\t");
//            System.out.println(userMap.get("age") + "\t");
//        }
    }




}

























