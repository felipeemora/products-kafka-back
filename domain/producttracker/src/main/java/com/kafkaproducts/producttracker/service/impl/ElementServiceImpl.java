package com.kafkaproducts.producttracker.service.impl;

import java.time.Duration;
import java.util.Base64;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kafkaproducts.producttracker.model.Element;
import com.kafkaproducts.producttracker.service.ElementService;

import reactor.core.publisher.Flux;

@Service
public class ElementServiceImpl implements ElementService {

    private static final String URL_SHOP_EXITO = "http://localhost:8080";
	private static final String URL_SHOP_JUMBO = "http://localhost:8081";

    @Override
    public Flux<Element> filterByMaxPrice(double precioMax) {
        Flux<Element> flux1=getAllProductsExito(URL_SHOP_EXITO,"Exito");
		Flux<Element> flux2=getAllProductsJumbo(URL_SHOP_JUMBO,"Jumbo");
		return Flux.merge(flux1, flux2)
				.filter(e-> e.getPrice() <= precioMax)
				.delayElements(Duration.ofSeconds(1));
    }

    private Flux<Element> getAllProductsExito(String url, String shop){
		WebClient webClient = WebClient.create(url);
		return webClient
            .get()
            .uri("/products")
            .accept(MediaType.APPLICATION_JSON)
            .header("Authorization", "Basic " + getAuth("levi", "pass1"))
            .retrieve()
            .bodyToFlux(Element.class)
            .map(e->{
                e.setShop(shop);
                return e;
            });
	}

    private Flux<Element> getAllProductsJumbo(String url, String shop){
		WebClient webClient = WebClient.create(url);
		return webClient
            .get()
            .uri("/products")
            .accept(MediaType.APPLICATION_JSON)
            .header("Authorization", "Basic " + getAuth("felipe", "pass1"))
            .retrieve()
            .bodyToFlux(Element.class)
            .map(e->{
                e.setShop(shop);
                return e;
            });
	}

    private String getAuth(String user, String pass) {
        String creadential = user + ":" + pass;
        return Base64.getEncoder().encodeToString(creadential.getBytes());
    }
}
