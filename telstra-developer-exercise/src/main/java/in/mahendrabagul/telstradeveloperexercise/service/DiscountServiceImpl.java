package in.mahendrabagul.telstradeveloperexercise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import in.mahendrabagul.telstradeveloperexercise.bo.DiscountBo;

@Service
public class DiscountServiceImpl implements DiscountService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerService customerService;

	@Override
	public List<DiscountBo> getDiscountsByUserIdNProductId(String userId, String productId) throws JSONException {
		if (Objects.nonNull(userId)) {
			String response = customerService.getCustomer(userId);
			List<DiscountBo> discountBos = new ArrayList<>();
			String eligibleDiscounts = new JSONObject(response).get("eligibleDiscounts").toString();
			if (Objects.nonNull(eligibleDiscounts)) {
				List<DiscountBo> list = new Gson().fromJson(eligibleDiscounts, new TypeToken<List<DiscountBo>>() {
				}.getType());
				if (Objects.nonNull(productId)) {
					discountBos.addAll(filterByProductId(list, productId));
				} else {
					discountBos.addAll(list);
				}
			}
			return discountBos;
		}
		return new ArrayList<>();
	}

	private List<DiscountBo> filterByProductId(List<DiscountBo> list, String productId) {
		List<DiscountBo> filteredDiscountBos = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			DiscountBo discountBo = list.get(i);
			if (productId.equals(discountBo.getProductId())) {
				filteredDiscountBos.add(discountBo);
			}
		}
		if (filteredDiscountBos.isEmpty()) {
			LOGGER.debug("No discounts found for the productId:" + productId);
		}
		return filteredDiscountBos;
	}
}
