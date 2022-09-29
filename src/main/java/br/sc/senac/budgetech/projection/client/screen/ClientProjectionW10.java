package br.sc.senac.budgetech.projection.client.screen;

import java.time.LocalDate;
import java.util.List;

public interface ClientProjectionW10 {

    byte[] getImage();

    String getNameClient();

    String getLastName();

    String getCpf();

    AddressProjection getAddress();

    ContactProjection getContact();

    List<ItemProjection> getItems();

    interface AddressProjection {

        String getStreet();

        Integer getNumber();

        String getComplement();

        String getNeighborhood();

        String getCity();

        String getCep();
    }

    interface ContactProjection {

        String getEmail();

        String getPhoneNumber();

        String getSocialNetwork();
    }

    interface ItemProjection {

        OrderProjection getOrder();

        interface OrderProjection {

            Long getId();

            LocalDate getInitialDate();

            Double getPriceOrder();
        }
    }
}
