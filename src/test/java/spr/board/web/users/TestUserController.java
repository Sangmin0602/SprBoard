package spr.board.web.users;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;

import spr.board.model.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
			"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}
)
public class TestUserController {

	@Autowired
	UserController userController;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void listAllUsers() {
		ExtendedModelMap model = new ExtendedModelMap();
		userController.listAllUsers(model);
		List<UserVO> users = (List<UserVO>) model.get("users");
		assertTrue ( users.size() > 0 );
	}

}
