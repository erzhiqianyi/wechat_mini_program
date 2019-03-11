package com.erzhiqianyi.mall.controller.product;

import com.erzhiqianyi.mall.domain.product.AdvertsProduct;
import com.erzhiqianyi.mall.domain.product.ProductStatus;
import com.erzhiqianyi.mall.payload.product.AdvertsProductPayload;
import com.erzhiqianyi.mall.service.product.AdvertsProductService;
import com.erzhiqianyi.mall.vo.product.AdvertsProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class AdvertsProductController {

    @Autowired
    private AdvertsProductService advertsProductService;

    @GetMapping("/adverts")
    public Flux<AdvertsProductResponse> list(ProductStatus status) {
        status = null == status ? ProductStatus.SALE : status;
        return advertsProductService.listAdvertsProduct(status);
    }

    @PostMapping("/adverts")
    public Mono<Long> add( @Valid  @RequestBody AdvertsProductPayload request){
        return advertsProductService.add(request);
    }

    @PutMapping("/adverts/{id}")
    public Mono<Long> update(@PathVariable Long id ,@RequestBody AdvertsProductPayload request){
        return advertsProductService.update(id,request);
    }

}
