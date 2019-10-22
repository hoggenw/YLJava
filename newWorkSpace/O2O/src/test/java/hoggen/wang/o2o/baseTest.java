package hoggen.wang.o2o;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @注释 配置spring和junit整合，junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件的位置
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml",
		"classpath:spring/spring-redis.xml" })
public class baseTest {
	@Test
	public void testTrue() {
		assertEquals(2, 2);
	}

}
