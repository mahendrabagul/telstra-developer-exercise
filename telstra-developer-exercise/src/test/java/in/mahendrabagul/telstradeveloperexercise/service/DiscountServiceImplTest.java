package in.mahendrabagul.telstradeveloperexercise.service;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.json.JSONException;

import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DiscountServiceImplTest {
	@InjectMocks
	private DiscountServiceImpl discountServiceImpl;
	@Mock
	private CustomerService customerService;

	@Test
	public void test_DiscountsWhenProductsArePresentInCustomer() throws JSONException {
		String customer = " {\r\n" + "  \"uuid\": \"Mahendra\",\r\n" + "  \"name\": \"Mahendra Bagul\",\r\n"
				+ "  \"address\": \"Pune\",\r\n" + "  \"eligibleDiscounts\": [\r\n" + "    {\r\n"
				+ "      \"discountId\": \"10-percent-off\",\r\n" + "      \"type\": \"PERCENT\",\r\n"
				+ "      \"description\": \"Reduce the purchase price by 10%\",\r\n" + "      \"amount\": 10\r\n"
				+ "    },\r\n" + "    {\r\n" + "      \"discountId\": \"5-dollars-off\",\r\n"
				+ "      \"type\": \"AMOUNT\",\r\n" + "      \"description\": \"Reduce the purchase price by $5\",\r\n"
				+ "      \"amount\": 5,\r\n" + "      \"productId\": \"sku-1234567890\"\r\n" + "    }\r\n" + "  ],\r\n"
				+ "  \"products\": [\r\n" + "    {\r\n" + "      \"productId\": \"sku-1234567890\",\r\n"
				+ "      \"description\": \"12 month subscription to 'Horse and Hound'\",\r\n"
				+ "      \"originalPrice\": 100,\r\n" + "      \"finalPrice\": 50,\r\n"
				+ "      \"discountInformation\": {\r\n" + "        \"discountId\": \"50-percent-off\",\r\n"
				+ "        \"type\": \"PERCENT\",\r\n"
				+ "        \"description\": \"Reduce the purchase price by 50%\",\r\n" + "        \"amount\": 50,\r\n"
				+ "        \"productId\": \"sku-1234567890\"\r\n" + "      }\r\n" + "    }\r\n" + "  ]\r\n" + "}";
		when(customerService.getCustomer("Mahendra")).thenReturn(customer);
		assertThat(discountServiceImpl.getDiscountsByUserIdNProductId("Mahendra", "sku-1234567890"), hasSize(1));
	}

	@Test
	public void test_DiscountsWhenProductsAreNotPresentInCustomer() throws JSONException {
		String aryan = " {\r\n" + "  \"uuid\": \"Aryan\",\r\n" + "  \"name\": \"Aryan Bagul\",\r\n"
				+ "  \"address\": \"Nashik\",\r\n" + "  \"eligibleDiscounts\": [\r\n" + "    {\r\n"
				+ "      \"discountId\": \"15-percent-off\",\r\n" + "      \"type\": \"PERCENT\",\r\n"
				+ "      \"description\": \"Reduce the purchase price by 15%\",\r\n" + "      \"amount\": 15\r\n"
				+ "    }\r\n" + "  ],\r\n" + "  \"products\": []\r\n" + "}";

		when(customerService.getCustomer("Aryan")).thenReturn(aryan);
		assertThat(discountServiceImpl.getDiscountsByUserIdNProductId("Aryan", "productId"), hasSize(0));
	}

	@Test
	public void test_DiscountsWhenCustomerIsNotPresent() throws JSONException {
		String aryan = " {\r\n" + "  \"uuid\": \"Aryan\",\r\n" + "  \"name\": \"Aryan Bagul\",\r\n"
				+ "  \"address\": \"Nashik\",\r\n" + "  \"eligibleDiscounts\": [\r\n" + "    {\r\n"
				+ "      \"discountId\": \"15-percent-off\",\r\n" + "      \"type\": \"PERCENT\",\r\n"
				+ "      \"description\": \"Reduce the purchase price by 15%\",\r\n" + "      \"amount\": 15\r\n"
				+ "    }\r\n" + "  ],\r\n" + "  \"products\": []\r\n" + "}";

		when(customerService.getCustomer("Aryan")).thenReturn(aryan);
		assertThat(discountServiceImpl.getDiscountsByUserIdNProductId(null, "productId"), hasSize(0));
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
}
