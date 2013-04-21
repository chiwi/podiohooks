package org.pkt;

import java.util.Properties;

import com.podio.APIFactory;
import com.podio.ResourceFactory;
import com.podio.app.AppAPI;
import com.podio.app.Application;
import com.podio.contact.Profile;
import com.podio.oauth.OAuthAppCredentials;
import com.podio.oauth.OAuthClientCredentials;
import com.podio.user.UserAPI;

public class Podio {

	private Properties properties;
	private APIFactory apiFactory;

	public Podio(Properties props) {
		this.properties = props;
		init();
	}

	private void init() {
		String clientId = properties.getProperty("clientId");
		String clientSecret = properties.getProperty("clientSecret");
		String appId = properties.getProperty("appId");
		String appToken = properties.getProperty("appToken");
		System.out.println(String.format("clientId:%s, clientSecret:%s, appId:%s, appToken:%s", clientId, clientSecret, appId, appToken ));
		ResourceFactory resourceFactory = new ResourceFactory(
		        new OAuthClientCredentials(clientId, clientSecret),
		        new OAuthAppCredentials(Integer.parseInt(appId), appToken));
		apiFactory = new APIFactory(resourceFactory);
		
		AppAPI appApi = apiFactory.getAPI(AppAPI.class);
		Application app = appApi.getApp(Integer.parseInt(appId));
		System.out.println(app.getConfiguration().getName());
	}

}
