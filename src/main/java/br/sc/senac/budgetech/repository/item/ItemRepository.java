package br.sc.senac.budgetech.repository.item;

import br.sc.senac.budgetech.model.item.Item;
import br.sc.senac.budgetech.projection.item.ItemProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<ItemProjection> findItemById(Long id);

    List<ItemProjection> findItemByTotalPrice(Double totalPrice);
}
