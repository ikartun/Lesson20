package by.epam.tr.lesson20;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.tr.lesson20.bean.Product;
import by.epam.tr.lesson20.bean.Rent;
import by.epam.tr.lesson20.sax.SaxParser;

public class SAXParserExecutor {

	public static void main(String[] args) throws SAXException, IOException {
		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
		SaxParser saxParser = new SaxParser();
		xmlReader.setContentHandler(saxParser);
		xmlReader.parse("src/by/epam/tr/lesson20/ProductRent.xml");
		
		xmlReader.setFeature("http://xml.org/sax/features/validation", true);
		
		xmlReader.setFeature("http://xml.org/sax/features/namespaces", true);
		
		xmlReader.setFeature("http://xml.org/sax/features/string-interning", true);
		
		xmlReader.setFeature("http://apache.org/xml/features/validation/schema",
		true);
		
		List<Product> products = saxParser.getProducts();
		
		for (Product product : products) {
			System.out.println(product.getName());
			System.out.println("rents are: ");
			
			for (Rent rent : product.getRents()) {
				System.out.println("from " + rent.getDateFrom().toString() + " to " + ((rent.getDateTo() == null) ? "now" : rent.getDateTo().toString()));
			}
		}
	}
}
