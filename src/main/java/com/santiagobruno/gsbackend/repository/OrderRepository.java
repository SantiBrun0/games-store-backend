package com.santiagobruno.gsbackend.repository;

import com.santiagobruno.gsbackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.trackingCode = :trackingCode")
    Order findByTrackingCode(String trackingCode);

}
