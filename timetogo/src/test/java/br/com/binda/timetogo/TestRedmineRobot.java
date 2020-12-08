package br.com.binda.timetogo;

import org.junit.Test;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.TimeEntryManager;
import com.taskadapter.redmineapi.bean.TimeEntry;
import com.taskadapter.redmineapi.bean.TimeEntryFactory;
import com.taskadapter.redmineapi.internal.Transport;

public class TestRedmineRobot {

	@Test
	public void redmineTest() {
		String API_KEY = "";
		String REDMINE_URL = "";
		RedmineManager redmineManager = RedmineManagerFactory.createWithApiKey(REDMINE_URL, API_KEY);
		TimeEntryManager timeEntryManager = redmineManager.getTimeEntryManager();
		Transport transport = redmineManager.getTransport();
		TimeEntry timeEntry = TimeEntryFactory.create();

		timeEntry.setComment("test");
		timeEntry.setHours(1f);

		try {
			System.err.println(redmineManager.getIssueManager().getIssueById(47495).getSubject());
			System.err.println(redmineManager.getIssueManager().getIssueById(47495).getSpentHours());

		} catch (RedmineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
