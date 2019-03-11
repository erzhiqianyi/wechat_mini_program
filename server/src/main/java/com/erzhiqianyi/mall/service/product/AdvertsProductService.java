package com.erzhiqianyi.mall.service.product;

import com.erzhiqianyi.mall.domain.product.ProductStatus;
import com.erzhiqianyi.mall.payload.product.AdvertsProductPayload;
import com.erzhiqianyi.mall.vo.product.AdvertsProductResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AdvertsProductService {
    Flux<AdvertsProductResponse> listAdvertsProduct(ProductStatus status);

    Mono<Long> add(AdvertsProductPayload request);

    Mono<Long> update(Long id ,AdvertsProductPayload request);
}
