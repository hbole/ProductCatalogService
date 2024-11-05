package com.example.productcatalogservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchProductDTO {
    private String query;
    private int pageNumber;
    private int pageSize;
    private List<SortParamDTO> sortParams;
}
