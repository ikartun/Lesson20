package by.epam.tr.lesson20.bean;

import java.sql.Date;

public class Rent {
	private long rentId;
	private Date dateFrom;
	private Date dateTo;
	private Product product;
	
	public Rent(long rentId, Date dateFrom, Date dateTo, Product product) {
		this.rentId = rentId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.product = product;
	}
	
	public Rent() {		
	}

	public long getRentId() {
		return rentId;
	}

	public void setRentId(long rentId) {
		this.rentId = rentId;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Rent [rentId=" + rentId + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", product=" + product
				+ "]";
	}
}
