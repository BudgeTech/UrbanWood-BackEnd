package br.sc.senac.budgetech.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long id;

    @Column(name = "street_address", length = 45, nullable = false)
    private String street;

    @Column(name = "number_address", nullable = false)
    private int number;

    @Column(name = "complement_address", length = 45, nullable = false)
    private String complement;

    @Column(name = "neighbor_address", length = 45, nullable = false)
    private String neighbor;

    @Column(name = "city_address", length = 45, nullable = false)
    private String city;

    @Column(name = "province_address", length = 45, nullable = false)
    private String province;

    @Column(name = "cep_address", nullable = false)
    private String cep;

    @OneToOne(mappedBy = "address")
    private Client client;

    @OneToOne(mappedBy = "address")
    private Woodwork woodwork;

    public Address(Long id, String street, int number, String complement, String neighbor, String city, String province, String cep) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighbor = neighbor;
        this.city = city;
        this.province = province;
        this.cep = cep;
    }
}