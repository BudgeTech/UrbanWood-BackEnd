package br.sc.senac.budgetech.backend.projection;

public interface ClientAllProjection {

    Long getId();

    String getName();

    String getLastName();

    String getCpf();

    String getLogin();

    AddressProjection getAddress();

    ContactProjection getContact();

    interface AddressProjection {

        String getStreet();

        int getNumber();

        String getComplement();

        String getNeighbor();

        String getCity();

        String getProvince();

        String getCep();
    }

    interface ContactProjection {

        String getEmail();

        String getPhoneNumber();

        String getSocialNetwork();
    }
}