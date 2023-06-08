package org.clientserver.jwtappdem.repository;

import org.clientserver.jwtappdem.model.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDishRepository extends JpaRepository<OrderDish, Long> {
}