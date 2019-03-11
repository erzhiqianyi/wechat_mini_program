package com.erzhiqianyi.mall.controller.product;

import com.erzhiqianyi.mall.domain.product.ProductStatus;
import com.erzhiqianyi.mall.payload.product.AdvertsProductPayload;
import com.erzhiqianyi.mall.vo.product.AdvertsProductResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdvertsProductControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void list() {
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/product/adverts")
                        .queryParam("status", "CREATE")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .returnResult(AdvertsProductResponse.class)
                .getResponseBody()
                .toStream()
                .forEach(System.out::println);
    }

    @Test
    public void add() {
        AdvertsProductPayload advertsProduct = new AdvertsProductPayload();
        advertsProduct.setTitle("Test");
        advertsProduct.setPicUrl("Test");
        advertsProduct.setAdvertUrl("Test");
        advertsProduct.setSort(1);
        Long id = webClient.post().uri("/product/adverts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(advertsProduct))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Long.class).returnResult().getResponseBody();
        Assert.assertNotNull(id);
    }

    @Test
    public void update() {
        AdvertsProductPayload advertsProduct = new AdvertsProductPayload();
        advertsProduct.setStatus(ProductStatus.CHECKED);
        Long id = webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("/product/adverts")
                        .path("/2")
                        .build()
                )
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(advertsProduct))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Long.class).returnResult().getResponseBody();
        Assert.assertNotNull(id);
    }
}

