package br.sc.senac.budgetech.projection.client.screen;

import java.util.List;

public interface ClientProjectionC13 {

    byte[] getImage();

    String getNameClient();

    String getCpf();

    ContactProjection getContact();

    AddressProjection getAddress();

    List<ItemProjection> getItems();

    interface ContactProjection {

        String getEmail();

        String getPhoneNumber();
    }

    interface AddressProjection {

        String getStreet();

        String getNeighborhood();

        String getCep();

        Integer getNumber();

        String getCity();
    }

    interface ItemProjection {

        OrderProjection getOrder();

        Integer getQuantity();

        interface OrderProjection {

            FurnitureProjection getFurniture();

            interface FurnitureProjection {

                byte[] getImage();

                String getNameFurniture();

                Long getId();

                Double getPriceFurniture();
            }
        }
    }
}