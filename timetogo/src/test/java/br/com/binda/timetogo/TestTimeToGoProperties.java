package br.com.binda.timetogo;

import org.junit.Assert;
import org.junit.Test;

import br.com.binda.timetogo.properties.TimeToGoProperties;

public class TestTimeToGoProperties {

	@Test
	public void testLoadProperties() {
		try {
			TimeToGoProperties.loadConfig();

			Assert.assertNotNull(TimeToGoProperties.redmineUser);
			Assert.assertNotNull(TimeToGoProperties.redmineUrl);
			Assert.assertNotNull(TimeToGoProperties.timeDoctorPassword);
			Assert.assertNotNull(TimeToGoProperties.timeDoctorUser);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
