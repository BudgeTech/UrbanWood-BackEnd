package br.sc.senac.budgetech.backend.projection.furniture;

import java.util.List;

public interface FurnitureWithColorProjectionC14andW15andW18 {

    byte[] getImage();

    String getNameFurniture();

    String getDescription();

    Double getFurnitureSize();

    Double getPriceFurniture();

    List<ColorProjection> getColors();

    interface ColorProjection {

        String getNameColor();
    }
}