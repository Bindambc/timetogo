package br.com.binda.timetogo.redmine;

import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;

public class RedmineRobot {
	private static String API_KEY = "";
	private static String REDMINE_URL = "";
	private static RedmineManager redmineManager = RedmineManagerFactory.createWithApiKey(REDMINE_URL, API_KEY);
}
