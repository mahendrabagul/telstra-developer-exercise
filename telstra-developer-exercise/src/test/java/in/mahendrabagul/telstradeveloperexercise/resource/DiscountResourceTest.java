package in.mahendrabagul.telstradeveloperexercise.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import in.mahendrabagul.telstradeveloperexercise.bo.DiscountBo;
import in.mahendrabagul.telstradeveloperexercise.service.DiscountService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DiscountResource.class)
public class DiscountResourceTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private DiscountService discountService;

	@Test
	public void givenDiscounts_whenGetDiscounts_thenReturnJsonArray() throws Exception {
		DiscountBo discountBo = new DiscountBo();
		discountBo.setAmount(new BigDecimal(15));
		discountBo.setDescription("Reduce the purchase price by $5");
		discountBo.setDiscountId("5-dollars-off");
		discountBo.setPercent(null);
		discountBo.setProductId("sku-1234567890");
		discountBo.setType("AMOUNT");
		given(discountService.getDiscountsByUserIdNProductId("qa-test-user", null))
				.willReturn(Arrays.asList(discountBo));
		mockMvc.perform(get("/rest/v1/users/qa-test-user/discounts").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].amount", is(15)));
	}

	@Test
	public void givenDiscounts_whenGetDiscountsByProductId_thenReturnJsonArray() throws Exception {
		DiscountBo discountBo1 = new DiscountBo(new BigDecimal(5), "Reduce the purchase price by $5", "5-dollars-off",
				null, "sku-1234567890", "AMOUNT");
		DiscountBo discountBo2 = new DiscountBo(new BigDecimal(15), "Reduce the purchase price by $15",
				"15-dollars-off", null, "sku-1234567890", "AMOUNT");
		List<DiscountBo> asList = Arrays.asList(discountBo1, discountBo2);
		given(discountService.getDiscountsByUserIdNProductId("qa-test-user", "sku-1234567890")).willReturn(asList);
		mockMvc.perform(get("/rest/v1/users/qa-test-user/discounts?productId=sku-1234567890")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].amount", is(5))).andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void givenDiscounts_whenGetDiscountsByWrongInput_thenReturnException() throws Exception {
		given(discountService.getDiscountsByUserIdNProductId("qa-test-user", "sku-1234567890"))
				.willThrow(new NullPointerException());
		mockMvc.perform(get("/rest/v1/users/qa-test-user/discounts?productId=sku-1234567890")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
	}
}
