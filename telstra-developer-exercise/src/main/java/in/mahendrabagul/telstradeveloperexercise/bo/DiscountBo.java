package in.mahendrabagul.telstradeveloperexercise.bo;

/**
 * @author mahendra.bagul
 */
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class DiscountBo {
	@NotNull
	private BigDecimal amount;
	@NotNull
	private String description;
	@NotNull
	private String discountId;
	@NotNull
	private String percent;
	@NotNull
	private String type;
	private String productId;

	public DiscountBo(BigDecimal amount, String description, String discountId, String percent, String productId,
			String type) {
		super();
		this.amount = amount;
		this.description = description;
		this.discountId = discountId;
		this.percent = percent;
		this.productId = productId;
		this.type = type;
	}

	public DiscountBo() {
		super();
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	public String getDiscountId() {
		return discountId;
	}

	public String getPercent() {
		return percent;
	}

	public String getProductId() {
		return productId;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + ((discountId == null) ? 0 : discountId.hashCode());
	 * return result; }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null) return false; if (getClass() != obj.getClass()) return
	 * false; DiscountBo other = (DiscountBo) obj; if (discountId == null) { if
	 * (other.discountId != null) return false; } else if
	 * (!discountId.equals(other.discountId)) return false; return true; }
	 */
}
