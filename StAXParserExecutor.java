package by.epam.tr.lesson20;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.epam.tr.lesson20.bean.Product;
import by.epam.tr.lesson20.bean.Rent;
import by.epam.tr.lesson20.tag.ProductTagName;

public class StAXParserExecutor {

	public static void main(String[] args) throws FileNotFoundException {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		
		try {
			InputStream inputStream = new FileInputStream("src/by/epam/tr/lesson20/ProductRent.xml");
			XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
			List<Product> products = process(xmlStreamReader);
			
			for (Product product : products) {
				System.out.println(product.getName());
				System.out.println("rents are: ");
				
				for (Rent rent : product.getRents()) {
					System.out.println("from " + rent.getDateFrom().toString() + " to " + ((rent.getDateTo() == null) ? "now" : rent.getDateTo().toString()));
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private static List<Product> process(XMLStreamReader xmlStreamReader) throws XMLStreamException {
		List<Product> products = new ArrayList<>();
		Set<Rent> rents = new HashSet<>();
		Product product = null;
		Rent rent = null;
		ProductTagName elementName = null;
		
		while (xmlStreamReader.hasNext()) {
			int type = xmlStreamReader.next();
			
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = ProductTagName.getElementTagName(xmlStreamReader.getLocalName());				
				switch (elementName) {
				case PRODUCT:
					product = new Product();
					int productId = Integer.parseInt(xmlStreamReader.getAttributeValue(null, "id"));
					product.setProductId(productId);
					break;
				case RENT:
					rent = new Rent();
					int rentId = Integer.parseInt(xmlStreamReader.getAttributeValue(null, "id"));
					rent.setRentId(rentId);
					rent.setProduct(product);
				default:
					break;
				}
				break;

			case XMLStreamConstants.CHARACTERS:
				String text = xmlStreamReader.getText().trim();
				if (text.isEmpty()) {
					break;
				}
				switch (elementName) {
				case NAME:
					product.setName(text);
					break;
				case PRICE_PER_DAY:
					product.setPrisePerDay(Integer.parseInt(text));
					break;
				case AMOUNT:
					product.setAmount(Integer.parseInt(text));
					break;
				case DATE_FROM:
					rent.setDateFrom(Date.valueOf(text));
					break;
				case DATE_TO:
					if (!text.toString().isEmpty()) {
						rent.setDateTo(Date.valueOf(text));
					}			
					break;
				default:
					break;
				}
				break;
				
			case XMLStreamConstants.END_ELEMENT:
				elementName = ProductTagName.getElementTagName(xmlStreamReader.getLocalName());				
				switch (elementName) {
				case PRODUCT:
					product.setRents(rents);
					products.add(product);
					rents = new HashSet<>();
					break;
				case RENT:
					rents.add(rent);
					break;
				default:
					break;
				}
			}			
		}
		return products;
	}

}
