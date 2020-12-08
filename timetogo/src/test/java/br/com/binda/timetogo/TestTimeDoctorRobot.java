package br.com.binda.timetogo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import br.com.binda.timetogo.properties.TimeToGoProperties;
import br.com.binda.timetogo.timedoctor.TimeDoctorRobot;

/**
 * Unit test for simple App.
 */
public class TestTimeDoctorRobot {

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
	public void TestGetStartPageTest() {

		String startPage = TimeDoctorRobot.getCsrfToken();

		Document html = Jsoup.parse(startPage);

		System.out.println(html.getElementsByAttributeValue("name", "_csrf_token").attr("value"));

	}

	@Test
	public void doLoginTest() {

		String startPage = TimeDoctorRobot.getCsrfToken();

		Document html = Jsoup.parse(startPage);

		String userId = null;
		try {
			userId = TimeDoctorRobot.doLogin(html.getElementsByAttributeValue("name", "_csrf_token").attr("value"));
			System.err.println(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void getCSVTimeUseReportDataTest() {

		String startPage = TimeDoctorRobot.getCsrfToken();

		Document html = Jsoup.parse(startPage);
		String userId = null;
		try {
			userId = TimeDoctorRobot.doLogin(html.getElementsByAttributeValue("name", "_csrf_token").attr("value"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String csv = null;
		try {
			csv = TimeDoctorRobot.getCSVTimeUseReportData(userId,
					Date.valueOf(LocalDate.now(ZoneId.of("America/Sao_Paulo")).minusDays(1)),
					Date.valueOf(LocalDate.now(ZoneId.of("America/Sao_Paulo")).minusDays(1)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println(csv);

	}

}
