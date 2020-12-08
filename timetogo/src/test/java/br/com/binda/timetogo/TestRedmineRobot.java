package br.com.binda.timetogo;

import org.junit.Before;
import org.junit.Test;

import br.com.binda.timetogo.properties.TimeToGoProperties;
import br.com.binda.timetogo.redmine.RedmineRobot;

public class TestRedmineRobot {

	@Before
	public void init() {

		try {
			TimeToGoProperties.loadConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void getCsrfTokenTest() {

		String csrfToken;
		try {
			csrfToken = RedmineRobot.getCsrfToken();
			System.out.print(csrfToken);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void doLoginTest() {
		String csrfToken;
		try {
			csrfToken = RedmineRobot.getCsrfToken();
			RedmineRobot.doLogin(csrfToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
