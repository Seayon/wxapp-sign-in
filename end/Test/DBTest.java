import com.cl.ytsignin.dao.po.Depart;
import com.cl.ytsignin.service.DepartService;
import com.cl.ytsignin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/18
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class DBTest {
	@Autowired
	DepartService departService;
	@Autowired
	UserService userService;
	@Test
	public void main() {
		userService.bindUser("B16043124", "赵旭阳", "2222");

	}
	@Test
	public void unBind() {
		userService.unBind("B16043124", "222");
	}
}
