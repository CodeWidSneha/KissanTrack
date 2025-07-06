package com.agri.kissanTrack.repository;

import com.agri.kissanTrack.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByPriceGreaterThan(double price);

    @Query("Select p from Product p where p.name LIKE CONCAT(:prefix, '%')")
    List<Product> fetchProductsWithNamesStartWith(@Param("prefix")String prefix);

}
