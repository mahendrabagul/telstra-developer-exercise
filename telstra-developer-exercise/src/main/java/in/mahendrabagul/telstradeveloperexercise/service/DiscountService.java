package in.mahendrabagul.telstradeveloperexercise.service;

import java.util.List;

import org.json.JSONException;

import in.mahendrabagul.telstradeveloperexercise.bo.DiscountBo;

public interface DiscountService {

	List<DiscountBo> getDiscountsByUserIdNProductId(String userId, String productId) throws JSONException;
}
