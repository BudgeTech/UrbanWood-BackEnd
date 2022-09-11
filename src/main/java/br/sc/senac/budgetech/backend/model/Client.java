package br.sc.senac.budgetech.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "client_name", nullable = false)
    private String nameClient;

    @Column(name = "client_last_name", nullable = false)
    private String lastName;

    @Column(name = "client_cpf", length = 15, nullable = false, unique = true)
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Client() {}

    public Client(String login, String password, Blob image, Long id, String nameClient, String lastName, String cpf) {
        super(login, password, image);
        this.id = id;
        this.nameClient = nameClient;
        this.lastName = lastName;
        this.cpf = cpf;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
