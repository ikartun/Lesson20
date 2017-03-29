package by.epam.tr.lesson20.bean;

import java.util.Set;

public class Product {
	private long productId;
	private String name;
	private int prisePerDay;
	private int amount;
	private Set<Rent> rents;
	
	public Product(long productId, String name, int prisePerDay, int amount) {
		this.productId = productId;
		this.name = name;
		this.prisePerDay = prisePerDay;
		this.amount = amount;
	}
	
	public Product() {
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrisePerDay() {
		return prisePerDay;
	}

	public void setPrisePerDay(int prisePerDay) {
		this.prisePerDay = prisePerDay;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Set<Rent> getRents() {
		return rents;
	}

	public void setRents(Set<Rent> rents) {
		this.rents = rents;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", prisePerDay=" + prisePerDay + ", amount="
				+ amount + "]";
	}	
}
