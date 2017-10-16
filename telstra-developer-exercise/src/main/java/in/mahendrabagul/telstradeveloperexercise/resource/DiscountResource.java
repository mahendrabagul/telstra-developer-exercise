package in.mahendrabagul.telstradeveloperexercise.resource;

/**
 * @author mahendra.bagul
 */
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.mahendrabagul.telstradeveloperexercise.bo.DiscountBo;
import in.mahendrabagul.telstradeveloperexercise.service.DiscountService;

@RestController
@RequestMapping("/rest/v1")
public class DiscountResource {

	@Autowired
	DiscountService discountService;

	@GetMapping("/users/{userId}/discounts")
	public List<DiscountBo> getDiscounts(@PathVariable String userId,
			@RequestParam(value = "productId", required = false) String productId) throws JSONException {
		return discountService.getDiscountsByUserIdNProductId(userId, productId);
	}
}
