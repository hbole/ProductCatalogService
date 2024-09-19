package com.example.productcatalogservice.dtomappers;

public interface DTOMapper<D, E> {
    E toDTO(D d);
    D toEntity(E dto);
}
