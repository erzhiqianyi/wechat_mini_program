package com.erzhiqianyi.mall.repository.product;

import com.erzhiqianyi.mall.domain.product.AdvertsProduct;
import com.erzhiqianyi.mall.domain.product.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertsProductRepository extends JpaRepository<AdvertsProduct,Long> {
    List<AdvertsProduct> findByStatus(ProductStatus status);

}
