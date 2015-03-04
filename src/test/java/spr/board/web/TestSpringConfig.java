package spr.board.web;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spr.board.dao.IDaoRepository;

public class TestSpringConfig {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-context.xml");
		IDaoRepository repoA = ctx.getBean("daoRepo", IDaoRepository.class);
		assertNotNull(repoA);
		
		// new DaoRep(...)
		IDaoRepository repoB = ctx.getBean("daoRepo", IDaoRepository.class);
		
		assertTrue ( repoA == repoB );
		
		
	}

}
