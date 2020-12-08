package br.com.binda.timetogo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.binda.timetogo.properties.TimeToGoProperties;
import br.com.binda.timetogo.redmine.RedmineIssue;
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

	@Test
	public void getIssueByIdTest() {
		String csrfToken;
		try {
			csrfToken = RedmineRobot.getCsrfToken();
			RedmineRobot.doLogin(csrfToken);

			RedmineIssue issue = RedmineRobot.getIssueById("47304");
			Assert.assertNotNull(issue);
			System.err.println(issue.getSubject() + "\n");

			issue = RedmineRobot.getIssueById("47525");
			Assert.assertNotNull(issue);
			System.err.println(issue.getSubject() + "\n");

			issue = RedmineRobot.getIssueById("47495");
			Assert.assertNotNull(issue);
			System.err.println(issue.getSubject() + "\n");

			issue = RedmineRobot.getIssueById("47357");
			Assert.assertNotNull(issue);
			System.err.println(issue.getSubject() + "\n");

			issue = RedmineRobot.getIssueById("47124");
			Assert.assertNotNull(issue);
			System.err.println(issue.getSubject() + "\n");

			issue = RedmineRobot.getIssueById("47231");
			Assert.assertNotNull(issue);
			System.err.println(issue.getSubject() + "\n");

			issue = RedmineRobot.getIssueById("47351");
			Assert.assertNotNull(issue);
			System.err.println(issue.getSubject() + "\n");

			issue = RedmineRobot.getIssueById("47129");
			Assert.assertNotNull(issue);
			System.err.println(issue.getSubject() + "\n");

			issue = RedmineRobot.getIssueById("47088");
			Assert.assertNotNull(issue);
			System.err.println(issue.getSubject() + "\n");

			issue = RedmineRobot.getIssueById("4677");
			Assert.assertNotNull(issue);
			System.err.println(issue.getSubject() + "\n");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
