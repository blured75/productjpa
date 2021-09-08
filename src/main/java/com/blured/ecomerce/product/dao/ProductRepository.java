package com.blured.ecomerce.product.dao;

import com.blured.ecomerce.product.domain.entity.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Cacheable("productsByCategoryCache")
    List<Product> findByCatId(int catId);

    @CacheEvict(cacheNames="productsByCategoryCache", key = "#result?.catId")
    Product save(Product product);

    @CacheEvict(cacheNames="productsByCategoryCache", key = "#p0")
    void deleteById(int id);
}
