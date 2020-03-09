package blog.juanfrancisco.net.retclientjuan.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Data
public class Product {

	private String productNumber;
	private double price;
	private String description;
	private Stock stock;


	public Product(String productNumber, String description, double price) {
		super();
		this.productNumber = productNumber;
		this.price = price;
		this.description = description;
	}


}
