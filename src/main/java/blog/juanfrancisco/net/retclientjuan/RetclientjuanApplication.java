package blog.juanfrancisco.net.retclientjuan;

import blog.juanfrancisco.net.retclientjuan.models.Order;
import blog.juanfrancisco.net.retclientjuan.models.ShoppingCart;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class RetclientjuanApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RetclientjuanApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

       ShoppingCart shoppingCart =this.createCart();
       this.addTwoProductsToCart(shoppingCart);
        this.doCheckoutWithOrderId(shoppingCart,"1");
        System.out.println(this.getOrderById("1"));




    }

    private Order getOrderById(String s) {

        final String uri = "http://localhost:8080/order/"+s;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return restTemplate.exchange(uri, HttpMethod.GET, entity, Order.class).getBody();

    }

    private void addTwoProductsToCart(ShoppingCart shoppingCart) {


        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        restTemplate.exchange("http://localhost:8080/cart/"+shoppingCart.getShoppingId()+"/200/3", HttpMethod.POST, entity, String.class);
        restTemplate.exchange("http://localhost:8080/cart/"+shoppingCart.getShoppingId()+"/100/2", HttpMethod.POST, entity, String.class);

    }

    private void doCheckoutWithOrderId(ShoppingCart shoppingCart, String orderId) {
        final String uri = "http://localhost:8080/order/checkout/"+shoppingCart.getShoppingId()+"/"+orderId+"/";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);


    }


    private ShoppingCart createCart() {
        final String uri = "http://localhost:8080/cart/";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<ShoppingCart> result = restTemplate.exchange(uri, HttpMethod.POST, entity, ShoppingCart.class);

        return result.getBody();
    }
}
