package br.sc.senac.budgetech.model.client;

import br.sc.senac.budgetech.model.address.Address;
import br.sc.senac.budgetech.model.contact.Contact;
import br.sc.senac.budgetech.model.item.Item;
import br.sc.senac.budgetech.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "client_id", referencedColumnName = "user_id")
public class Client extends User {

    @Column(name = "client_name", nullable = false)
    private String nameClient;

    @Column(name = "client_last_name", nullable = false)
    private String lastName;

    @Column(name = "client_cpf", length = 15, nullable = false, unique = true)
    private String cpf;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Item> items;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public Client() {
        items = new ArrayList<>();
    }

    public Client(Long id, String login, String password, byte[] image, String nameClient, String lastName, String cpf) {
        super(id, login, password, image);
        this.nameClient = nameClient;
        this.lastName = lastName;
        this.cpf = cpf;
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
