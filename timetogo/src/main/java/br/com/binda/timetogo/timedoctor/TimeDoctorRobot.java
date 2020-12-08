package br.com.binda.timetogo.timedoctor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import br.com.binda.timetogo.properties.TimeToGoProperties;

public class TimeDoctorRobot {
	private final static CookieStore COOKIE_STORE = new BasicCookieStore();
	private final static String START_PAGE_URL = TimeToGoProperties.timeDoctorUrl + "/login";
	private final static String LOGIN_URL = TimeToGoProperties.timeDoctorUrl + "/login_check";
	private final static String CSV_TIME_USE_REPORT_URL = TimeToGoProperties.timeDoctorUrl + "/v2/content/reports/timeUse/getTimeUseReportData.php";

	private static CloseableHttpResponse httpResponse;

	public static String getStartPage() {

		HttpGet httpGet = new HttpGet(START_PAGE_URL);

		httpGet.addHeader("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");

		try (CloseableHttpClient httpClient = getHttpClient(COOKIE_STORE)) {

			httpResponse = httpClient.execute(httpGet);

			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				System.out.println("Unable to acces site.");

			return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String doLogin(String csrfToken) throws Exception {

		HttpPost httpPost = new HttpPost(LOGIN_URL);

		httpPost.addHeader("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
		httpPost.addHeader("origin", TimeToGoProperties.timeDoctorUrl);
		httpPost.addHeader("referer", LOGIN_URL);
		httpPost.addHeader("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");

		try (CloseableHttpClient httpClient = getHttpClient(COOKIE_STORE)) {

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("user_locale", TimeToGoProperties.timeDoctorUserLocale));
			params.add(new BasicNameValuePair("_csrf_token", csrfToken));
			params.add(new BasicNameValuePair("_username", TimeToGoProperties.timeDoctorUser));
			params.add(new BasicNameValuePair("_password", TimeToGoProperties.timeDoctorPassword));
			params.add(new BasicNameValuePair("_remember_me", "0"));
			params.add(new BasicNameValuePair("submit", ""));

			httpPost.setEntity(new UrlEncodedFormEntity(params));

			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				throw new Exception("Unable to acces site.");

			// return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			Optional<Cookie> cookie = COOKIE_STORE.getCookies().stream().filter(a -> a.getName().equals("UserID"))
					.findFirst();
			if (cookie.isPresent())
				return cookie.get().getValue();
			else
				throw new Exception("Unable to get UserId - Cookie not found.");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		}

	}

	public static String getCSVTimeUseReportData(String userId, Date fromDate, Date toDate) throws Exception {

		HttpPost httpPost = new HttpPost(CSV_TIME_USE_REPORT_URL);

		httpPost.addHeader("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
		httpPost.addHeader("origin", TimeToGoProperties.timeDoctorUrl);
		httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
		httpPost.addHeader("referer", CSV_TIME_USE_REPORT_URL);
		httpPost.addHeader("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");

		try (CloseableHttpClient httpClient = getHttpClient(COOKIE_STORE)) {

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("userId", userId));
			params.add(new BasicNameValuePair("exportType", "csv"));
			params.add(new BasicNameValuePair("fromDate", new SimpleDateFormat("yyyy-MM-dd").format(fromDate)));
			params.add(new BasicNameValuePair("toDate", new SimpleDateFormat("yyyy-MM-dd").format(toDate)));
			params.add(new BasicNameValuePair("action", "export_consolidated"));
			params.add(new BasicNameValuePair("reportType", "daily"));
			params.add(new BasicNameValuePair("loggedUserType", "user"));
			params.add(new BasicNameValuePair("user_locale", TimeToGoProperties.timeDoctorUserLocale));

			httpPost.setEntity(new UrlEncodedFormEntity(params));

			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				System.out.println("Unable to acces site.");

			return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

		} catch (Exception e) {

			throw e;
		}

	}

	private static CloseableHttpClient getHttpClient(CookieStore cookieStore) {
		return HttpClients.custom() //
				.setRedirectStrategy(new LaxRedirectStrategy()) //
				.setDefaultCookieStore(cookieStore) //
				.build();
	}

}
