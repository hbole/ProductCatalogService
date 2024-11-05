package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dto.SortParamDTO;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements ISearchService {
    private final ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> searchProducts(
            String query,
            int pageNumber,
            int pageSize,
            List<SortParamDTO> sortParams
    ) {
        Sort sort = null;
        if(sortParams != null && !sortParams.isEmpty()) {
            sort = Sort.by(sortParams.get(0).getSortType(), sortParams.get(0).getAttribute());

            for(int i = 1; i < sortParams.size(); i++) {
                sort = sort.and(Sort.by(sortParams.get(i).getSortType(), sortParams.get(i).getAttribute()));
            }
        }

        return this.productRepository.findProductByTitleEquals(
                query,
                PageRequest.of(pageNumber, pageSize, sort)
        );
    }
}
