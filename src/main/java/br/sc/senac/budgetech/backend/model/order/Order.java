package br.sc.senac.budgetech.backend.model.order;

import br.sc.senac.budgetech.backend.enumeration.Payment;
import br.sc.senac.budgetech.backend.enumeration.Status;
import br.sc.senac.budgetech.backend.model.furniture.Furniture;
import br.sc.senac.budgetech.backend.model.item.Item;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "orderClass")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderClass_id")
    private Long id;

    @Column(name = "order_price")
    private Double priceOrder;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "order_status", nullable = false)
    private Status status;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "order_payment", nullable = false)
    private Payment payment;

    @Column(name = "order_initial_date", nullable = false)
    private LocalDate initialDate;

    @Column(name = "order_final_date")
    private LocalDate finalDate;

    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    private Item item;

    @ManyToMany
    @JoinTable(name = "order_furniture", joinColumns = @JoinColumn(name = "orderClass_id"), inverseJoinColumns = @JoinColumn(name = "furniture_id"))
    private List<Furniture> furnitures;

    public Order() {
        furnitures = new ArrayList<>();
    }

    public Order(Long id, Double priceOrder, Status status, Payment payment, LocalDate initialDate, LocalDate finalDate) {
        this.id = id;
        this.priceOrder = priceOrder;
        this.status = status;
        this.payment = payment;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        furnitures = new ArrayList<>();
    }

    public void addFurniture(Furniture furniture) {
        this.furnitures.add(furniture);
    }

    public void removeFurniture(Furniture furniture) {
        this.furnitures.remove(furniture);
    }

    @JsonManagedReference
    public List<Furniture> getFurniture() {
        return furnitures;
    }

    public double calculationPrice() {

        double valor = 0;

        for (Furniture furniture : furnitures) {
            valor += furniture.getPriceFurniture();
        }

        return valor;
    }
}
