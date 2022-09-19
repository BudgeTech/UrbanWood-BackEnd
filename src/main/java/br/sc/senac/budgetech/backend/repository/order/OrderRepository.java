package br.sc.senac.budgetech.backend.repository.order;

import br.sc.senac.budgetech.backend.model.order.Order;
import br.sc.senac.budgetech.backend.projection.order.OrderListProjection;
import br.sc.senac.budgetech.backend.projection.order.OrderProfileProjection;
import br.sc.senac.budgetech.backend.projection.order.OrderProjection;
import br.sc.senac.budgetech.backend.projection.order.OrderWithFurnituresProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<OrderProjection> findOrderByInitialDate(LocalDate initialDate);

    Optional<OrderProjection> findOrderById(Long id);

    List<OrderListProjection> findOrderListById(Long id);

    Optional<OrderProfileProjection> findOrderProfileById(Long id);

    Optional<OrderWithFurnituresProjection> findOrderWithFurnituresById(Long id);
}