package br.com.binda.timetogo.properties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class TimeToGoProperties {

	final private static String PROPERTIES_FILE_PATH = "./timetogo.properties";

	// vars timedoctor
	public static String timeDoctorUrl;
	public static String timeDoctorUser;
	public static String timeDoctorPassword;
	public static String timeDoctorUserLocale;

	// vars redmine
	public static String redmineUrl;
	public static String redmineUser;
	public static String redminePassword;

	public static void loadConfig() throws Exception {
		Properties properties = new Properties();

		try (InputStream inputStream = new FileInputStream(Paths.get(PROPERTIES_FILE_PATH).toFile())) {
			properties.load(inputStream);

			timeDoctorUrl = properties.getProperty("timeDoctorUrl");
			timeDoctorUser = properties.getProperty("timeDoctorUser");
			timeDoctorPassword = properties.getProperty("timeDoctorPassword");
			timeDoctorUserLocale = properties.getProperty("timeDoctorUserLocale");

			redmineUrl = properties.getProperty("redmineUrl");
			redmineUser = properties.getProperty("redmineUser");
			redminePassword = properties.getProperty("redminePassword");

		} catch (Exception e) {
			throw new Exception("Unable to load \"timetogo.properties\" file.\n", e);
		}
	}

}
