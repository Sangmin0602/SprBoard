package spr.board.web.users;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import spr.board.dao.IDaoRepository;
import spr.board.model.UserVO;

/**
 * <li> annotation을 사용하지 않는 경우 직접 Controller 인터페이스를 구현해야 함.
 * <li> handleRequest 를 구현해서 ModelAndView 인스턴스를 반환해주어야 함. 
 * <li> servlet-context.xml 에서는 url handler mapping을 직접 넣어주어야 함.
 * 
 * <pre>
 * <beans:bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
		<beans:property name="mappings">
			<beans:value>
			/users=userController
			</beans:value>
		</beans:property>
	</beans:bean>
 * </pre>
 */
public class UserController implements org.springframework.web.servlet.mvc.Controller {

	private IDaoRepository repository;
	
	public void setDaoRepository(IDaoRepository repo) {
		this.repository = repo;
	}
	
	public String listAllUsers() {
		
		return "list-all-users";
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("list-all-users");
		List<UserVO> users = repository.getUserDao().findAll();
		request.setAttribute("users", users);
		return mav;
	}
}
