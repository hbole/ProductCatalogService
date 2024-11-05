package com.example.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class SortParamDTO {
    private String attribute;
    private Sort.Direction sortType;
}
