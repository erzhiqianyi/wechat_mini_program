package com.erzhiqianyi.mall.service.product;

import com.erzhiqianyi.mall.domain.product.AdvertsProduct;
import com.erzhiqianyi.mall.domain.product.ProductStatus;
import com.erzhiqianyi.mall.payload.product.AdvertsProductPayload;
import com.erzhiqianyi.mall.repository.product.AdvertsProductRepository;
import com.erzhiqianyi.mall.vo.product.AdvertsProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.stream.Collectors;

@Service
public class AdvertsProductServiceImpl implements AdvertsProductService {

    @Autowired
    @Qualifier("jdbcScheduler")
    private Scheduler jdbcScheduler;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private AdvertsProductRepository advertsProductRepository;

    @Override
    public Flux<AdvertsProductResponse> listAdvertsProduct(ProductStatus status) {
        Flux<AdvertsProductResponse> defer = Flux.defer(
                () -> Flux.fromIterable(advertsProductRepository
                        .findByStatus(status)
                        .stream()
                        .map(AdvertsProductResponse::new)
                        .collect(Collectors.toList())));
        return defer.subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Long> add(AdvertsProductPayload request) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
            AdvertsProduct product = new AdvertsProduct();
            product.setTitle(request.getTitle());
            product.setPicUrl(request.getPicUrl());
            product.setAdvertUrl(request.getAdvertUrl());
            product.setSort(request.getSort());
            product.setStatus(ProductStatus.CREATE);
            AdvertsProduct savedProduct = advertsProductRepository.save(product);
            return savedProduct.getId();
        })).subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<Long> update(Long id,AdvertsProductPayload request) {
         return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
            return 1l;
        })).subscribeOn(jdbcScheduler);
    }


}
