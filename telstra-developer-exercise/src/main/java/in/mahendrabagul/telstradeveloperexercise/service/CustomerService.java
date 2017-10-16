package in.mahendrabagul.telstradeveloperexercise.service;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	/**
	 * We can user Eureka server for service discovery and then use Hystrix Circuit
	 * Breaker for Fallback method(avoid cascading failure) Netflix Feign Client
	 * will reduce the complexity and will help to use load balancer as well
	 */
	@Value("${customer.service.url}")
	private String URL;

	public String getCustomer(String userId) {
		/**
		 * This is for Infosys Network
		 */
	/*	SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("indpunsbd4intpxy01.ad.infosys.com", 80));
		clientHttpRequestFactory.setProxy(proxy);
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		ResponseEntity<String> forEntity = restTemplate.getForEntity(URL + "/" + userId, String.class);
		LOGGER.debug(forEntity.getBody());
		return forEntity.getBody();*/
		 String customerData = new RestTemplate().getForEntity(URL + "/" + userId,
		 String.class).getBody();
		 LOGGER.debug(customerData);
		 return customerData;
	}
}
