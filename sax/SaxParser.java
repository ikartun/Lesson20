package by.epam.tr.lesson20.sax;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.tr.lesson20.bean.Product;
import by.epam.tr.lesson20.bean.Rent;
import by.epam.tr.lesson20.tag.ProductTagName;

public class SaxParser extends DefaultHandler {
	private List<Product> products = new ArrayList<>();
	private Set<Rent> rents = new HashSet<>();
	private Product product;
	private Rent rent;
	private StringBuilder text;
	
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Parsing started.");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Parsing ended.");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("startElement -> " + "uri: " + uri + ", localName: " + localName + ", qName: " + qName);
		
		text = new StringBuilder();
		
		if (qName.equals("product")) {
			product = new Product();
			product.setProductId(Integer.parseInt(attributes.getValue("id")));
		}
		else if (qName.equals("rent")) {
			rent = new Rent();
			rent.setRentId(Integer.parseInt(attributes.getValue("id")));
			rent.setProduct(product);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		ProductTagName productTagName = ProductTagName.valueOf(qName.toUpperCase().replace(":PR", ""));
		
		switch (productTagName) {
		case NAME:
			product.setName(text.toString());
			break;
		case PRICE_PER_DAY:
			product.setPrisePerDay(Integer.parseInt(text.toString()));
			break;
		case AMOUNT:
			product.setAmount(Integer.parseInt(text.toString()));
			break;
		case RENT:
			rents.add(rent);
			rent = null;
			break;
		case DATE_FROM:
			rent.setDateFrom(Date.valueOf(text.toString()));
			break;
		case DATE_TO:
			if (!text.toString().isEmpty()) {
				rent.setDateTo(Date.valueOf(text.toString()));
			}			
			break;
		case PRODUCT:
			product.setRents(rents);
			products.add(product);
			product = null;
			rents = new HashSet<>();
			break;
		case PRODUCTS:
			break;
		default:
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		text.append(ch, start, length);
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		System.err.println("WARNING: line " + e.getLineNumber() + ": " + e.getMessage());
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		System.err.println("ERROR: line " + e.getLineNumber() + ": " + e.getMessage());
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.err.println("FATAL: line " + e.getLineNumber() + ": " + e.getMessage());
		throw (e);
	}
}
