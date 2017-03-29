package by.epam.tr.lesson20.tag;

public enum ProductTagName {
	PRODUCTS,
	PRODUCT,
	NAME,
	PRICE_PER_DAY,
	AMOUNT,
	RENT,
	DATE_FROM,
	DATE_TO;
	
	public static ProductTagName getElementTagName(String element) {		
		switch (element) {
		case "name":
			return NAME;
		case "price_per_day":
			return PRICE_PER_DAY;
		case "amount":
			return AMOUNT;
		case "rent":
			return RENT;
		case "date_from":
			return DATE_FROM;
		case "date_to":
			return DATE_TO;
		case "product":
			return PRODUCT;
		case "products":
			return PRODUCTS;
		default:
			throw new EnumConstantNotPresentException(ProductTagName.class, element);
		}
	}	
}
