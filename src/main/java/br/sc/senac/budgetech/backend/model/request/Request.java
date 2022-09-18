package br.sc.senac.budgetech.backend.model.request;

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
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @Column(name = "request_price")
    private Double priceRequest;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "request_status", nullable = false)
    private Status status;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "request_payment", nullable = false)
    private Payment payment;

    @Column(name = "request_initial_date", nullable = false)
    private LocalDate initialDate;

    @Column(name = "request_final_date")
    private LocalDate finalDate;

    @OneToOne(mappedBy = "request")
    private Item item;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "request_furniture", joinColumns = @JoinColumn(name = "request_id"), inverseJoinColumns = @JoinColumn(name = "furniture_id"))
    private List<Furniture> furnitures;

    public Request() {
        furnitures = new ArrayList<>();
    }

    public Request(Long id, Double priceRequest, Status status, Payment payment, LocalDate initialDate, LocalDate finalDate) {
        this.id = id;
        this.priceRequest = priceRequest;
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

