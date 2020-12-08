package br.com.binda.timetogo.redmine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;

import br.com.binda.timetogo.properties.TimeToGoProperties;

public class RedmineRobot {
	private static String START_LOGIN_PAGE_URL = TimeToGoProperties.redmineUrl + "/login";

	private static CookieStore COOKIE_STORE = new BasicCookieStore();
	private static CloseableHttpResponse httpResponse;

	public static String getCsrfToken() throws Exception {

		HttpGet httpGet = new HttpGet(START_LOGIN_PAGE_URL);

		httpGet.addHeader("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");

		try (CloseableHttpClient httpClient = getHttpClient(COOKIE_STORE)) {

			httpResponse = httpClient.execute(httpGet);

			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				throw new Exception(
						"Unable to acces site. Status code: " + httpResponse.getStatusLine().getStatusCode());

			return Jsoup.parse(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"))
					.getElementsByAttributeValue("name", "authenticity_token").attr("value");

		} catch (Exception e) {
			throw e;
		}

	}

	public static void doLogin(String csrfToken) throws Exception {

		HttpPost httpPost = new HttpPost(START_LOGIN_PAGE_URL);

		httpPost.addHeader("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
		httpPost.addHeader("origin", TimeToGoProperties.timeDoctorUrl);
		httpPost.addHeader("referer", START_LOGIN_PAGE_URL);
		httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
		httpPost.addHeader("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");

		try (CloseableHttpClient httpClient = getHttpClient(COOKIE_STORE)) {

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("utf8", "✓"));
			params.add(new BasicNameValuePair("authenticity_token", csrfToken));
			params.add(new BasicNameValuePair("username", TimeToGoProperties.redmineUser));
			params.add(new BasicNameValuePair("password", TimeToGoProperties.redminePassword));
			params.add(new BasicNameValuePair("login", "Entrar"));

			httpPost.setEntity(new UrlEncodedFormEntity(params));

			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				throw new Exception("Unable to acces site. Status: " + httpResponse.getStatusLine().getStatusCode());

			if (Jsoup.parse(EntityUtils.toString(httpResponse.getEntity(), "UTF-8")).getElementById("loggedas") == null)
				throw new Exception("Login Error");

		} catch (IOException e) {
			// TODO Auto-generated catch block
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
