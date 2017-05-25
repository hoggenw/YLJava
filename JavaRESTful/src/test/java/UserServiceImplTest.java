

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import controller.*;


/**
 * Created by wangliugen on 2017/5/24.
 */
public class UserServiceImplTest {
        @Autowired
        private  UserServiceImpl  userService;
        @Before
        public  void setUp() {
            userService.deleteAllUsers();
        }
        @Test
        public void test() throws Exception {
            //插入5个用户
            userService.create("a",1);
            userService.create("b", 2);
            userService.create("c", 3);
            userService.create("d", 4);
            userService.create("e", 5);
            //查数据库，应该有5个用户
            Assert.assertEquals(5,userService.getAllUsers().intValue());
            userService.deleteByName("a");
            userService.deleteByName("e");
            Assert.assertEquals(3,userService.getAllUsers().intValue());
        }
}






















