package com.erzhiqianyi.mall.controller.product;

import com.erzhiqianyi.mall.domain.product.AdvertsProduct;
import com.erzhiqianyi.mall.payload.product.AdvertsProductPayload;
import com.erzhiqianyi.mall.service.product.AdvertsProductService;
import com.erzhiqianyi.mall.vo.product.AdvertsProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class AdvertsProductController {

    @Autowired
    private AdvertsProductService advertsProductService;

    @GetMapping("/adverts")
    public Flux<AdvertsProductResponse> list() {
        return advertsProductService.listAdvertsProduct();
    }

    @PostMapping("/adverts")
    public Mono<Long> add( @RequestBody AdvertsProductPayload request){
        return advertsProductService.add(request);
    }


}
